package utils;

import java.util.LinkedList;

public class Sum {
	
	public int sum = Integer.MAX_VALUE;
	public LinkedList<History> histories = new LinkedList<History>();
	
	// Does a deep copy.
	public Sum clone() {
		
		Sum retSum = new Sum();
		
		retSum.sum = this.sum;
		
		for (History hist : this.histories) {
			retSum.histories.add( hist.clone() );
		}
		
		return retSum;
	}
	
	public void remClearedHistories() {
		int histSize = histories.size();
		
		for (int i = histSize-1; i >= 0; i--) {
			if (histories.get(i).rcPairs.size() == 0) {
				histories.remove(i);
			}
		}
		
		checkNoZeroLengthHistories();
	}
	
	private void checkNoZeroLengthHistories() {
		for (History h : this.histories) {
			if (h.rcPairs.size() == 0) {
				System.out.println("empty history found");
				try { System.in.read(); } catch (Exception e) {}
			}
		}
	}
	
	public void addRCPairToHistories(RCPair p) {
		for (History h : histories) {
			h.rcPairs.add(p);
		}
	}
}
