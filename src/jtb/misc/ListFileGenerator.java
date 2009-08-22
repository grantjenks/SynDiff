
package misc;

import java.util.*;
import java.io.*;

public class ListFileGenerator {
	
	public static final int INDENT_AMT = 3;

	private File visitorDir;

	public ListFileGenerator() {
		visitorDir = new File(Globals.visitorDir);

		if ( !visitorDir.exists() )
			visitorDir.mkdir();
		else if ( !visitorDir.isDirectory() )
			Errors.softErr("\"" + Globals.visitorDir + "\" exists but is not a " +
			"directory.");

	}

	public void generateListVisitors() throws FileExistsException {
		generateListVisitor();
		generateGJVoidListVisitor();
		generateGJListVisitor();
		generateGJNoArguListVisitor();
	}

	public void generateListVisitor() throws FileExistsException {
		try {
			File file = new File(visitorDir, "ListVisitor.java");

			if ( Globals.noOverwrite && file.exists() )
				throw new FileExistsException("ListVisitor.java");

			PrintWriter out = new PrintWriter(new FileOutputStream(file), false);
			
			Spacing spc = new Spacing(INDENT_AMT);

			out.println(Globals.fileHeader(spc));
			out.println();
			
			out.println(spc.spc + "package " + Globals.visitorPackage + ";");
			
			if ( !Globals.visitorPackage.equals(Globals.nodePackage) )
				out.println(spc.spc + "import " + Globals.nodePackage + ".*;");
			out.println(spc.spc + "import java.util.*;\n");
			out.println(spc.spc + "/**");
			out.println(spc.spc + " * All List Visitors with no argument must implement this interface.");
			out.println(spc.spc + " */\n");
			out.println(spc.spc + "public interface ListVisitor {\n");
			
			spc.updateSpc(+1);
			
			out.println(spc.spc + "public void visit(Node n);");

			spc.updateSpc(-1);
			out.println(spc.spc + "}\n");
			out.flush();
			out.close();
		}
		catch (IOException e) {
			Errors.hardErr(e);
		}
	}

	public void generateGJVoidListVisitor() throws FileExistsException {
		try {
			File file = new File(visitorDir, "GJVoidListVisitor.java");

			if ( Globals.noOverwrite && file.exists() )
				throw new FileExistsException("GJVoidListVisitor.java");

			PrintWriter out = new PrintWriter(new FileOutputStream(file), false);
			
			Spacing spc = new Spacing(INDENT_AMT);

			out.println(Globals.fileHeader(spc));
			out.println();
			
			out.println(spc.spc + "package " + Globals.visitorPackage + ";");
			
			if ( !Globals.visitorPackage.equals(Globals.nodePackage) )
				out.println(spc.spc + "import " + Globals.nodePackage + ".*;");
			out.println(spc.spc + "import java.util.*;\n");
			out.println(spc.spc + "/**");
			out.println(spc.spc + " * All GJVoid List Visitors with no argument must implement this interface.");
			out.println(spc.spc + " */\n");
			out.println(spc.spc + "public interface GJVoidListVisitor<A> {\n");
			
			spc.updateSpc(+1);
			
			out.println(spc.spc + "public void visit(Node n, A argu);");

			spc.updateSpc(-1);
			out.println(spc.spc + "}\n");
			out.flush();
			out.close();
		}
		catch (IOException e) {
			Errors.hardErr(e);
		}
	}

	public void generateGJListVisitor() throws FileExistsException {
		try {
			File file = new File(visitorDir, "GJListVisitor.java");

			if ( Globals.noOverwrite && file.exists() )
				throw new FileExistsException("GJListVisitor.java");

			PrintWriter out = new PrintWriter(new FileOutputStream(file), false);
			
			Spacing spc = new Spacing(INDENT_AMT);

			out.println(Globals.fileHeader(spc));
			out.println();
			
			out.println(spc.spc + "package " + Globals.visitorPackage + ";");
			
			if ( !Globals.visitorPackage.equals(Globals.nodePackage) )
				out.println(spc.spc + "import " + Globals.nodePackage + ".*;");
			out.println(spc.spc + "import java.util.*;\n");
			out.println(spc.spc + "/**");
			out.println(spc.spc + " * All GJ List Visitors with no argument must implement this interface.");
			out.println(spc.spc + " */\n");
			out.println(spc.spc + "public interface GJListVisitor<R,A> {\n");
			
			spc.updateSpc(+1);
			
			out.println(spc.spc + "public R visit(Node n, A argu);");

			spc.updateSpc(-1);
			out.println(spc.spc + "}\n");
			out.flush();
			out.close();
		}
		catch (IOException e) {
			Errors.hardErr(e);
		}
	}

	public void generateGJNoArguListVisitor() throws FileExistsException {
		try {
			File file = new File(visitorDir, "GJNoArguListVisitor.java");

			if ( Globals.noOverwrite && file.exists() )
				throw new FileExistsException("GJNoArguListVisitor.java");

			PrintWriter out = new PrintWriter(new FileOutputStream(file), false);
			
			Spacing spc = new Spacing(INDENT_AMT);

			out.println(Globals.fileHeader(spc));
			out.println();
			
			out.println(spc.spc + "package " + Globals.visitorPackage + ";");
			
			if ( !Globals.visitorPackage.equals(Globals.nodePackage) )
				out.println(spc.spc + "import " + Globals.nodePackage + ".*;");
			out.println(spc.spc + "import java.util.*;\n");
			out.println(spc.spc + "/**");
			out.println(spc.spc + " * All GJ No Argu List Visitors with no argument must implement this interface.");
			out.println(spc.spc + " */\n");
			out.println(spc.spc + "public interface GJNoArguListVisitor<R> {\n");
			
			spc.updateSpc(+1);
			
			out.println(spc.spc + "public R visit(Node n);");

			spc.updateSpc(-1);
			out.println(spc.spc + "}\n");
			out.flush();
			out.close();
		}
		catch (IOException e) {
			Errors.hardErr(e);
		}
	}
}
