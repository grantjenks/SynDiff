package visitors;

import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.*;
import lang.visitor.*;

public class OutputVisitor implements ListVisitor {
	// Used for tracking the indent level.
	private int spaces = 0;

	// Visit a Node object.
	public void visit(Node n) {
		// Build the indent string.
		String indent = "";
		for (int i = 0; i < spaces; i++) {
			indent += " ";
		}

		// Get the class string.
		String classString = n.getClass().toString();

		// Find the last period.
		int lastPeriod = classString.lastIndexOf(".");

		// Truncate the class string if there is a period.
		if (lastPeriod > 0) {
			classString = classString.substring(lastPeriod + 1);
		}

		// If the node is a NodeToken then print the image as well.
		String image = "";
		if (n instanceof NodeToken) {
			image = " - " + ((NodeToken)(n)).tokenImage;
		}

		// Print the current class information.
		System.out.println(indent + classString + image);

		// Get the list of subNodes.
		ArrayList<Node> subNodes = n.getSubNodes();

		// Get the iterator for the subNodes list.
		ListIterator<Node> subNodesIterator = subNodes.listIterator();

		// Increase the indent amount.
		spaces += 1;

		// Iterate through the subNodes list and accept OutputVisitor for each.
		while(subNodesIterator.hasNext()) {
			Node currNode = subNodesIterator.next();
			currNode.accept(this);
		}

		// Decrease the indent amount.
		spaces -= 1;
	}
}