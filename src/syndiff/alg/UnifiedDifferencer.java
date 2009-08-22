package alg;

import lang.syntaxtree.Node;
import utils.EditList;
import utils.Statics;
import visitors.DeleteEditVisitor;
import visitors.AddEditVisitor;
import utils.UnorderedChooser;
import utils.OrderedChooser;
import utils.History;
import utils.RCPair;
import java.util.HashSet;

public class UnifiedDifferencer extends Differencer {

	private Node left;
	private Node right;
	private EditList edits;
	
	public UnifiedDifferencer(Node leftNode, Node rightNode, EditList editList) {
		left = leftNode;
		right = rightNode;
		edits = editList;
	}

	public void compute() {
		edits.addEdits(compare(left, right));
	}

	public EditList compare(Node curNode, Node oppNode) {
		// Get the number of subnodes on each side.
		int numCurSubNodes = curNode.getSubNodes().size();
		int numOppSubNodes = oppNode.getSubNodes().size();

		if (numCurSubNodes == 0 && numOppSubNodes == 0) {
			// Neither node has subnodes.

			// Initialize an empty edit list.
			EditList el = new EditList();

			// Add a change edit if these nodes are different.
			if ( ! (Statics.compareNodes(curNode, oppNode)) ) {
				el.addEdit(new TreeEdit(curNode, oppNode));
			}

			// Return the edit list.
			return el;
		}
		else if (numCurSubNodes == 0 && numOppSubNodes > 0) {
			// The opposite node has subnodes.

			// Create an edit list of adds excluding root.
			AddEditVisitor aev = new AddEditVisitor(false);
			oppNode.accept(aev);
			EditList el = aev.getEditList();

			// Add a change edit if root nodes are different.
			if ( ! (Statics.compareNodes(curNode, oppNode)) ) {
				el.addEditToFront(new TreeEdit(curNode, oppNode));
			}

			return el;
		}
		else if (numCurSubNodes > 0 && numOppSubNodes == 0) {
			// The current node has subnodes.

			// Create an edit list of deletes excluding root.
			DeleteEditVisitor dev = new DeleteEditVisitor(false);
			curNode.accept(dev);
			EditList el = dev.getEditList();

			// Add a change edit if root nodes are different.
			if ( ! (Statics.compareNodes(curNode, oppNode)) ) {
				el.addEditToFront(new TreeEdit(curNode, oppNode));
			}

			return el;
		}
		else {
			// Both nodes have subnodes.

			// Build a table of all possible matches.
			EditList table[][];
			
			// Determine if this is an unordered case.
			boolean unorderedCase = isUnorderedNode(curNode);
			
			// Ordered case first.
			if (! unorderedCase) {
				// Initialize table.
				table = new EditList[numCurSubNodes][numOppSubNodes];
				
				// Calculate min.
				int min = numCurSubNodes < numOppSubNodes ? 
						numCurSubNodes : numOppSubNodes;
				
				// Fill diagonal.
				for (int i = 0; i < min; i++) {
					Node curCompareNode = curNode.getSubNodes().get(i);
					Node oppCompareNode = oppNode.getSubNodes().get(i);
					table[i][i] = compare(curCompareNode, oppCompareNode);
				}
				
				// Check if able to immediately return.
				if (zerosOnDiagonal(table, min)) {
					return new EditList();
				}

				// Fill in the rest of the table.
				for (int i = 0; i < numCurSubNodes; i++) {
					for (int j = 0; j < numOppSubNodes; j++) {
						if (i != j) {
							Node curCompareNode = curNode.getSubNodes().get(i);
							Node oppCompareNode = oppNode.getSubNodes().get(j);
							table[i][j] = compare(curCompareNode, oppCompareNode);
						}
					}
				}
			}
			// Make sure number of rows is less than/equal to number of columns.
			// This is for the unordered algorithm if needed.
			else if (numOppSubNodes < numCurSubNodes) {
				// Initialize table.
				table = new EditList[numOppSubNodes][numCurSubNodes];

				// Fill diagonal.
				for (int i = 0; i < numOppSubNodes; i++) {
					Node curCompareNode = curNode.getSubNodes().get(i);
					Node oppCompareNode = oppNode.getSubNodes().get(i);
					table[i][i] = compare(curCompareNode, oppCompareNode);
				}

				// Check if able to immediately return.
				if (zerosOnDiagonal(table, numOppSubNodes)) {
					return new EditList();
				}

				// Fill in the rest of the table.
				for (int i = 0; i < numCurSubNodes; i++) {
					for (int j = 0; j < numOppSubNodes; j++) {
						if (i != j) {
							Node curCompareNode = curNode.getSubNodes().get(i);
							Node oppCompareNode = oppNode.getSubNodes().get(j);
							table[j][i] = compare(curCompareNode, oppCompareNode);
						}
					}
				}
			}
			else {
				// Initialize table.
				table = new EditList[numCurSubNodes][numOppSubNodes];

				// Fill diagonal.
				for (int i = 0; i < numCurSubNodes; i++) {
					Node curCompareNode = curNode.getSubNodes().get(i);
					Node oppCompareNode = oppNode.getSubNodes().get(i);
					table[i][i] = compare(curCompareNode, oppCompareNode);
				}

				// Check if able to immediately return.
				if (zerosOnDiagonal(table, numCurSubNodes)) {
					return new EditList();
				}

				// Fill in the rest of the table.
				for (int i = 0; i < numCurSubNodes; i++) {
					for (int j = 0; j < numOppSubNodes; j++) {
						if (i != j) {
							Node curCompareNode = curNode.getSubNodes().get(i);
							Node oppCompareNode = oppNode.getSubNodes().get(j);
							table[i][j] = compare(curCompareNode, oppCompareNode);
						}
					}
				}
			}

			// Edit list to return.
			EditList el = new EditList();
			
			if (unorderedCase) {
				// Unordered chooser.
				
				UnorderedChooser choice = new UnorderedChooser(table);
				choice.computeMinimumSum();
				History hist = choice.getResult();

				HashSet<Integer> cols = new HashSet<Integer>();
				for (RCPair pair : hist.rcPairs) {
					el.addEdits(table[pair.row][pair.col]);
					cols.add(pair.col);
				}

				for (int i = 0; i < table[0].length; i++) {
					if ( ! cols.contains(i) ) {
						if (numCurSubNodes < numOppSubNodes) {
							AddEditVisitor aev = new AddEditVisitor();
							oppNode.getSubNodes().get(i).accept(aev);
							el.addEdits(aev.getEditList());
						}
						else {
							DeleteEditVisitor dev = new DeleteEditVisitor();
							curNode.getSubNodes().get(i).accept(dev);
							el.addEdits(dev.getEditList());
						}
					}
				}
			}
			else {
				// Ordered chooser.
				
				// Compute the delete vector.
				EditList[] deletes = new EditList[numCurSubNodes];
				int i = 0;
				for (Node n : curNode.getSubNodes()) {
					DeleteEditVisitor dev = new DeleteEditVisitor();
					n.accept(dev);
					deletes[i] = dev.getEditList();
					i++;
				}
				
				// Compute the add vector.
				EditList[] adds = new EditList[numOppSubNodes];
				int j = 0;
				for (Node n : oppNode.getSubNodes()) {
					AddEditVisitor aev = new AddEditVisitor();
					n.accept(aev);
					adds[j] = aev.getEditList();
					j++;
				}
				
				// Initialize the ordered chooser.
				OrderedChooser choice = new OrderedChooser(deletes, adds, table);
				
				// Compute the result.
				choice.computeMinimumSum();
				
				// Get the result.
				History result = choice.getResult();
				
				// Form the edit list from the result.
				for (RCPair p : result.rcPairs) {
					if (p.row == -1 && p.col != -1) {
						el.addEdits(adds[p.col]);
					}
					else if (p.col == -1 && p.row != -1) {
						el.addEdits(deletes[p.row]);
					}
					else if (p.row != -1 && p.col != -1) {
						el.addEdits(table[p.row][p.col]);
					}
					else {
						// Code should never reach here.
					}
				}
			}
			
			// Add a change edit if root nodes are different.
			if ( ! (Statics.compareNodes(curNode, oppNode)) ) {
				el.addEditToFront(new TreeEdit(curNode, oppNode));
			}

			return el;
		}
		// Code can never reach here.
	}

	public boolean zerosOnDiagonal(EditList[][] table, int rank) {
		boolean allzeros = true;
		for (int i = 0; i < rank; i++) {
			if (table[i][i].size() != 0) {
				allzeros = false;
				break;
			}
		}
		return allzeros;
	}
	
	public boolean isUnorderedNode(Node parent) {
		for (Node n : parent.getSubNodes()) {
			if ( ! n.getClass().toString().contains("Unordered")) {
				return false;
			}
		}
		return true;
	}
}
