package visitors;

import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.Node;
import lang.visitor.ListVisitor;
import alg.TreeEdit;
import utils.EditList;

// Visitor creates add edits for all nodes from 
// a starting node down.
// This produces an edit list which will add an
// entire tree.
public class AddEditVisitor implements ListVisitor {
	// Private fields are readonly.
	private EditList editList;
	
	// true: include root in edit list
	// false: do not include root in edit list, just subnodes.
	private boolean addRoot;
	
	// used to find out if we're at the root.
	private int depth;
	
	// Constructor.
	public AddEditVisitor() {
		editList = new EditList();
		this.resetList();
		depth = 0;
		addRoot = true;
	}
	
	public AddEditVisitor(boolean addRoot){
		editList = new EditList();
		this.resetList();
		depth = 0;
		this.addRoot = addRoot;
	}
	
	// Get the editList.
	public EditList getEditList() {
		return editList;
	}
	
	// Reset the list.
	public void resetList() {
		editList.reset();
	}

	public void visit(Node n) {
		// add self if we're not at root
		// or if we are at root and we allow adding root.
		if ( depth != 0 || (addRoot && depth == 0)){
			// Add self to edit list.
			TreeEdit newEdit = new TreeEdit(null, n);
			editList.addEdit(newEdit);
		}
		
		// Get the list of subNodes.
		ArrayList<Node> subNodes = n.getSubNodes();

		// Get the iterator for the subNodes list.
		ListIterator<Node> subNodesIterator = subNodes.listIterator();

		// Iterate through the subNodes list and accept AddEditVisitor for each.
		while(subNodesIterator.hasNext()) {
			Node currNode = subNodesIterator.next();
			depth++;
			currNode.accept(this);
			depth--;
		}
	}
}