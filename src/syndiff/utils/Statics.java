package utils;

import java.util.Enumeration;
import java.util.Hashtable;

import lang.syntaxtree.Node;
import lang.syntaxtree.NodeToken;

public class Statics {

	// Constant names.
	private static final String progName 	= "Syndiff";
	private static final String version 	= "0.1";
	private static final String authors 	= "Grant Jenks, Robert Nakamoto, Jason Stoops";
	private static final String email 		= "syndiff@gmail.com";
	private static final String usage		=
		progName + " " + version + "\n" +
		"Authors: " + authors + "\n" +
		"Email: " + email + "\n" +
		"\n" +
		"Usage:\n" +
		"  java -jar syndiff.jar [SWITCHES] PATH_TO_FILE_1 PATH_TO_FILE_2\n" +
		"\n" +
		"[SWITCHES] are name/value pairs that may be any of the following:\n" +
		"  NAME=(value1|value2|...) - DESCRIPTION\n" +
		"  debug=(on|off) - Prints debug information. Is optional. Defaults to off.\n" +
		"  verbose=(on|off) - Prints verbose status messages. Is optional. Defaults to off.\n" +
		"  process=(on|off) - Prints process indicator. Is optional. Defaults to off.\n" +
		"  lang=(mj|j|p) - Specifies grammar of files. Is required.\n" +
		"  alg=(h|u|o|s|t|f) - Specifies algorithm to use. Is required.\n" +
		"  view=(a|s|v) - Specifies how the differences will be viewed.\n" +
		"\n" +
		"Example:\n" +
		"  java -jar syndiff.jar lang=mj alg=h debug=on First.java Second.java\n";

	// Constant name accessors.
	public static String ProgName() 	{ return progName; 	}
	public static String Version() 		{ return version; 	}
	public static String Authors() 		{ return authors; 	}
	public static String Email() 		{ return email; 	}
	public static String Usage()		{ return usage;		}

	// Changeable flags.
	public static boolean 		debug 		= false;
	public static boolean 		verbose 	= false;
	public static boolean 		process 	= false;

	// Changeable parameters.
	public static Language		lang 		= null;
	public static Algorithm 	alg 		= null;
	public static String		left		= null;
	public static String 		right		= null;
	public static ViewType		view		= ViewType.All;

	// Enumerated types.
	public static enum Language 	{ MiniJava, Java, Python };
	public static enum Algorithm 	{ Hybrid, Ordered, Unordered, Trivial, Simple, Unified };
	public static enum EditType		{ Add, Delete, Change };
	public static enum ViewType		{ All, StringOnly, Viewer };
	
	// Compare two nodes for equality.
	public static boolean compareNodes(Node left, Node right){
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
					return false;
				} else {
					return true;
				}	
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	
	// Dumps all the global values.
	public static void dumpGlobals() {
		System.err.println("debug flag:\t" + debug);
		System.err.println("verbose flag:\t" + verbose);
		System.err.println("process flag:\t" + process);
		System.err.print("lang value:\t");
		if (lang == null) {
			System.err.println("NULL");
		}
		else {
			System.err.println(lang);
		}
		System.err.print("alg value:\t");
		if (alg == null) {
			System.err.println("NULL");
		}
		else {
			System.err.println(alg);
		}
		System.err.println("left file name:\t" + (left == null?"NULL":left));
		System.err.println("right file name:\t" + (right == null?"NULL":right));
	}

	// Sets command line parameters in Globals object.
	public static boolean parseCommandLineArguments(String[] args) {

		// Argument count check.
		if (args.length < 2) {
			System.out.println("Error: There must be at least 2 arguments to Syndiff.");
			return false;
		}

		// Calculate file name indexes.
		int leftFileIndex 	= args.length - 2;
		int rightFileIndex 	= args.length - 1;

		// Get the file names.
		String leftFileName 	= args[leftFileIndex];
		String rightFileName 	= args[rightFileIndex];

		// Warn the user about "=" in a file name.
		if (leftFileName.contains("=")) {
			System.out.println("Warning: Does the left file name really contain an equal sign?");
		}
		if (rightFileName.contains("=")) {
			System.out.println("Warning: Does the right file name really contain an equal sign?");
		}

		// Set the file names globally.
		Statics.left 	= leftFileName;
		Statics.right 	= rightFileName;

		// Hashtable for switches.
		Hashtable<String, String> switches = new Hashtable<String, String>();

		for (int i = 0; i < args.length - 2; i++) {
			// Get the switch statement.
			String switchStmt = args[i];

			// Calculate index of equals sign.
			int indexOfEquals = switchStmt.indexOf("=");

			// All switch statements must contain equal signs.
			if (indexOfEquals == -1) {
				System.out.println("Error: Switch \"" + switchStmt + "\" must contain an equal sign.");
				return false;
			}

			// Get the name/value pair.
			String switchName 	= switchStmt.substring(0,indexOfEquals);
			String switchValue 	= switchStmt.substring(indexOfEquals + 1);

			// Error if switches are redundant.
			if (switches.containsKey(switchName)) {
				System.out.println("Error: Switch, \"" + switchName + "\", specified twice at command line.");
			}

			// Add switch name/value pair to dictionary.
			switches.put(switchName, switchValue);
		}

		Enumeration<String> switchKeys = switches.keys();
		while (switchKeys.hasMoreElements()) {
			String switchKey = switchKeys.nextElement();
			// Process "debug" switch.
			if (switchKey.equals("debug")) {
				String switchValue = switches.get("debug");
				if (switchValue.equals("on")) {
					Statics.debug = true;
				}
				else if (switchValue.equals("off")) {
					Statics.debug = false;
				}
				else {
					System.out.println("Error: Unrecognized debug switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			// Process "verbose" switch.
			else if (switchKey.equals("verbose")) {
				String switchValue = switches.get("verbose");
				if (switchValue.equals("on")) {
					Statics.verbose = true;
				}
				else if (switchValue.equals("off")) {
					Statics.verbose = false;
				}
				else {
					System.out.println("Error: Unrecognized verbose switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			// Process "process" switch.
			else if (switchKey.equals("process")) {
				String switchValue = switches.get("process");
				if (switchValue.equals("on")) {
					Statics.process = true;
				}
				else if (switchValue.equals("off")) {
					Statics.process = false;
				}
				else {
					System.out.println("Error: Unrecognized process switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			// Process "alg" switch.
			else if (switchKey.equals("alg")) {
				String switchValue = switches.get("alg");
				if (switchValue.equals("h")) {
					Statics.alg = Statics.Algorithm.Hybrid;
				}
				else if (switchValue.equals("u")) {
					Statics.alg = Statics.Algorithm.Unordered;
				}
				else if (switchValue.equals("o")) {
					Statics.alg = Statics.Algorithm.Ordered;
				}
				else if (switchValue.equals("t")) {
					Statics.alg = Statics.Algorithm.Trivial;
				}
				else if (switchValue.equals("s")) {
					Statics.alg = Statics.Algorithm.Simple;
				}
				else if (switchValue.equals("f")) {
					Statics.alg = Statics.Algorithm.Unified;
				}
				else {
					System.out.println("Error: Unrecognized alg switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			// Process "lang" switch.
			else if (switchKey.equals("lang")) {
				String switchValue = switches.get("lang");
				if (switchValue.equals("mj")) {
					Statics.lang = Statics.Language.MiniJava;
				}
				else if (switchValue.equals("j")) {
					Statics.lang = Statics.Language.Java;
				}
				else if (switchValue.equals("p")) {
					Statics.lang = Statics.Language.Python;
				}
				else {
					System.out.println("Error: Unrecognized lang switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			else if (switchKey.equals("view")) {
				String switchValue = switches.get("view");
				if (switchValue.equals("a")) {
					Statics.view = Statics.ViewType.All;
				}
				else if (switchValue.equals("s")) {
					Statics.view = Statics.ViewType.StringOnly;
				}
				else if (switchValue.equals("v")) {
					Statics.view = Statics.ViewType.Viewer;
				}
				else {
					System.out.println("Error: Unrecognized view switch value: \"" + switchValue + "\"");
					return false;
				}
			}
			else {
				System.out.println("Error: Unrecognized switch key: \"" + switchKey + "\"");
				return false;
			}
		}

		// Make sure "alg" and "lang" switches were present.
		if (!switches.containsKey("alg")) {
			System.out.println("Error: \"alg\" switch must be present on comand line.");
			return false;
		}
		if (!switches.containsKey("lang")) {
			System.out.println("Error: \"lang\" switch must be present on comand line.");
			return false;
		}

		// If everything was parsed successfully then return.
		return true;
	}
}
