// SYNDIFF

import lang.syntaxtree.*;
import utils.*;
import alg.*;

public class Syndiff {

	public Syndiff(String[] args) {
		
		try {
			
			// Parse the command line arguments.
			boolean parseResult = Statics.parseCommandLineArguments(args);

			// If parsing failed then print error message and exit with error.
			if (parseResult == false) {
				System.out.println("Error occurred parsing command line arguments.");
				System.out.println(Statics.Usage());
				System.exit(-1);
			}
			
			// Create the lex/parse/tree build handler.
			TreeBuildHandler leftTree = new TreeBuildHandler(Statics.left);
			TreeBuildHandler rightTree = new TreeBuildHandler(Statics.right);
			
			// Lex, Parse, and Tree Build.
			leftTree.process();
			rightTree.process();
			
			// Get the left and right root nodes.
			Node leftRoot = leftTree.getRoot();
			Node rightRoot = rightTree.getRoot();
			
			// Create the edit list.
			EditList edits = new EditList();
			
			// Declare the differencer.
			Differencer differ;
			
			// Initialize the differencer.
			if (Statics.alg == Statics.Algorithm.Hybrid) {
				differ = new HybridDifferencer(leftRoot, rightRoot, edits);
			}
			else if (Statics.alg == Statics.Algorithm.Ordered) {
				differ = new OrderedDifferencer(leftRoot, rightRoot, edits);
			}
			else if (Statics.alg == Statics.Algorithm.Unordered) {
				differ = new UnorderedDifferencer(leftRoot, rightRoot, edits);
			}
			else if (Statics.alg == Statics.Algorithm.Trivial) {
				differ = new TrivialDifferencer(leftRoot, rightRoot, edits);
			}
			else if (Statics.alg == Statics.Algorithm.Simple) {
				differ = new SimpleDifferencer(leftRoot, rightRoot, edits);
			}
			else if (Statics.alg == Statics.Algorithm.Unified) {
				differ = new UnifiedDifferencer(leftRoot, rightRoot, edits);
			}
			else {
				throw new Exception("Failure occurred in choosing differencer.");
			}
			
			// Compute the difference.
			differ.compute();
			
			// Initialize the edit list printer.
			EditListPrinter elp = new EditListPrinter(edits, leftRoot, rightRoot);
			
			// Output the edit list.
			elp.print();
		}
		catch(Exception e) { // TODO this is debug right now
			// Signal an exception and print the stack trace.
			System.out.println("Exception occurred! Stack trace:");
			e.printStackTrace(System.err);
		}
	}

	public static void main(String[] args) {
		new Syndiff(args);
	}
}
