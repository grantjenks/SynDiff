//
// Generated by JTB 1.3.1
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> [ LexicalStateList() ]
 * f1 -> RegExprKind()
 * f2 -> [ <LBRACKET> <IGNORE_CASE_TK> <RBRACKET> ]
 * f3 -> <COLON>
 * f4 -> <LBRACE>
 * f5 -> RegExprSpec()
 * f6 -> ( <BIT_OR> RegExprSpec() )*
 * f7 -> <RBRACE>
 */
public class RegularExprProduction implements Node {
   public NodeOptional f0;
   public RegExprKind f1;
   public NodeOptional f2;
   public NodeToken f3;
   public NodeToken f4;
   public RegExprSpec f5;
   public NodeListOptional f6;
   public NodeToken f7;

   public RegularExprProduction(NodeOptional n0, RegExprKind n1, NodeOptional n2, NodeToken n3, NodeToken n4, RegExprSpec n5, NodeListOptional n6, NodeToken n7) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
      f7 = n7;
   }

   public RegularExprProduction(NodeOptional n0, RegExprKind n1, NodeOptional n2, RegExprSpec n3, NodeListOptional n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = new NodeToken(":");
      f4 = new NodeToken("{");
      f5 = n3;
      f6 = n4;
      f7 = new NodeToken("}");
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
