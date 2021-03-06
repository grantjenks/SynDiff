//
// Generated by JTB 1.3.3
//

package lang.java.syntaxtree;

/**
 * Grammar production:
 * f0 -> "{"
 * f1 -> MemberValue()
 * f2 -> MemberValueArrayInitializerList()
 * f3 -> [ "," ]
 * f4 -> "}"
 */
import java.util.*;
import lang.syntaxtree.*;
public class MemberValueArrayInitializer implements Node {
   private Node parent;
   public NodeToken f0;
   public MemberValue f1;
   public MemberValueArrayInitializerList f2;
   public NodeOptional f3;
   public NodeToken f4;
   private ArrayList<Node> subNodes = new ArrayList<Node>();

   public MemberValueArrayInitializer(NodeToken n0, MemberValue n1, MemberValueArrayInitializerList n2, NodeOptional n3, NodeToken n4) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
      f4 = n4;
      if ( f4 != null ) f4.setParent(this);

      buildSubNodesList();
   }

   public MemberValueArrayInitializer(MemberValue n0, MemberValueArrayInitializerList n1, NodeOptional n2) {
      f0 = new NodeToken("{");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = n1;
      if ( f2 != null ) f2.setParent(this);
      f3 = n2;
      if ( f3 != null ) f3.setParent(this);
      f4 = new NodeToken("}");
      if ( f4 != null ) f4.setParent(this);

      buildSubNodesList();
   }
   private void buildSubNodesList() {
      subNodes.add(f0);
      subNodes.add(f1);
      subNodes.add(f2);
      subNodes.add(f3);
      subNodes.add(f4);
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

