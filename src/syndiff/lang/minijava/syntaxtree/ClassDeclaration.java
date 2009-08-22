//
// Generated by JTB 1.3.3
//

package lang.minijava.syntaxtree;

/**
 * Grammar production:
 * f0 -> "class"
 * f1 -> Identifier()
 * f2 -> "{"
 * f3 -> ( Comment() )*
 * f4 -> ( VarDeclarationUnordered() )*
 * f5 -> ( MethodDeclarationUnordered() )*
 * f6 -> "}"
 */
import java.util.*;
import lang.syntaxtree.*;
public class ClassDeclaration implements Node {
   private Node parent;
   public NodeToken f0;
   public Identifier f1;
   public NodeToken f2;
   public NodeListOptional f3;
   public NodeListOptional f4;
   public NodeListOptional f5;
   public NodeToken f6;
   private ArrayList<Node> subNodes = new ArrayList<Node>();

   public ClassDeclaration(NodeToken n0, Identifier n1, NodeToken n2, NodeListOptional n3, NodeListOptional n4, NodeListOptional n5, NodeToken n6) {
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
      f5 = n5;
      if ( f5 != null ) f5.setParent(this);
      f6 = n6;
      if ( f6 != null ) f6.setParent(this);

      buildSubNodesList();
   }

   public ClassDeclaration(Identifier n0, NodeListOptional n1, NodeListOptional n2, NodeListOptional n3) {
      f0 = new NodeToken("class");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = new NodeToken("{");
      if ( f2 != null ) f2.setParent(this);
      f3 = n1;
      if ( f3 != null ) f3.setParent(this);
      f4 = n2;
      if ( f4 != null ) f4.setParent(this);
      f5 = n3;
      if ( f5 != null ) f5.setParent(this);
      f6 = new NodeToken("}");
      if ( f6 != null ) f6.setParent(this);

      buildSubNodesList();
   }
   private void buildSubNodesList() {
      subNodes.add(f0);
      subNodes.add(f1);
      subNodes.add(f2);
      subNodes.add(f3);
      subNodes.add(f4);
      subNodes.add(f5);
      subNodes.add(f6);
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
