package utils;

import java.util.HashSet;

public class UnorderedChooser {

	private int INF = Integer.MAX_VALUE;
	private Sum INFSum = new Sum();

	private int rowMax;
	private int colMax;

	private int[][] sizes;
	private Sum[][] sums;

	public UnorderedChooser(EditList[][] table) {
		INFSum.sum = INF;

		rowMax = table.length;
		colMax = table[0].length;

		sizes = new int[rowMax][colMax];
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				sizes[i][j] = table[i][j].size();
			}
		}

		sums = new Sum[rowMax][colMax];
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				sums[i][j] = null;
			}
		}
	}

	public void computeMinimumSum() {
		
		// Create diagonal history.
		History diagonal = new History();
		for (int i = 0; i < rowMax; i++) {
			diagonal.rcPairs.add(new RCPair(i, i));
		}
		
		// Shortcut 1: The sizes on the diagonal are 0.
		boolean allzero = true;
		for (int i = 0; i < rowMax; i++) {
			if (sizes[i][i] != 0) {
				allzero = false;
				break;
			}
		}
		if (allzero) {
			sums[rowMax-1][colMax-1] = new Sum();
			sums[rowMax-1][colMax-1].histories.add(diagonal);
			return ;
		}
		
		// Shortcut 2: The sizes in each column are identical.
		boolean identical = true;
		for (int i = 0; i < rowMax; i++) {
			int first = sizes[i][0];
			for (int j = 1; j < colMax; j++) {
				if (sizes[i][j] != first) {
					identical = false;
					break;
				}
			}
		}
		if (identical) {
			sums[rowMax-1][colMax-1] = new Sum();
			sums[rowMax-1][colMax-1].histories.add(diagonal);
			return ;
		}
		
		// Shortcut 3: The sizes in each row are identical.
		identical = true;
		for (int j = 0; j < colMax; j++) {
			int first = sizes[0][j];
			for (int i = 1; i < rowMax; i++) {
				if (sizes[i][j] != first) {
					identical = false;
					break;
				}
			}
		}
		if (identical) {
			sums[rowMax-1][colMax-1] = new Sum();
			sums[rowMax-1][colMax-1].histories.add(diagonal);
			return ;
		}
		
		// This is the "beefy" algorithm.
		
		// Set first position.
		sums[0][0] = new Sum();
		sums[0][0].sum = sizes[0][0];
		History zerozero = new History();
		zerozero.rcPairs.add(new RCPair(0, 0));
		sums[0][0].histories.add(zerozero);

		// Compute first row.
		for (int i = 1; i < colMax; i++) {
			int sizeAtI = sizes[0][i];

			if (sizeAtI < sums[0][i-1].sum) {
				// New sum w/ new history.
				sums[0][i] = new Sum();
				sums[0][i].sum = sizeAtI;

				History newHist = new History();
				newHist.rcPairs.add(new RCPair(0, i));
				sums[0][i].histories.add(newHist);
			}
			else if (sizeAtI == sums[0][i-1].sum) {
				// Copied sum w/ new history.
				sums[0][i] = sums[0][i-1].clone();

				History newHist = new History();
				newHist.rcPairs.add(new RCPair(0, i));
				sums[0][i].histories.add(newHist);
			}
			else if (sizeAtI > sums[0][i-1].sum) {
				// Point to previous.
				sums[0][i] = sums[0][i-1].clone();
			}
			else {
				// Should never get here.
			}
		}

		// Compute first column.
		for (int j = 1; j < rowMax; j++) {
			int sizeAtJ = sizes[j][0];

			if (sizeAtJ < sums[j-1][0].sum) {
				// New sum w/ new history.
				sums[j][0] = new Sum();
				sums[j][0].sum = sizeAtJ;

				History newHist = new History();
				newHist.rcPairs.add(new RCPair(j, 0));
				sums[j][0].histories.add(newHist);
			}
			else if (sizeAtJ == sums[j-1][0].sum) {
				// Copied sum w/ new history.
				sums[j][0] = sums[j-1][0].clone();

				History newHist = new History();
				newHist.rcPairs.add(new RCPair(j, 0));
				sums[j][0].histories.add(newHist);
			}
			else if (sizeAtJ > sums[j-1][0].sum) {
				// Point to previous.
				sums[j][0] = sums[j-1][0].clone();
			}
			else {
				// Should never get here.
			}
		}
		
		if (Statics.debug) { validateFirstRowAndColumn(); }

		// Compute inner sums.
		for (int i = 1; i < rowMax; i++) {
			for (int j = 1; j < colMax; j++) {
				Sum sumOpts[];
				
				if (i == j) {
					sumOpts = new Sum[1 + (i+1) + (j+1)];
					sumOpts[0] = SumOptFrPlusTatTo(i-1, j-1, i, j);
					for (int m = 0; m <= i; m++) {
						sumOpts[1+m] = SumOptFrPlusTatTo(i, j-1, i-m, j);
					}
					for (int m = 0; m <= j; m++) {
						sumOpts[1+(i+1)+m] = SumOptFrPlusTatTo(i-1, j, i, j-m);
					}
				}
				else if (i < j) {
					sumOpts = new Sum[1 + (j+1) + 1];
					sumOpts[0] = SumOptFrPlusTatTo(i-1, j-1, i, j);
					sumOpts[1] = sums[i][j-1].clone();
					for (int m = 0; m <= j; m++) {
						sumOpts[2+m] = SumOptFrPlusTatTo(i-1, j, i, j-m);
					}
				}
				else { // j < i
					sumOpts = new Sum[1 + (i+1) + 1];
					sumOpts[0] = SumOptFrPlusTatTo(i-1, j-1, i, j);
					sumOpts[1] = sums[i-1][j].clone();
					for (int m = 0; m <= i; m++) {
						sumOpts[2+m] = SumOptFrPlusTatTo(i, j-1, i-m, j);
					}
				}
				
				if (Statics.debug) { validateSumOpts(sumOpts, i, j); }

				// Find minimum sum.
				int minSum = sumOpts[0].sum;
				for (int m = 1; m < sumOpts.length; m++) {
					if (sumOpts[m].sum < minSum) {
						minSum = sumOpts[m].sum;
					}
				}

				// Combine histories of new minimum sum.
				sums[i][j] = new Sum();
				sums[i][j].sum = minSum;
				for (int m = 0; m < sumOpts.length; m++) {
					if (sumOpts[m].sum == minSum) {
						// System.out.print(m + " ");
						for (History ho : sumOpts[m].histories) {
							boolean add = true;
							for (History hi : sums[i][j].histories) {
								if (ho.equals(hi)) {
									add = false;
									break;
								}
							}
							if (add) {
								sums[i][j].histories.add(ho);
							}
						}
					}
				}
				if (Statics.debug && !check(sums[i][j], i, j)) {
					System.err.println("at i: " + i + "and j: " + j);
					dumpSizes();
					dumpSums();
					dumpSum(sums[i][j]);
					System.out.println("check failed, continue?");
					try {
						System.in.read();
					} catch (Exception e) {}
				}
			}
		}
	}

	private Sum SumOptFrPlusTatTo(int li, int lj, int ri, int rj) {
		// Clone the sum.
		Sum retSum = sums[li][lj].clone();

		for (History h : retSum.histories) {
			boolean found = false;
			for (RCPair p : h.rcPairs) {
				if (p.row == ri || p.col == rj) {
					found = true;
					break;
				}
			}
			if (found) {
				h.rcPairs.clear();
			}
		}

		// Remove each history that is cleared.
		retSum.remClearedHistories();

		// If no histories remain then return INFSum
		// else add new RCPair with added sum
		if (retSum.histories.size() == 0) {
			return INFSum;
		}
		else {
			retSum.addRCPairToHistories(new RCPair(ri, rj));
			retSum.sum += sizes[ri][rj];
			// System.out.println("Returning sum: ");
			// dumpSum(retSum);
			return retSum;
		}
	}

	public History getResult() {
		return sums[rowMax-1][colMax-1].histories.get(0);
	}
	
	private void validateSumOpts(Sum[] sumOpts, int i, int j) {
		int min = (i<j?i:j) + 1;
		for (int m = 0; m < sumOpts.length; m++) {
			for (History h : sumOpts[m].histories) {
				if (h.rcPairs.size() != min) {
					System.err.println("Error in history length for sumOpt: " + m);
				}
			}
		}
	}
	
	private void validateFirstRowAndColumn() {
		for (int i = 0; i < rowMax; i++) {
			if (sums[i][0] == null) {
				System.out.println("Error in first row. null");
			}
			for (History h : sums[i][0].histories) {
				if (h.rcPairs.size() != 1) {
					System.out.println("Error in first row. size != 1");
				}
			}
		}
		for (int j = 0; j < colMax; j++) {
			if (sums[0][j] == null) {
				System.out.println("Error in first col. null");
			}
			
			for (History h : sums[0][j].histories) {
				if (h.rcPairs.size() != 1) {
					System.out.println("Error in first col. size != 1");
				}
			}
		}
	}

	private void dumpSum(Sum s) {
		System.err.println("Printing sum histories: " + s.sum);
		for (History h : s.histories) {
			System.out.print("history: ");
			for (RCPair p : h.rcPairs) {
				System.out.print("r:" + p.row + " c:" + p.col + " ");
			}
			System.out.println();
		}
	}

	private boolean check(Sum s, int i, int j) {
		int min = (i<j?i:j) + 1;
		for (History h : s.histories) {
			HashSet<Integer> rows = new HashSet<Integer>();
			HashSet<Integer> cols = new HashSet<Integer>();

			if (h.rcPairs.size() != min) {
				System.out.println("size mismatch in check: " + h.rcPairs.size() + ":" + min);
				return false;
			}

			for (RCPair p : h.rcPairs) {
				if (rows.contains(p.row)) {
					System.out.println("duplicated row");
					return false;
				}
				rows.add(p.row);

				if (cols.contains(p.col)) {
					System.out.println("duplicated col");
					return false;
				}
				cols.add(p.col);
			}
		}
		return true;
	}

	private void dumpHistory(History h, String s) {
		System.out.print(s + "History: ");
		for (RCPair p : h.rcPairs) {
			System.out.print("r:"+p.row+" c:" + p.col + " ");
		}
		System.out.println();
	}

	private void dumpSizes() {
		System.out.println("Sizes:");
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				System.out.print(sizes[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private void dumpSums() {
		System.out.println("Sums:");
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				String s;
				if (sums[i][j] == null) {
					s = "null";
				}
				else {
					s = "" + sums[i][j].sum;
				}
				System.out.print(s + "\t");
			}
			System.out.println();
		}
	}
}

/*              ...
 *              z2
 *              z1
 *           x  z0
 * ... y2 y1 y0 aij
 * 
 * 3 cases
 * 1) i == j, 1 + i + j options
 * no check
 * x + T[aij]
 * check overlap
 * y0 + T[z0]
 * y0 + T[z1]
 * y0 + T[z2]
 * ...
 * z0 + T[y0]
 * z0 + T[y1]
 * z0 + T[y2]
 * ...
 * y0 + T[aij]
 * z0 + T[aij]
 * 
 * 2) i < j, 1 + j + 1 options
 * x + T[aij]
 * check overlap
 * z0 + T[y0]
 * z0 + T[y1]
 * z0 + T[y2]
 * ...
 * z0 + T[aij]
 * y0
 * 
 * 3) i > j, 1 + i options
 * x + T[aij]
 * check overlap
 * y0 + T[z0]
 * y0 + T[z1]
 * y0 + T[z2]
 * ...
 * y0 + T[aij]
 * z0
 */





















