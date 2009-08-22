package utils;

import java.util.LinkedList;
import java.util.Iterator;

public class History {
	
	public LinkedList<RCPair> rcPairs = new LinkedList<RCPair>();
	
	// Does a deep copy.
	public History clone() {
		
		History retHist = new History();
		
		for (RCPair pair : this.rcPairs) {
			retHist.rcPairs.add( pair.clone() );
		}
		
		return retHist;
	}
	
	// Does a deep compare.
	public boolean equals(History opp) {
		if (opp == null) return false;
		
		if (opp.rcPairs == null && this.rcPairs == null) return true;
		
		if (opp.rcPairs == null || this.rcPairs == null) return false;
		
		if (opp.rcPairs.size() != this.rcPairs.size()) return false;
		
		Iterator<RCPair> thisIter = this.rcPairs.iterator();
		Iterator<RCPair> oppIter = opp.rcPairs.iterator();
		
		while (thisIter.hasNext() && oppIter.hasNext()) {
			RCPair thisPair = thisIter.next();
			RCPair oppPair = oppIter.next();
			
			if (!thisPair.equals(oppPair)) return false;
		}
		
		if (thisIter.hasNext() == oppIter.hasNext()) return true;
		
		return false;
	}
}
