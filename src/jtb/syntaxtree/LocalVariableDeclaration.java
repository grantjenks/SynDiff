//
// Generated by JTB 1.3.1
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> [ "final" ]
 * f1 -> Type()
 * f2 -> VariableDeclarator()
 * f3 -> ( "," VariableDeclarator() )*
 */
public class LocalVariableDeclaration implements Node {
   public NodeOptional f0;
   public Type f1;
   public VariableDeclarator f2;
   public NodeListOptional f3;

   public LocalVariableDeclaration(NodeOptional n0, Type n1, VariableDeclarator n2, NodeListOptional n3) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
