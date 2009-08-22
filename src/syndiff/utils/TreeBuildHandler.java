package utils;

import java.io.*;
import lang.syntaxtree.*;
import lang.minijava.*;
import lang.java.*;
import lang.python.*;
import visitors.OutputVisitor;

public class TreeBuildHandler {
	private Node root = null;
	private String fileName = null;
	
	public TreeBuildHandler(String file) {
		fileName = file;
	}
	
	public void process() throws IOException, 
			lang.minijava.ParseException, 
			lang.java.ParseException,
			lang.python.ParseException {
		
		// Open the file.
		FileInputStream fis = new FileInputStream(fileName);
		
		if (Statics.lang == Statics.Language.MiniJava) {
			// Lex/Parse/Tree Build.
			MiniJavaParser mjp = new MiniJavaParser(fis);
			
			// Set root.
			root = mjp.Goal();
		}
		else if (Statics.lang == Statics.Language.Java) {
			// Lex/Parse/Tree Build.
			JavaParser jp = new JavaParser(fis);
			
			// Set root.
			root = jp.CompilationUnit();
		}
		else if (Statics.lang == Statics.Language.Python) {
			// Lex/Parse/Tree Build.
			PythonParser pp = new PythonParser(fis);
			
			// Set root.
			root = pp.file_input();
		}
		
		// If debug is on, print tree using OutputVisitor
		if (Statics.debug)
			root.accept(new OutputVisitor()); 
	}
	
	public Node getRoot() {
		return root;
	}
}
