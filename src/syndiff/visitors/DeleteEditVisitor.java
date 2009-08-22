package visitors;

import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.Node;
import lang.visitor.ListVisitor;
import alg.TreeEdit;
import utils.EditList;

// Visitor creates delete edits for all nodes from 
// a starting node down.
// This produces an edit list which will delete an
// entire tree.
public class DeleteEditVisitor implements ListVisitor {
	// Private fields are read only.
	private EditList editList;
	
	// true: include root in delete list
	// false: include only subnodes of root.
	private boolean deleteRoot;
	
	// necessary to find out if we're at the root
	private int depth;
	
	// Constructor.
	public DeleteEditVisitor() {
		editList = new EditList();
		depth = 0;
		deleteRoot = true;
	}
	
	public DeleteEditVisitor(boolean deleteRoot){
		editList = new EditList();
		this.deleteRoot = deleteRoot;
		depth = 0;
	}
	
	// Get the editList.
	public EditList getEditList() {
		return editList;
	}
	
	// Reset the edit list.
	public void resetList() {
		editList.reset();
	}

	public void visit(Node n) {
		// add self if we're not at root
		// or if we are at root and we allow adding root.
		if ( depth != 0 || (deleteRoot && depth == 0)){
			// Add self to edit list.
			TreeEdit newEdit = new TreeEdit(n, null);
			editList.addEdit(newEdit);
		}
		
		// Get the list of subNodes.
		ArrayList<Node> subNodes = n.getSubNodes();

		// Get the iterator for the subNodes list.
		ListIterator<Node> subNodesIterator = subNodes.listIterator();

		// Iterate through the subNodes list and accept DeleteEditVisitor for each.
		while(subNodesIterator.hasNext()) {
			Node currNode = subNodesIterator.next();
			depth++;
			currNode.accept(this);
			depth--;
		}
	}
}