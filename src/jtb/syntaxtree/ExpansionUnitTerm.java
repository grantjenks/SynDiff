//
// Generated by JTB 1.3.1
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> RegularExpression()
 *       | <IDENTIFIER> Arguments()
 */
public class ExpansionUnitTerm implements Node {
   public NodeChoice f0;

   public ExpansionUnitTerm(NodeChoice n0) {
      f0 = n0;
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

