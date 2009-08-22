package alg;

// This is the abstract class for all differencers.

// If there are common objects or operations for all
// differencers then it would be good to move those
// objects/operations here. One example of a move right
// now is the constructor and its objects. Notice that
// the differencer implementations all have identical
// implementations. This could be moved here.

public abstract class Differencer {
	
	
	public abstract void compute();
}
