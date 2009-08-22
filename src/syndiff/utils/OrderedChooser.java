package utils;

import utils.Sum;

public class OrderedChooser {
	public int[] dels;
	public int[] adds;
	public int[][] changes;
	public Sum[][] sums;
	public int addCount;
	public int delCount;

	public OrderedChooser(EditList[] delLists, EditList[] addLists, EditList[][] changeLists) {
		delCount = delLists.length;
		dels = new int[delCount];
		for (int i = 0; i < dels.length; i++) {
			dels[i] = delLists[i].size();
		}

		addCount = addLists.length;
		adds = new int[addCount];
		for (int i = 0; i < adds.length; i++) {
			adds[i] = addLists[i].size();
		}

		changes = new int[delCount][addCount];
		for (int i = 0; i < delCount; i++) {
			for (int j = 0; j < addCount; j++) {
				changes[i][j] = changeLists[i][j].size();
			}
		}

		sums = new Sum[delCount+1][addCount+1];
	}

	public void computeMinimumSum() {
		// Initialize first position.
		sums[0][0] = new Sum();
		sums[0][0].sum = 0;
		History h = new History();
		sums[0][0].histories.add(h);

		// Initialize first row.
		for (int i = 0; i < addCount; i++) {
			sums[0][i+1] = sums[0][i].clone();
			sums[0][i+1].sum = sums[0][i].sum + adds[i];
			sums[0][i+1].addRCPairToHistories(new RCPair(-1,i));
		}

		// Initialize first column.
		for (int i = 0; i < delCount; i++) {
			sums[i+1][0] = sums[i][0].clone();
			sums[i+1][0].sum = sums[i][0].sum + dels[i];
			sums[i+1][0].addRCPairToHistories(new RCPair(i,-1));
		}

		// Iterate through table.
		for (int i = 0; i < delCount; i++) {
			for (int j = 0; j < addCount; j++) {
				int sumup;
				int sumlt;
				int sumdi;
				
				sumdi = sums[i][j].sum + changes[i][j];
				sumup = sums[i][j+1].sum + dels[i];
				sumlt = sums[i+1][j].sum + adds[j];
				
				if (sumdi <= sumup && sumdi <= sumlt) {
					sums[i+1][j+1] = sums[i][j].clone();
					sums[i+1][j+1].sum = sumdi;
					sums[i+1][j+1].addRCPairToHistories(new RCPair(i,j));
				}
				else if (sumup <= sumdi && sumup <= sumlt) {
					sums[i+1][j+1] = sums[i][j+1].clone();
					sums[i+1][j+1].sum = sumup;
					sums[i+1][j+1].addRCPairToHistories(new RCPair(i, -1));
				}
				else if (sumlt <= sumdi && sumlt <= sumup) {
					sums[i+1][j+1] = sums[i+1][j].clone();
					sums[i+1][j+1].sum = sumlt;
					sums[i+1][j+1].addRCPairToHistories(new RCPair(-1, j));
				}
			}
		}
	}
	
	public History getResult() {
		return sums[delCount][addCount].histories.get(0);
	}
}

/* Here's how ordered chooser works.
 * Given a change table: C
 * A delete vector: D
 * An add vector: A
 * 
 * Consider C, D, and A together:
 * 
 *  Add vector:  a b c d e f
 *  
 *  Change table:
 *  q r s t u v
 *  w x y z k l
 *  m n o p q x
 *  y z 1 2 3 4
 *  
 *  Delete vector: g h i j
 * 
 * Dynamic Programming
 * 
 * Create second table:
 * 
 * 0 1 2 3 4 5 6
 * 7 b c d e f g
 * 8 h i j k l m
 * 9 n o p q r s
 * a t u v w x y
 * 
 * Initialize:
 * 0 = 0
 * 1 = a
 * 2 = a + b
 * 3 = a + b + c
 * ...
 * 7 = g
 * 8 = g + h
 * 9 = g + h + i
 * ...
 * 
 * Fill in:
 * b = 1 + 7 | q + 0
 * c = 2 + b | r + 1
 * ...
 * s(ij) = s(i-1,j-1) + c(i,j) | s(i-1,j) + d(i) | s(i,j-1) + a(j)
 * 
 * To compute the minimum ordered difference
 * perform a depth first search through the
 * table. Consider a grid around the change
 * table that starts at position 0,0
 * 
 * Recursive DFS:
 * at position: i, j
 * Three options:
 * 1) Move diagonally, add C[i,j], i++, j++
 * 2) Move right, add A[j], j++
 * 3) Move down, add D[i], i++
 * 
 * Calculate on demand!
 * 
 * Init:
 * Del vector: z z z
 * Add vector: z z z z z
 * Change table:
 * z z z z z
 * z z z z z
 * z z z z z
 * 
 */
