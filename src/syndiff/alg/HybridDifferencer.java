package alg;

import lang.syntaxtree.Node;
import lang.syntaxtree.NodeToken;
import utils.EditList;
import utils.Statics;
import visitors.AddEditVisitor;
import visitors.DeleteEditVisitor;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

import lang.syntaxtree.*;

public class HybridDifferencer extends Differencer {
	
	private Node left;
	private Node right;
	private EditList edits;
	
	public HybridDifferencer(Node leftNode, Node rightNode, EditList editList) {
		left = leftNode;
		right = rightNode;
		edits = editList;
	}
	
	public void compute() {
		edits.addEdits(compare(left, right));
	}
	
	// if two nodes have the same class and possibly image, return true.
	// else false.
	// does not look at subnodes.
	public boolean shallowCompare(Node left, Node right){
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
					// images are not the same.
					return false;
				} else {
					/*if (Statics.debug)
						printIndented("shallowCompare: matched " + currentString + " - " + leftImage + " with "
								+ oppositeString + " - " + rightImage);*/
					return true;
				}
					
					
			}
			
			// classes are the same
			/*if (Statics.debug)
				printIndented("shallowCompare: matched " + currentString + " with " + oppositeString);*/
			return true;
		}
		
		// classes are not the same.
		//if (Statics.debug)
			//printIndented("shallowCompare: failed to match " + currentString + " with " + oppositeString);
		return false;
	}
	
	// Determine if node is ordered or unordered type
	public boolean isUnordered(Node parent) {
		boolean isUnordered = true;
		ArrayList<Node> subNodes = parent.getSubNodes();
    	int numSubNodes = subNodes.size();
    	int i;
    	for(i = 0; i< numSubNodes; i++) {
    		if(!subNodes.get(i).getClass().toString().contains("Unordered")) {
    			isUnordered = false;
    			break;
    		}
    	}
    	return isUnordered;
	}
	
	// switching function. determines how to compare nodes
	public EditList compare(Node leftNode, Node rightNode) {
		EditList editList = new EditList();
		
		// compare the roots to see if we add an edit for that.
		boolean rootsAreTheSame = shallowCompare(leftNode, rightNode);
		if (!rootsAreTheSame){
			editList.addEdit(new TreeEdit(leftNode, rightNode));
		}
		
		// add edits for subnodes of each root.
		if(isUnordered(leftNode) || isUnordered(rightNode))
			editList.addEdits(compareSubnodesUnordered(leftNode, rightNode));
		else
			editList.addEdits(compareSubnodesOrdered(leftNode, rightNode));
		
		return editList;
	}
	
	// compare left and right subnodes, order doesn't matter, i.e. free swaps.
	public EditList compareSubnodesUnordered(Node leftNode, Node rightNode) {
		ArrayList<Node> currSubNodes = leftNode.getSubNodes();
		ArrayList<Node> oppSubNodes = rightNode.getSubNodes();
		
		EditList editList = new EditList();
		
		// Compare the two nodes (without subnodes).
		if ( ! (Statics.compareNodes(leftNode, rightNode)) ){
			editList.addEdit(new TreeEdit(leftNode, rightNode));
		}

		if (currSubNodes.size() == 0 && oppSubNodes.size() == 0){
			// no subnodes on either side. return.
			return editList;
		}	
		else if (currSubNodes.size() == 0 && oppSubNodes.size() != 0){
			// All subnodes on opposite side are adds.
			// There are no subnodes on this side.
			
			// Initialize the add edit visitor.
			AddEditVisitor aev = new AddEditVisitor(false);
			
			// Compute all the add edits.
			rightNode.accept(aev);
			
			// Get the list of add edits.
			EditList adds = aev.getEditList();
			
			// Add the add edits to the local edit list.
			editList.addEdits(adds);
			
			// add counted number of nodes to editDistance
			
			// we're done exploring down this node.
			return editList;
		}
		else if (currSubNodes.size() != 0 && oppSubNodes.size() == 0){
			// All subnodes on this side are deletes.
			// There are no subnodes on other side. 
			
			// Initialize the delete edit visitor.
			DeleteEditVisitor dev = new DeleteEditVisitor(false);
			
			// Compute all the delete edits.
			leftNode.accept(dev);
			
			// Get the list of delete edits.
			EditList deletes = dev.getEditList();

			// Add the delete edits to local edit list.
			editList.addEdits(deletes);
			
			// we're done exploring down this node.
			return editList;
		}
		
		// now we're in a situation where both nodes have subnodes.
		
		// set up a table of edit distances between all subnodes.
		
		// table is used to "map" subnodes on one side to the other side, based on least edits.
		int[][] editDistanceTable = new int[currSubNodes.size()][oppSubNodes.size()];
		
		// store edit list for going from node i to j in editList[i][j]
		EditList[][] editLists = new EditList[currSubNodes.size()][oppSubNodes.size()];
		
		// maps nodes on current side to opposite side.
		// to map node i to j, we have nodeMap[i] = j;
		int[] nodeMap = new int[currSubNodes.size()];
		
		// initialize values to -1 to show they have not been set yet.
		for(int i = 0; i < currSubNodes.size(); i++){
			nodeMap[i] = -1;
		}
		
		// Here we keep a vector of unmapped node indices.
		// we remove nodes as we map them to nodes in currSubNodes.
		Vector<Integer> unmappedOppNodes = new Vector<Integer>();
		
		// initialize the vector to include all oppSubNode indices
		for (int j = 0; j < oppSubNodes.size(); j++)
			unmappedOppNodes.add(j);
		
		// begin calculating edits
		// we try to only calculate edit distances as needed.
		// short circuit on 0-edit-distance matches.
		// if we have a 0-edit-distance pairing
		//		advance i (leftnode index)
		//		remove j from unmapped node list
		
		for (int i=0; i < currSubNodes.size() && unmappedOppNodes.size() != 0; i++){
		
			// get nodei, since its used throughout the loop below
			Node nodei = currSubNodes.get(i);
			
			for(int unmappedOppIndex = 0; unmappedOppIndex < unmappedOppNodes.size(); unmappedOppIndex++){
				Integer j = unmappedOppNodes.get(unmappedOppIndex);
				Node nodej = oppSubNodes.get(j);
				
				// initialize edit list.
				editLists[i][j] = new EditList();
				
				// set up and calculate edit distance for i -> j
				editLists[i][j].addEdits(compare(nodei, nodej));
				
				// store edit distance in table
				editDistanceTable[i][j] = editLists[i][j].size();
				
				if (editDistanceTable[i][j] == 0){
					nodeMap[i] = j;
					unmappedOppNodes.remove(j);
					break;
				}
			}
		}
		
		// now, pick the minimum changes for nodes not mapped above.
		for(int i = 0; i < currSubNodes.size(); i++){
			
			// skip nodes that are already mapped.
			if (nodeMap[i] != -1) continue;
			
			// variables used to find minimum edits.
			int minimumEdits = -1;
			int minimumIndex = -1;
			
			for (Integer j : unmappedOppNodes){
				
				// calculate edit distance if we haven't done this comparison already.
				if (editLists[i][j] == null) {
					// get nodes to compare.
					Node nodei = currSubNodes.get(i);
					Node nodej = oppSubNodes.get(j);
					
					// initialize edit list.
					editLists[i][j] = new EditList();
					
					// set up and calculate edit distance for i -> j
					editLists[i][j].addEdits(compare(nodei, nodej));
					
					// store edit distance in table
					editDistanceTable[i][j] = editLists[i][j].size();
				}					
				
				if (minimumEdits == -1 || editDistanceTable[i][j] < minimumEdits){
					// we've found a better match
					minimumEdits = editDistanceTable[i][j];
					minimumIndex = j;
				}
			}
			
			// map the minimum to i and remove from unmappedOppNodes.
			nodeMap[i] = minimumIndex;
			unmappedOppNodes.remove((Integer) minimumIndex);
		}
		
		// now, check that everything got mapped.
		// checking depends on number of nodes on each side.
		// also, handle adds or deletes here if not same number of nodes on both sides.
		
		if (currSubNodes.size() == oppSubNodes.size()){
			// each side has same number of sub nodes.
			// there should be no unmapped nodes in nodeMap
			for (int i = 0; i < currSubNodes.size(); i++)
				if (nodeMap[i] == -1){
					System.err.println("UnorderedDifferencer: Not all subnodes mapped for current node " + leftNode.toString());
					System.err.println("Encountered while comparing " + leftNode.toString() + " to " +rightNode.toString());
				}
			// check that unmappedOppNodes is empty
			if (unmappedOppNodes.size() != 0){
				System.err.println("UnorderedDifferencer: Not all subnodes mapped for opposite node " + rightNode.toString());
				System.err.println("Encountered while comparing " + leftNode.toString() + " to " + rightNode.toString());
			}
		}
		else if (currSubNodes.size() < oppSubNodes.size()){
			// more nodes on opposite side.
			// extraneous nodes are adds.
			
			// there should be no unmapped nodes in nodeMap
			for (int i = 0; i < currSubNodes.size(); i++)
				if (nodeMap[i] == -1){
					System.err.println("UnorderedDifferencer: Not all subnodes mapped for current node " + leftNode.toString());
					System.err.println("Encountered while comparing " + leftNode.toString() + " to " + rightNode.toString());
				}
			
			// go through all the unmapped nodes on the opposite side
			// these nodes and their subnodes are adds.
			for(Integer j : unmappedOppNodes){
				Node nodej = oppSubNodes.get(j);
				AddEditVisitor aev = new AddEditVisitor(true);
				nodej.accept(aev);
				editList.addEdits(aev.getEditList());
			}
		}
		else if (currSubNodes.size() > oppSubNodes.size()){
			// more nodes on current side
			// extraneous nodes must be deletes.
			
			// check that unmappedOppNodes is empty
			if (unmappedOppNodes.size() != 0){
				System.err.println("UnorderedEditDistanceVisitor: Not all subnodes mapped for opposite node " + rightNode.toString());
				System.err.println("Encountered while comparing " + leftNode.toString() + " to " + rightNode.toString());
			}
			
			// go through all unmapped nodes on this side
			// these nodes and their subnodes are deletes.
			for (int i = 0; i < currSubNodes.size(); i++){
				// skip mapped nodes.
				if (nodeMap[i] != -1) continue;
				
				Node nodei = currSubNodes.get(i);
				DeleteEditVisitor dev = new DeleteEditVisitor();
				nodei.accept(dev);
				editList.addEdits(dev.getEditList());
			}
		}
		
		// Calculating total changes here is a matter of looking up 
		// edit distances for nodes that got mapped to each other.
		
		for (int i = 0; i < currSubNodes.size(); i++){
			// skip unmapped nodes (they got handled above as deletes).
			// they are already added to edit distance.
			if (nodeMap[i] == -1) continue;
			int j = nodeMap[i];
			
			editList.addEdits(editLists[i][j]);
		}
		
		return editList;

	}
	
	// compare left and right as both ordered.
	public EditList compareSubnodesOrdered(Node leftNode, Node rightNode) {
		EditList editList = new EditList();
		 
    	ArrayList<Node> leftSub = leftNode.getSubNodes();
    	ArrayList<Node> rightSub = rightNode.getSubNodes(); 
    	int numLeftSub = leftSub.size();
    	int numRightSub = rightSub.size();

    	int i, j;

    	if(numLeftSub == 0) {
    	    if(numRightSub == 0)
    	    	return editList;
    	    else {
    	    	for(i = 0; i< numRightSub; i++)
    	    		editList.addEdits(insert(rightSub.get(i)));
    	    }
    	}
    	else if (numRightSub == 0) {
    	    for(i = 0; i< numLeftSub; i++)
    	    	editList.addEdits(delete(leftSub.get(i)));
    	}
    	
    	// Define costArray.
    	EditList[][] costArray = new EditList[numLeftSub+1][numRightSub+1];

    	// Initialize costArray with empty edit lists.
    	for(i = 0; i< numLeftSub + 1; i++)
    	    for(j = 0; j< numRightSub + 1; j++)
    	    	costArray[i][j] = new EditList();

    	// Store costs for deleting nodes from leftSub and 
    	// inserting nodes in rightSub (will be used twice)
    	
    	EditList[] deleteCosts = new EditList[numLeftSub];
    	
    	for(i = 0; i< numLeftSub; i++){
    		deleteCosts[i] = new EditList();
    		deleteCosts[i].addEdits(delete(leftSub.get(i)));
    	}
    	
    	EditList[] insertCosts = new EditList[numRightSub];
    	
    	for(i = 0; i< numRightSub; i++){
    		insertCosts[i] = new EditList();
    		insertCosts[i].addEdits(insert(rightSub.get(i)));
    	}
    	
    
    	
    	//build up costArray
    	//let boarder of array stay empty lists for easier comparison
    	//therefore correct access of node will be -1
    	i = j = 0;

    	while(i < numLeftSub && j < numRightSub) {
    	    EditList change = compare(leftSub.get(i), rightSub.get(j));
    	    if(deleteCosts[i].size() < insertCosts[j].size()) {
    	    	if(deleteCosts[i].size() < change.size()) {
    	    		costArray[i+1][j] = costArray[i][j].clone();
    	    		costArray[i+1][j].addEdits(deleteCosts[i]);
    	    		i++;
    	    	}
    	    	else {
    	    		costArray[i+1][j+1] = costArray[i][j].clone();
    	    		costArray[i+1][j+1].addEdits(change);
    	    		i++;
    	    		j++;
    	    	}
    	    }
    	    else if (insertCosts[j].size() < change.size()) {
    	    	costArray[i][j+1] = costArray[i][j].clone();
    	    	costArray[i][j+1].addEdits(insertCosts[j]);
    	    	j++;
    	    }
    	    else {
    	    	costArray[i+1][j+1] = costArray[i][j].clone();
    	    	costArray[i+1][j+1].addEdits(change);
    	    	i++;
    	    	j++;
    	    }
    	}
    	while(i != numLeftSub) {
    	    costArray[i+1][j] = costArray[i][j].clone();
    	    costArray[i+1][j].addEdits(deleteCosts[i]);
    	    i++;
    	}
    	while(j != numRightSub) {
    	    costArray[i][j+1] = costArray[i][j].clone();
    	    costArray[i][j+1].addEdits(insertCosts[j]);
    	    j++;
    	}
    	
    	editList.addEdits(costArray[numLeftSub][numRightSub].clone());
    	return editList;
	}
	
    public EditList insert(Node n) {
    	EditList editList = new EditList();

    	TreeEdit newEdit = new TreeEdit(null, n);
    	editList.addEdit(newEdit);

    	ArrayList<Node> subNodes = n.getSubNodes();
    	ListIterator<Node> subNodesIterator = subNodes.listIterator();

    	while(subNodesIterator.hasNext()) {
    	    Node currNode = subNodesIterator.next();
    	    editList.addEdits(insert(currNode));
    	}
    	
    	return editList;
    }
    
    public EditList delete(Node n) {
    	EditList editList = new EditList();
    	
    	TreeEdit newEdit = new TreeEdit(n, null);
    	editList.addEdit(newEdit);

    	ArrayList<Node> subNodes = n.getSubNodes();
    	ListIterator<Node> subNodesIterator = subNodes.listIterator();
    	
    	while(subNodesIterator.hasNext()) {
    	    Node currNode = subNodesIterator.next();
    	    editList.addEdits(delete(currNode));
    	}
    	
    	return editList;
    }
}
