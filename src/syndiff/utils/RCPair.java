package utils;

public class RCPair {
	
	public int row = -1;
	public int col = -1;
	
	public RCPair(int r, int c) {
		row = r;
		col = c;
	}
	
	// Does a deep copy.
	public RCPair clone() {
		
		RCPair retPair = new RCPair(this.row, this.col);
		
		return retPair;
	}
	
	// Does a deep compare.
	public boolean equals(RCPair opp) {
		if (opp.row == this.row && opp.col == this.col) {
			return true;
		}
		else {
			return false;
		}
	}
}
