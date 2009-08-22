package utils;

import java.util.ListIterator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import lang.syntaxtree.NodeToken;
import alg.TreeEdit;
import utils.EditList;
import lang.syntaxtree.Node;
import utils.Statics;

public class EditListPrinter {
	private EditList editList;
	private Node lRoot;
	private Node rRoot;

	public EditListPrinter(EditList list, Node leftRoot, Node rightRoot) {
		lRoot = leftRoot;
		rRoot = rightRoot;
		if (list == null) {
			System.err.println("Error in constructor of EditListPrinter.");
		}
		editList = list;
	}

	public void print() {
		
		// Get the iterator for the editList list.
		ListIterator<TreeEdit> editListIterator = editList.getListIterator();
		
		if (Statics.view == Statics.ViewType.Viewer) {
			//Schedule a job for the event dispatching thread:
			//creating and showing this application's GUI.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					//Turn off metal's use of bold fonts
					UIManager.put("swing.boldMetal", Boolean.FALSE);
					DiffViewer.createAndShowGUI(editList, lRoot, rRoot);
				}
			});
		}
		else if (Statics.view == Statics.ViewType.StringOnly) {
			// Iterate through the edit list and print only the string changes.
			while (editListIterator.hasNext()) {
				TreeEdit currEdit = editListIterator.next();
				Node leftNode = currEdit.getLeft();
				Node rightNode = currEdit.getRight();

				if (currEdit.getType() == Statics.EditType.Add) {
					if ( ! (rightNode instanceof NodeToken)) {
						continue;
					}
					String pos = "a" + ((NodeToken)rightNode).beginLine;
					pos += "," + ((NodeToken)rightNode).beginColumn;
					pos += ":" + ((NodeToken)rightNode).endLine;
					pos += "," + ((NodeToken)rightNode).endColumn;
					System.out.println(pos);
					System.out.print(">");
					System.out.println(rightNode.toString());
				}
				else if (currEdit.getType() == Statics.EditType.Delete) {
					if ( ! (leftNode instanceof NodeToken)) {
						continue;
					}
					String pos = "d" + ((NodeToken)leftNode).beginLine;
					pos += "," + ((NodeToken)leftNode).beginColumn;
					pos += ":" + ((NodeToken)leftNode).endLine;
					pos += "," + ((NodeToken)leftNode).endColumn;
					System.out.println(pos);
					System.out.print("<");
					System.out.println(leftNode.toString());
				}
				else if (currEdit.getType() == Statics.EditType.Change) {
					if ( ! (rightNode instanceof NodeToken && 
							leftNode instanceof NodeToken)) {
						continue;
					}
					String pos = ((NodeToken)leftNode).beginLine + ",";
					pos += ((NodeToken)leftNode).beginColumn + ":";
					pos += ((NodeToken)leftNode).endLine + ",";
					pos += ((NodeToken)leftNode).endColumn + "c";
					pos += ((NodeToken)rightNode).beginLine + ",";
					pos += ((NodeToken)rightNode).beginColumn + ":";
					pos += ((NodeToken)rightNode).endLine + ",";
					pos += ((NodeToken)rightNode).endColumn + "";
					System.out.println(pos);
					System.out.print("<");
					System.out.println(leftNode.toString());
					System.out.println("---");
					System.out.print(">");
					System.out.println(rightNode.toString());
				}
				else {
					// Should never get here.
				}
			}
		}
		else if (Statics.view == Statics.ViewType.All) {
			// Iterate through the editList list and print all the tree edits.
			while (editListIterator.hasNext()) {
				TreeEdit currEdit = editListIterator.next();

				if (currEdit.getType() == Statics.EditType.Add) {
					Node n = currEdit.getRight();

					// Get the class string.
					String classString = n.getClass().toString();

					// Find the last period.
					int lastPeriod = classString.lastIndexOf(".");

					// Truncate the class string if there is a period.
					if (lastPeriod > 0) {
						classString = classString.substring(lastPeriod + 1);
					}

					// If the node is a NodeToken then print the image and position as well.
					String image = "";
					String position = "";
					if (n instanceof NodeToken) {
						image = " - " + ((NodeToken)(n)).tokenImage;
						position += " at line ";
						position += ((NodeToken) n).beginLine;
						position += " column ";
						position += ((NodeToken) n).beginColumn;
						position += " in right file.";
					}

					System.out.println("Add node: " + classString + image + position);
				}
				else if (currEdit.getType() == Statics.EditType.Delete) {
					Node n = currEdit.getLeft();

					// Get the class string.
					String classString = n.getClass().toString();

					// Find the last period.
					int lastPeriod = classString.lastIndexOf(".");

					// Truncate the class string if there is a period.
					if (lastPeriod > 0) {
						classString = classString.substring(lastPeriod + 1);
					}

					// If the node is a NodeToken then print the image and position as well.
					String image = "";
					String position = "";
					if (n instanceof NodeToken) {
						image = " - " + ((NodeToken)(n)).tokenImage;
						position += " at line ";
						position += ((NodeToken) n).beginLine;
						position += " column ";
						position += ((NodeToken) n).beginColumn;
						position += " in left file.";
					}

					System.out.println("Delete node: " + classString + image + position);
				}
				else if (currEdit.getType() == Statics.EditType.Change) {
					Node n = currEdit.getLeft();
					Node m = currEdit.getRight();

					// Get the class string.
					String classn = n.getClass().toString();
					String classm = m.getClass().toString();

					// Find the last period.
					int lastPeriodn = classn.lastIndexOf(".");
					int lastPeriodm = classm.lastIndexOf(".");

					// Truncate the class string if there is a period.
					if (lastPeriodn > 0) {
						classn = classn.substring(lastPeriodn + 1);
					}
					if (lastPeriodm > 0) {
						classm = classm.substring(lastPeriodm + 1);
					}

					// If the node is a NodeToken then print the image and position as well.
					String imagen = "";
					String positionn = "";
					if (n instanceof NodeToken) {
						imagen = " - " + ((NodeToken)(n)).tokenImage;
						positionn += " at line ";
						positionn += ((NodeToken) n).beginLine;
						positionn += " column ";
						positionn += ((NodeToken) n).beginColumn;
						positionn += " in left file.";
					}
					String imagem = "";
					String positionm = "";
					if (m instanceof NodeToken) {
						imagem = " - " + ((NodeToken)(m)).tokenImage;
						positionm += " at line ";
						positionm += ((NodeToken) m).beginLine;
						positionm += " column ";
						positionm += ((NodeToken) m).beginColumn;
						positionm += " in right file.";
					}
					System.out.println("Change node: " + classn + imagen + 
							positionn + "\n to " + classm + imagem + positionm);
				}
			}
		}
	}
}
