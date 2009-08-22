package visitors;

import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.*;

// Note that this class is not actually a visitor in that it does 
// not implement or inherit from one of the automatically generated 
// visitor classes.

public class OrderedEqualityVisitor {
	
	public boolean compare(Node left, Node right) {
		
		// Get the class strings for opposite and current.
		String currentString = left.getClass().toString();
		String oppositeString = right.getClass().toString();
		
		// Compare the class strings.
		if (currentString.compareTo(oppositeString) == 0) { // equal
			
			// If they're NodeToken objects, compare the images.
			if ((left instanceof NodeToken) && 
					(right instanceof NodeToken)) {
				
				// Get the left and right images.
				String leftImage = ((NodeToken)(left)).tokenImage;
				String rightImage = ((NodeToken)(right)).tokenImage;
				
				// If the images are not equal then return false.
				if (leftImage.compareTo(rightImage) != 0) {
					return false;
				}
			}
			
			// Get the list of current subNodes.
			ArrayList<Node> curSubNodes = left.getSubNodes();
			// Get the iterator for the current subNodes list.
			ListIterator<Node> curSubNodesIterator = curSubNodes.listIterator();

			// Get the list of opposite subNodes.
			ArrayList<Node> oppSubNodes = right.getSubNodes();
			// Get the iterator for the opposite subNodes list.
			ListIterator<Node> oppSubNodesIterator = oppSubNodes.listIterator();

			// Itearte through the subnodes of current.
			while (curSubNodesIterator.hasNext()) {
				Node oppSubNode;
				// Get the current subnode.
				Node curSubNode = curSubNodesIterator.next();
				
				// Test if their is an opposite subnode.
				if (oppSubNodesIterator.hasNext()) {
					// Set the opposite subnode.
					oppSubNode = oppSubNodesIterator.next();
				}
				else {
					// No opposite so inequality.
					return false;
				}
				
				// Compare the subnodes.
				boolean result = compare(curSubNode, oppSubNode);
				
				// If unequal then immediately return.
				if (result == false) {
					return false;
				}
			}
			
			// If both iterators are finished then return true.
			if (curSubNodesIterator.hasNext() == false &&
					oppSubNodesIterator.hasNext() == false) {
				return true;
			}
			else {
				// If one of the iterators is not finished, return false.
				return false;
			}
		}
		else { // Not equal class types.
			return false;
		}
	}
}
