//
// Generated by JTB 1.3.3
//

package lang.syntaxtree;

import java.util.*;

/**
 * Represents a sequence of nodes nested within a choice, list,
 * optional list, or optional, e.g. ( A B )+ or [ C D E ]
 */
public class NodeSequence implements NodeListInterface {
   public NodeSequence(int n) {
      nodes = new Vector<Node>(n);
   }

   public NodeSequence(Node firstNode) {
      nodes = new Vector<Node>();
      addNode(firstNode);
   }

   public void addNode(Node n) {
      nodes.addElement(n);
      n.setParent(this);
      subNodes.add(n);
   }

   public Node elementAt(int i)  { return nodes.elementAt(i); }
   public Enumeration<Node> elements() { return nodes.elements(); }
   public int size()             { return nodes.size(); }
   public void accept(lang.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(lang.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(lang.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(lang.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }

   public void accept(lang.visitor.ListVisitor v) {
      v.visit(this);
   }
   public <R,A> R accept(lang.visitor.GJListVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(lang.visitor.GJNoArguListVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(lang.visitor.GJVoidListVisitor<A> v, A argu) {
      v.visit(this,argu);
   }

   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }

   private Node parent;
   public void setSubNodes(ArrayList<Node> n) { subNodes = n; }
   public ArrayList<Node> getSubNodes() { return subNodes; }
   private ArrayList<Node> subNodes = new ArrayList<Node>();
   public Vector<Node> nodes;
}
