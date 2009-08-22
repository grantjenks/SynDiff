//
// Generated by JTB 1.3.1
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> "null"
 */
public class NullLiteral implements Node {
   public NodeToken f0;

   public NullLiteral(NodeToken n0) {
      f0 = n0;
   }

   public NullLiteral() {
      f0 = new NodeToken("null");
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

