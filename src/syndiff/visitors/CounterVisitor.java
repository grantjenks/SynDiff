package visitors;

import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.*;
import lang.visitor.*;

// a simple visitor used to count nodes. 
public class CounterVisitor implements ListVisitor{
	// tracks the number of nodes counted.
	private int numberOfNodes = 0;
	
	// returns  the number of nodes when done visiting.
	public int getNumberOfNodes() {
		return numberOfNodes;
	}
	
	// return number of subnodes.
	// CounterVisitor includes root, so we subtract 1.
	public int getNumberOfSubnodes() {
		return numberOfNodes - 1;
	}
	
	// used to reset the visitor's count.
	public void resetNumberOfNodes() {
		numberOfNodes = 0;
	}
	
	public void visit(Node n) {
		// Count this node.
		numberOfNodes++;
		
		// Get the list of subNodes.
		ArrayList<Node> subNodes = n.getSubNodes();

		// Get the iterator for the subNodes list.
		ListIterator<Node> subNodesIterator = subNodes.listIterator();

		// Iterate through the subNodes list and accept CounterVisitor for each.
		while(subNodesIterator.hasNext()) {
			Node currNode = subNodesIterator.next();
			currNode.accept(this);
		}
	}
}
