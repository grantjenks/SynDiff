package alg;

import lang.syntaxtree.Node;
import utils.EditList;
import visitors.AddEditVisitor;
import visitors.DeleteEditVisitor;

// This is more of an example than anything else.
//
// This class will produce an upper bound on the 
// number of edits which should occur for any
// two files which are syndiffed.
//
// The 'trivial' method of differencing is just to
// produce edits which delete every node in the 
// left tree and add every node in the right tree.

public class TrivialDifferencer extends Differencer {

	private Node left;
	private Node right;
	private EditList edits;
	
	public TrivialDifferencer(Node leftNode, Node rightNode, EditList editList) {
		left = leftNode;
		right = rightNode;
		edits = editList;
	}
	
	public void compute() {
		// Initialize the delete edit visitor.
		DeleteEditVisitor dev = new DeleteEditVisitor();
		
		// Compute all the delete edits.
		left.accept(dev);
		
		// Get the list of delete edits.
		EditList deletes = dev.getEditList();
		
		// Add the delete edits to local edit list.
		edits.addEdits(deletes);
		
		// Initialize the add edit visitor.
		AddEditVisitor aev = new AddEditVisitor();
		
		// Compute all the add edits.
		right.accept(aev);
		
		// Get the list of add edits.
		EditList adds = aev.getEditList();
		
		// Add the add edits to the local edit list.
		edits.addEdits(adds);
	}
}
