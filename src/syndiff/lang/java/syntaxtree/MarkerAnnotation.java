//
// Generated by JTB 1.3.3
//

package lang.java.syntaxtree;

/**
 * Grammar production:
 * f0 -> "@"
 * f1 -> Name()
 */
import java.util.*;
import lang.syntaxtree.*;
public class MarkerAnnotation implements Node {
   private Node parent;
   public NodeToken f0;
   public Name f1;
   private ArrayList<Node> subNodes = new ArrayList<Node>();

   public MarkerAnnotation(NodeToken n0, Name n1) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);

      buildSubNodesList();
   }

   public MarkerAnnotation(Name n0) {
      f0 = new NodeToken("@");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);

      buildSubNodesList();
   }
   private void buildSubNodesList() {
      subNodes.add(f0);
      subNodes.add(f1);
   }


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
   public void setSubNodes(ArrayList<Node> n) { subNodes = n; }
   public ArrayList<Node> getSubNodes() { return subNodes; }
}

