//
// Generated by JTB 1.3.3
//

package lang.minijava.syntaxtree;

/**
 * Grammar production:
 * f0 -> <SINGLE_LINE_COMMENT>
 *       | <FORMAL_COMMENT>
 *       | <MULTI_LINE_COMMENT>
 */
import java.util.*;
import lang.syntaxtree.*;
public class Comment implements Node {
   private Node parent;
   public NodeChoice f0;
   private ArrayList<Node> subNodes = new ArrayList<Node>();

   public Comment(NodeChoice n0) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);

      buildSubNodesList();
   }
   private void buildSubNodesList() {
      subNodes.add(f0);
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

