package alg;

import lang.syntaxtree.Node;
import lang.syntaxtree.NodeToken;
import utils.EditList;
import visitors.AddEditVisitor;
import visitors.DeleteEditVisitor;
import utils.Statics;

public class SimpleDifferencer extends Differencer {

	private Node left;
	private Node right;
	private EditList edits;

	public SimpleDifferencer(Node leftNode, Node rightNode, EditList editList) {
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
		EditList dels = dev.getEditList();

		// Initialize the add edit visitor.
		AddEditVisitor aev = new AddEditVisitor();

		// Compute all the add edits.
		right.accept(aev);

		// Get the list of add edits.
		EditList adds = aev.getEditList();

		// Initialize positions in lists.
		int delPos = 0;
		int addPos = 0;
		
		// Define max positions in lists.
		int maxNumDels = dels.size();
		int maxNumAdds = adds.size();
		
		// Declare variable to track where to apply incAmt
		boolean applyIncToDel = true;

		while (true) {
			// Track position variables.
			int incAmt = 0;
			int baseIncAmt = 0;
			
			// Find equality.
			while (true) {
				TreeEdit addEditAdj;
				TreeEdit delEditAdj;
				
				// Calculate adjusted bases.
				int adjBaseAddPos = addPos + baseIncAmt;
				int adjBaseDelPos = delPos + baseIncAmt;

				// Try to find equality in dels list.
				if (adjBaseDelPos + incAmt < maxNumDels) {
					// Get edits applying increment to dels.
					addEditAdj = adds.getEdit(adjBaseAddPos);
					delEditAdj = dels.getEdit(adjBaseDelPos + incAmt);
					
					// Set flag.
					applyIncToDel = true;
					
					// Break if equality found.
					if (treeEditParity(delEditAdj, addEditAdj)) {
						break;
					}
				}
				
				// Try to find equality in adds list.
				if (adjBaseAddPos + incAmt < maxNumAdds) {
					// Get edits applying increment to adds.
					addEditAdj = adds.getEdit(adjBaseAddPos + incAmt);
					delEditAdj = dels.getEdit(adjBaseDelPos);
					
					// Set flag.
					applyIncToDel = false;
					
					// Break if equality found.
					if (treeEditParity(delEditAdj, addEditAdj)) {
						break;
					}
				}
				
				// Increment 
				if (adjBaseDelPos + incAmt < maxNumDels ||
						adjBaseAddPos + incAmt < maxNumAdds) {
					incAmt++;
				}
				else {
					if (delPos + baseIncAmt + 1 < maxNumDels &&
							addPos + baseIncAmt + 1 < maxNumAdds) {
						baseIncAmt++;
						incAmt = 0;
					}
					else {
						break;
					}
				}
			}
			
			// Add edits up to adjusted values.
			for (int i = 0; i < baseIncAmt; i++) {
				edits.addEdit(dels.getEdit(delPos + i));
				edits.addEdit(adds.getEdit(addPos + i));
			}
			
			if (applyIncToDel) {
				for (int i = 0; i < incAmt; i++) {
					edits.addEdit(dels.getEdit(delPos + baseIncAmt + i));
				}
			}
			else {
				for (int i = 0; i < incAmt; i++) {
					edits.addEdit(adds.getEdit(addPos + baseIncAmt + i));
				}
			}
			
			// Add base increment amount.
			addPos += baseIncAmt;
			delPos += baseIncAmt;
			
			// Add single increment amount.
			if (applyIncToDel) {
				delPos += incAmt;
			}
			else {
				addPos += incAmt;
			}
			
			// Increment to compare next two positions.
			addPos++;
			delPos++;
			
			// Check if indexes exceed max sizes.
			if (addPos >= maxNumAdds || delPos >= maxNumDels) {
				break;
			}
		}
		
		// If there are remaining deletes then add them.
		while (delPos < maxNumDels) {
			edits.addEdit(dels.getEdit(delPos));
			delPos++;
		}
		
		// If there are remaining adds then add them.
		while (addPos < maxNumAdds) {
			edits.addEdit(adds.getEdit(addPos));
			addPos++;
		}
	}

	public boolean treeEditParity(TreeEdit left, TreeEdit right) {
		if (left.getType() != Statics.EditType.Delete
				&& right.getType() != Statics.EditType.Add) {
			System.err.println("Error in treeEditParity.");
		}

		Node leftNode = left.getLeft();
		Node rightNode = right.getRight();

		return checkNodeEquality(leftNode, rightNode);
	}

	public boolean checkTreeEditEquality(TreeEdit left, TreeEdit right) {
		if (left.getType() != right.getType()) {
			return false;
		}
		boolean leftEqual = checkNodeEquality(left.getLeft(), right.getLeft());
		boolean rightEqual = checkNodeEquality(left.getRight(), right.getRight());
		if (leftEqual && rightEqual ) {
			return true;
		}
		return false;
	}

	private boolean checkNodeEquality(Node left, Node right) {
		if (left == null && right == null) {
			return true;
		}
		else if (left == null || right == null) {
			return false;
		}
		String leftClassString = left.getClass().toString();
		String rightClassString = right.getClass().toString();
		if (leftClassString.compareTo(rightClassString) != 0) {
			return false;
		}
		if (left instanceof NodeToken && right instanceof NodeToken) {
			String leftImage = ((NodeToken)(left)).tokenImage;
			String rightImage = ((NodeToken)(right)).tokenImage;
			if (leftImage.compareTo(rightImage) != 0) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}
}
