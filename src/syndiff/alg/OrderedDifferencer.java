package alg;

import utils.EditList;
import java.util.ArrayList;
import java.util.ListIterator;
import lang.syntaxtree.*;
import utils.Statics;

public class OrderedDifferencer extends Differencer{

	private Node left;
	private Node right;
	private EditList edits;
	
	public OrderedDifferencer(Node leftNode, Node rightNode, EditList editList) {
		left = leftNode;
		right = rightNode;
		edits = editList;
	}
	
	public void compute() {
		edits.addEdits(compare(left, right));
	}
	
	/* compare
	 * - look shallow
	 * 
	 * 
	 */
		
    public EditList compare(Node leftNode, Node rightNode) {
    	
    	EditList editList = new EditList();
 
		if ( ! (Statics.compareNodes(leftNode, rightNode)) ) {
			editList.addEdit(new TreeEdit(leftNode, rightNode));
		}
		
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
    	
    	// Store "boarder" costs of cost matrix for deleting
    	// and inserting nodes
    	for(i = 1; i< numLeftSub + 1; i++) {
    		costArray[i][0] = costArray[i-1][0].clone();
    		costArray[i][0].addEdits(deleteCosts[i-1]);
    	}
    	for(j = 1; j< numRightSub + 1; j++) {
    		costArray[0][j] = costArray[0][j-1].clone();
    		costArray[0][j].addEdits(insertCosts[j-1]);
    	}
    	
    	//build up costArray
    	//let boarder of array stay empty lists for easier comparison
    	//therefore correct access of node will be -1
    	for(i = 1; i< numLeftSub + 1; i++) {
    	    for(j = 1; j< numRightSub + 1; j++) {
    		
    	    	EditList editCost = compare(leftSub.get(i-1), rightSub.get(j-1));

    	    	EditList deleteCost = deleteCosts[i-1].clone();

    	    	EditList insertCost = insertCosts[j-1].clone();
    	    	//find min edit path
    	    	if(costArray[i-1][j-1].size() + editCost.size() < costArray[i][j-1].size() + insertCost.size()) {
    	    		if(costArray[i-1][j-1].size() + editCost.size() < costArray[i-1][j].size() + deleteCost.size()) {
    	    			//Cheaper to edit
    	    			costArray[i][j] = costArray[i-1][j-1].clone();
    	    			costArray[i][j].addEdits(editCost);
    	    		}
    	    		else {
    	    			//Cheaper to delete
    	    			costArray[i][j] = costArray[i-1][j].clone();
    	    			costArray[i][j].addEdits(deleteCost);
    	    		}
    	    	}
    	    	else if(costArray[i][j-1].size() + insertCost.size() < costArray[i-1][j].size() + deleteCost.size()) {
    	    		//Cheaper to insert
    	    		costArray[i][j] = costArray[i][j-1].clone();
    	    		costArray[i][j].addEdits(insertCost);
    	    	}
    	    	else {
    	    		//Cheaper to delete
    	    		costArray[i][j] = costArray[i-1][j].clone();
    	    		costArray[i][j].addEdits(deleteCost);
    	    	}
    	    }
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
