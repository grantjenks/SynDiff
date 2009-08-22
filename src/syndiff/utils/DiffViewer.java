package utils;

/*
 * Text display.
 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import lang.syntaxtree.Node;
import lang.syntaxtree.NodeToken;
import java.util.*;
import alg.TreeEdit;

public class DiffViewer extends JPanel {

	String newline = "\n";
	
	LinkedList<NodeToken> tokens = new LinkedList<NodeToken>();
	
	// Just to shut compiler up.
	static final long serialVersionUID = 0;

	public DiffViewer(EditList edits, Node lRoot, Node rRoot) {
		setLayout(new BorderLayout());
		
		//Create left text pane.
		JTextPane leftTextPane = createTextPane(edits, lRoot);
		leftTextPane.setEditable(false);
		JScrollPane leftPaneScrollPane = new JScrollPane(leftTextPane);
		leftPaneScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		leftPaneScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		leftPaneScrollPane.setPreferredSize(new Dimension(500, 400));
		leftPaneScrollPane.setMinimumSize(new Dimension(10, 10));
		leftPaneScrollPane.setBorder(BorderFactory.createTitledBorder(
				Statics.left));
		
		//Create right text pane.
		JTextPane rightTextPane = createTextPane(edits, rRoot);
		rightTextPane.setEditable(false);
		JScrollPane rightPaneScrollPane = new JScrollPane(rightTextPane);
		rightPaneScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightPaneScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		rightPaneScrollPane.setPreferredSize(new Dimension(500, 400));
		rightPaneScrollPane.setMinimumSize(new Dimension(10, 10));
		rightPaneScrollPane.setBorder(BorderFactory.createTitledBorder(
				Statics.right));
		
		//Put the editor pane and the text pane in a split pane.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftPaneScrollPane,rightPaneScrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.5);
		JPanel centerPane = new JPanel(new GridLayout(1,0));
		centerPane.add(splitPane);
		
		//Put everything together.
		add(centerPane, BorderLayout.CENTER);
	}

	private JTextPane createTextPane(EditList edits, Node root) {
		
		JTextPane textPane = new JTextPane()
		{
			public void setSize(Dimension d)
			{
				if (d.width < getParent().getSize().width)
					d.width = getParent().getSize().width;
 
				super.setSize(d);
			}
 
			public boolean getScrollableTracksViewportWidth()
			{
				return false;
			}
		};
		
		StyledDocument doc = textPane.getStyledDocument();
		
		addStylesToDocument(doc);
		
		tokens.clear();
		
		addNodeTokensToList(root);
		
		Hashtable<Node, String> editnodes = 
			new Hashtable<Node, String>();
		
		for (TreeEdit te : edits.getList()) {
			String type = te.getType().toString();
			Node left = te.getLeft();
			Node right = te.getRight();
			if (left != null) {
				editnodes.put(left, type);
			}
			if (right != null) {
				editnodes.put(right, type);
			}
		}
		
		int curLine = 0;
		int curCol = 0;
		
		try {
			for (NodeToken t : tokens) {
				
				String sToInsert = t.tokenImage;
				
				if (curLine != t.beginLine) {
					String newlines = "";
					for (; curLine < t.beginLine; curLine++) {
						newlines += "\n";
					}
					doc.insertString(doc.getLength(), newlines, doc.getStyle("regular"));
					curCol = 0;
				}
				
				if (curCol != t.beginColumn) {
					String spaces = "";
					for (; curCol < t.beginColumn; curCol++) {
						spaces += " ";
					}
					doc.insertString(doc.getLength(), spaces, doc.getStyle("regular"));
				}
				
				String type = "regular";
				
				if (editnodes.containsKey(t)) {
					type = editnodes.get(t);
				}
				
				doc.insertString(doc.getLength(), sToInsert, doc.getStyle(type));
				
				curLine = t.endLine;
				curCol = t.endColumn + 1;
			}
		}
		catch (BadLocationException ble) {
			System.err.println("FAILED TO INSERT TEXT!");
		}
		
		return textPane;
	}
	
	private void addNodeTokensToList(Node parent) {
		for (Node child : parent.getSubNodes()) {
			addNodeTokensToList(child);
		}
		if (parent instanceof NodeToken) {
			tokens.add((NodeToken)parent);
		}
	}

	protected void addStylesToDocument(StyledDocument doc) {
		Style def = StyleContext.getDefaultStyleContext().
		getStyle(StyleContext.DEFAULT_STYLE);
		
		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "Monospaced");

		Style s = doc.addStyle("Add", regular);
		StyleConstants.setBackground(s, Color.GREEN);
		
		s = doc.addStyle("Change", regular);
		StyleConstants.setBackground(s, Color.YELLOW);
		
		s = doc.addStyle("Delete", regular);
		StyleConstants.setBackground(s, Color.RED);
	}

	public static void createAndShowGUI(EditList edits, Node leftRoot, Node rightRoot) {
		//Create and set up the window.
		JFrame frame = new JFrame("SynDiff");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new DiffViewer(edits, leftRoot, rightRoot));

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
