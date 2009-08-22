package alg;

import utils.Statics;
import lang.syntaxtree.*;

public class TreeEdit {
	// Private data.
	private Statics.EditType editType = null;
	private Node leftNode = null;
	private Node rightNode = null;
	
	// Constructor.
	public TreeEdit(Node left, Node right) {
		// Determine edit type.
		if (left != null && right == null) {
			editType = Statics.EditType.Delete;
			leftNode = left;
			rightNode = null;
		}
		else if (left == null && right != null) {
			editType = Statics.EditType.Add;
			leftNode = null;
			rightNode = right;
		}
		else if (left != null && right != null) {
			editType = Statics.EditType.Change;
			leftNode = left;
			rightNode = right;
		}
		else {
			System.err.println("Error in tree edit constructor.");
		}
	}
	
	// Private fields are read only.
	public Statics.EditType getType() {
		return editType;
	}
	public Node getLeft() {
		return leftNode;
	}
	public Node getRight() {
		return rightNode;
	}
	
	// Dump for debug and verbose.
	public void dumpTreeEdit() {
		System.err.println("Tree edit type: " + editType.toString());
		if (leftNode == null) {
			System.err.println("LeftNode: NULL");
		}
		else {
			// Get the class string.
			String classString = leftNode.getClass().toString();

			// Find the last period.
			int lastPeriod = classString.lastIndexOf(".");

			// Truncate the class string if there is a period.
			if (lastPeriod > 0) {
				classString = classString.substring(lastPeriod + 1);
			}

			// If the node is a NodeToken then print the image as well.
			String image = "";
			String position = "";
			if (leftNode instanceof NodeToken) {
				image = " - " + ((NodeToken)(leftNode)).tokenImage;
				position += " at line ";
				position += ((NodeToken) leftNode).beginLine;
				position += " column ";
				position += ((NodeToken) leftNode).beginColumn;
				position += " in left file.";
			}
			
			System.err.println("LeftNode: " + classString + image + position);
		}
		if (rightNode == null) {
			System.err.println("RightNode: NULL");
		}
		else {
			// Get the class string.
			String classString = rightNode.getClass().toString();

			// Find the last period.
			int lastPeriod = classString.lastIndexOf(".");

			// Truncate the class string if there is a period.
			if (lastPeriod > 0) {
				classString = classString.substring(lastPeriod + 1);
			}

			// If the node is a NodeToken then print the image as well.
			String image = "";
			String position = "";
			if (rightNode instanceof NodeToken) {
				image = " - " + ((NodeToken)(rightNode)).tokenImage;
				position += " at line ";
				position += ((NodeToken) rightNode).beginLine;
				position += " column ";
				position += ((NodeToken) rightNode).beginColumn;
				position += " in right file.";
			}
			
			System.err.println("RightNode: " + classString + image + position);
		}
	}
}
