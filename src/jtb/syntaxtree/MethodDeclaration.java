//
// Generated by JTB 1.3.1
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> ( "public" | "protected" | "private" | "static" | "abstract" | "final" | "native" | "synchronized" )*
 * f1 -> ResultType()
 * f2 -> MethodDeclarator()
 * f3 -> [ "throws" NameList() ]
 * f4 -> ( Block() | ";" )
 */
public class MethodDeclaration implements Node {
   public NodeListOptional f0;
   public ResultType f1;
   public MethodDeclarator f2;
   public NodeOptional f3;
   public NodeChoice f4;

   public MethodDeclaration(NodeListOptional n0, ResultType n1, MethodDeclarator n2, NodeOptional n3, NodeChoice n4) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
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

