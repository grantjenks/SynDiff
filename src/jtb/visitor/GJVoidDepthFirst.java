/**
 * Copyright (c) 2004,2005 UCLA Compilers Group. 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 *  Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 * 
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 * 
 *  Neither UCLA nor the names of its contributors may be used to endorse 
 *  or promote products derived from this software without specific prior 
 *  written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/

//
// Generated by JTB 1.3.1
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJVoidDepthFirst<A> implements GJVoidVisitor<A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public void visit(NodeList n, A argu) {
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
   }

   public void visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
      }
   }

   public void visit(NodeOptional n, A argu) {
      if ( n.present() )
         n.node.accept(this,argu);
   }

   public void visit(NodeSequence n, A argu) {
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
   }

   public void visit(NodeToken n, A argu) {}

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> [ PackageDeclaration() ]
    * f1 -> ( ImportDeclaration() )*
    * f2 -> ( TypeDeclaration() )*
    */
   public void visit(CompilationUnit n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "package"
    * f1 -> Name()
    * f2 -> ";"
    */
   public void visit(PackageDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "import"
    * f1 -> Name()
    * f2 -> [ "." "*" ]
    * f3 -> ";"
    */
   public void visit(ImportDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> ClassDeclaration()
    *       | InterfaceDeclaration()
    *       | ";"
    */
   public void visit(TypeDeclaration n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ( "abstract" | "final" | "public" )*
    * f1 -> UnmodifiedClassDeclaration()
    */
   public void visit(ClassDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "class"
    * f1 -> <IDENTIFIER>
    * f2 -> [ "extends" Name() ]
    * f3 -> [ "implements" NameList() ]
    * f4 -> ClassBody()
    */
   public void visit(UnmodifiedClassDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> "{"
    * f1 -> ( ClassBodyDeclaration() )*
    * f2 -> "}"
    */
   public void visit(ClassBody n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> ( "static" | "abstract" | "final" | "public" | "protected" | "private" )*
    * f1 -> UnmodifiedClassDeclaration()
    */
   public void visit(NestedClassDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> Initializer()
    *       | NestedClassDeclaration()
    *       | NestedInterfaceDeclaration()
    *       | ConstructorDeclaration()
    *       | MethodDeclaration()
    *       | FieldDeclaration()
    */
   public void visit(ClassBodyDeclaration n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ( "public" | "protected" | "private" | "static" | "abstract" | "final" | "native" | "synchronized" )*
    * f1 -> ResultType()
    * f2 -> <IDENTIFIER>
    * f3 -> "("
    */
   public void visit(MethodDeclarationLookahead n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> ( "abstract" | "public" )*
    * f1 -> UnmodifiedInterfaceDeclaration()
    */
   public void visit(InterfaceDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( "static" | "abstract" | "final" | "public" | "protected" | "private" )*
    * f1 -> UnmodifiedInterfaceDeclaration()
    */
   public void visit(NestedInterfaceDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "interface"
    * f1 -> <IDENTIFIER>
    * f2 -> [ "extends" NameList() ]
    * f3 -> "{"
    * f4 -> ( InterfaceMemberDeclaration() )*
    * f5 -> "}"
    */
   public void visit(UnmodifiedInterfaceDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
   }

   /**
    * f0 -> NestedClassDeclaration()
    *       | NestedInterfaceDeclaration()
    *       | MethodDeclaration()
    *       | FieldDeclaration()
    */
   public void visit(InterfaceMemberDeclaration n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ( "public" | "protected" | "private" | "static" | "final" | "transient" | "volatile" )*
    * f1 -> Type()
    * f2 -> VariableDeclarator()
    * f3 -> ( "," VariableDeclarator() )*
    * f4 -> ";"
    */
   public void visit(FieldDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> VariableDeclaratorId()
    * f1 -> [ "=" VariableInitializer() ]
    */
   public void visit(VariableDeclarator n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> <IDENTIFIER>
    * f1 -> ( "[" "]" )*
    */
   public void visit(VariableDeclaratorId n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ArrayInitializer()
    *       | Expression()
    */
   public void visit(VariableInitializer n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "{"
    * f1 -> [ VariableInitializer() ( "," VariableInitializer() )* ]
    * f2 -> [ "," ]
    * f3 -> "}"
    */
   public void visit(ArrayInitializer n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> ( "public" | "protected" | "private" | "static" | "abstract" | "final" | "native" | "synchronized" )*
    * f1 -> ResultType()
    * f2 -> MethodDeclarator()
    * f3 -> [ "throws" NameList() ]
    * f4 -> ( Block() | ";" )
    */
   public void visit(MethodDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> <IDENTIFIER>
    * f1 -> FormalParameters()
    * f2 -> ( "[" "]" )*
    */
   public void visit(MethodDeclarator n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "("
    * f1 -> [ FormalParameter() ( "," FormalParameter() )* ]
    * f2 -> ")"
    */
   public void visit(FormalParameters n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> [ "final" ]
    * f1 -> Type()
    * f2 -> VariableDeclaratorId()
    */
   public void visit(FormalParameter n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> [ "public" | "protected" | "private" ]
    * f1 -> <IDENTIFIER>
    * f2 -> FormalParameters()
    * f3 -> [ "throws" NameList() ]
    * f4 -> "{"
    * f5 -> [ ExplicitConstructorInvocation() ]
    * f6 -> ( BlockStatement() )*
    * f7 -> "}"
    */
   public void visit(ConstructorDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
   }

   /**
    * f0 -> "this" Arguments() ";"
    *       | [ PrimaryExpression() "." ] "super" Arguments() ";"
    */
   public void visit(ExplicitConstructorInvocation n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> [ "static" ]
    * f1 -> Block()
    */
   public void visit(Initializer n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( PrimitiveType() | Name() )
    * f1 -> ( "[" "]" )*
    */
   public void visit(Type n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "boolean"
    *       | "char"
    *       | "byte"
    *       | "short"
    *       | "int"
    *       | "long"
    *       | "float"
    *       | "double"
    */
   public void visit(PrimitiveType n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "void"
    *       | Type()
    */
   public void visit(ResultType n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <IDENTIFIER>
    * f1 -> ( "." <IDENTIFIER> )*
    */
   public void visit(Name n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> Name()
    * f1 -> ( "," Name() )*
    */
   public void visit(NameList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> Assignment()
    *       | ConditionalExpression()
    */
   public void visit(Expression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> AssignmentOperator()
    * f2 -> Expression()
    */
   public void visit(Assignment n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "="
    *       | "*="
    *       | "/="
    *       | "%="
    *       | "+="
    *       | "-="
    *       | "<<="
    *       | ">>="
    *       | ">>>="
    *       | "&="
    *       | "^="
    *       | "|="
    */
   public void visit(AssignmentOperator n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ConditionalOrExpression()
    * f1 -> [ "?" Expression() ":" ConditionalExpression() ]
    */
   public void visit(ConditionalExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ConditionalAndExpression()
    * f1 -> ( "||" ConditionalAndExpression() )*
    */
   public void visit(ConditionalOrExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> InclusiveOrExpression()
    * f1 -> ( "&&" InclusiveOrExpression() )*
    */
   public void visit(ConditionalAndExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ExclusiveOrExpression()
    * f1 -> ( "|" ExclusiveOrExpression() )*
    */
   public void visit(InclusiveOrExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> AndExpression()
    * f1 -> ( "^" AndExpression() )*
    */
   public void visit(ExclusiveOrExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> EqualityExpression()
    * f1 -> ( "&" EqualityExpression() )*
    */
   public void visit(AndExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> InstanceOfExpression()
    * f1 -> ( ( "==" | "!=" ) InstanceOfExpression() )*
    */
   public void visit(EqualityExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> RelationalExpression()
    * f1 -> [ "instanceof" Type() ]
    */
   public void visit(InstanceOfExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ShiftExpression()
    * f1 -> ( ( "<" | ">" | "<=" | ">=" ) ShiftExpression() )*
    */
   public void visit(RelationalExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> AdditiveExpression()
    * f1 -> ( ( "<<" | ">>" | ">>>" ) AdditiveExpression() )*
    */
   public void visit(ShiftExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> MultiplicativeExpression()
    * f1 -> ( ( "+" | "-" ) MultiplicativeExpression() )*
    */
   public void visit(AdditiveExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> UnaryExpression()
    * f1 -> ( ( "*" | "/" | "%" ) UnaryExpression() )*
    */
   public void visit(MultiplicativeExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( "+" | "-" ) UnaryExpression()
    *       | PreIncrementExpression()
    *       | PreDecrementExpression()
    *       | UnaryExpressionNotPlusMinus()
    */
   public void visit(UnaryExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "++"
    * f1 -> PrimaryExpression()
    */
   public void visit(PreIncrementExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "--"
    * f1 -> PrimaryExpression()
    */
   public void visit(PreDecrementExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( "~" | "!" ) UnaryExpression()
    *       | CastExpression()
    *       | PostfixExpression()
    */
   public void visit(UnaryExpressionNotPlusMinus n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "(" PrimitiveType()
    *       | "(" Name() "[" "]"
    *       | "(" Name() ")" ( "~" | "!" | "(" | <IDENTIFIER> | "this" | "super" | "new" | Literal() )
    */
   public void visit(CastLookahead n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> [ "++" | "--" ]
    */
   public void visit(PostfixExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "(" Type() ")" UnaryExpression()
    *       | "(" Type() ")" UnaryExpressionNotPlusMinus()
    */
   public void visit(CastExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> PrimaryPrefix()
    * f1 -> ( PrimarySuffix() )*
    */
   public void visit(PrimaryExpression n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> Literal()
    *       | Name()
    *       | "this"
    *       | "super" "." <IDENTIFIER>
    *       | "(" Expression() ")"
    *       | AllocationExpression()
    */
   public void visit(PrimaryPrefix n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "." "this"
    *       | "." "class"
    *       | "." AllocationExpression()
    *       | "[" Expression() "]"
    *       | "." <IDENTIFIER>
    *       | Arguments()
    */
   public void visit(PrimarySuffix n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    *       | <FLOATING_POINT_LITERAL>
    *       | <CHARACTER_LITERAL>
    *       | <STRING_LITERAL>
    *       | BooleanLiteral()
    *       | NullLiteral()
    */
   public void visit(Literal n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "true"
    *       | "false"
    */
   public void visit(BooleanLiteral n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "null"
    */
   public void visit(NullLiteral n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "("
    * f1 -> [ ArgumentList() ]
    * f2 -> ")"
    */
   public void visit(Arguments n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> Expression()
    * f1 -> ( "," Expression() )*
    */
   public void visit(ArgumentList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> "new" PrimitiveType() ArrayDimensions() [ ArrayInitializer() ]
    *       | "new" Name() ( ArrayDimensions() [ ArrayInitializer() ] | Arguments() [ ClassBody() ] )
    */
   public void visit(AllocationExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ( "[" Expression() "]" )+
    * f1 -> ( "[" "]" )*
    */
   public void visit(ArrayDimensions n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> LabeledStatement()
    *       | Block()
    *       | EmptyStatement()
    *       | StatementExpression() ";"
    *       | SwitchStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | DoStatement()
    *       | ForStatement()
    *       | BreakStatement()
    *       | ContinueStatement()
    *       | ReturnStatement()
    *       | ThrowStatement()
    *       | SynchronizedStatement()
    *       | TryStatement()
    */
   public void visit(Statement n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <IDENTIFIER>
    * f1 -> ":"
    * f2 -> Statement()
    */
   public void visit(LabeledStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "{"
    * f1 -> ( BlockStatement() )*
    * f2 -> "}"
    */
   public void visit(Block n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> LocalVariableDeclaration() ";"
    *       | Statement()
    *       | UnmodifiedClassDeclaration()
    */
   public void visit(BlockStatement n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> [ "final" ]
    * f1 -> Type()
    * f2 -> VariableDeclarator()
    * f3 -> ( "," VariableDeclarator() )*
    */
   public void visit(LocalVariableDeclaration n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> ";"
    */
   public void visit(EmptyStatement n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> PreIncrementExpression()
    *       | PreDecrementExpression()
    *       | Assignment()
    *       | PostfixExpression()
    */
   public void visit(StatementExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "switch"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> "{"
    * f5 -> ( SwitchLabel() ( BlockStatement() )* )*
    * f6 -> "}"
    */
   public void visit(SwitchStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
   }

   /**
    * f0 -> "case" Expression() ":"
    *       | "default" ":"
    */
   public void visit(SwitchLabel n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> [ "else" Statement() ]
    */
   public void visit(IfStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public void visit(WhileStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> "do"
    * f1 -> Statement()
    * f2 -> "while"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    * f6 -> ";"
    */
   public void visit(DoStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
   }

   /**
    * f0 -> "for"
    * f1 -> "("
    * f2 -> [ ForInit() ]
    * f3 -> ";"
    * f4 -> [ Expression() ]
    * f5 -> ";"
    * f6 -> [ ForUpdate() ]
    * f7 -> ")"
    * f8 -> Statement()
    */
   public void visit(ForStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
   }

   /**
    * f0 -> LocalVariableDeclaration()
    *       | StatementExpressionList()
    */
   public void visit(ForInit n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> StatementExpression()
    * f1 -> ( "," StatementExpression() )*
    */
   public void visit(StatementExpressionList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> StatementExpressionList()
    */
   public void visit(ForUpdate n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> "break"
    * f1 -> [ <IDENTIFIER> ]
    * f2 -> ";"
    */
   public void visit(BreakStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "continue"
    * f1 -> [ <IDENTIFIER> ]
    * f2 -> ";"
    */
   public void visit(ContinueStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "return"
    * f1 -> [ Expression() ]
    * f2 -> ";"
    */
   public void visit(ReturnStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "throw"
    * f1 -> Expression()
    * f2 -> ";"
    */
   public void visit(ThrowStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> "synchronized"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Block()
    */
   public void visit(SynchronizedStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> "try"
    * f1 -> Block()
    * f2 -> ( "catch" "(" FormalParameter() ")" Block() )*
    * f3 -> [ "finally" Block() ]
    */
   public void visit(TryStatement n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> JavaCCOptions()
    * f1 -> <PARSER_BEGIN_TK>
    * f2 -> <LPAREN>
    * f3 -> <IDENTIFIER>
    * f4 -> <RPAREN>
    * f5 -> CompilationUnit()
    * f6 -> <PARSER_END_TK>
    * f7 -> <LPAREN>
    * f8 -> <IDENTIFIER>
    * f9 -> <RPAREN>
    * f10 -> ( Production() )*
    * f11 -> <EOF>
    */
   public void visit(JavaCCInput n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
   }

   /**
    * f0 -> [ <OPTIONS_TK> <LBRACE> ( OptionBinding() )* <RBRACE> ]
    */
   public void visit(JavaCCOptions n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ( <IDENTIFIER> | <LOOKAHEAD_TK> | <IGNORE_CASE_TK> | <STATIC> )
    * f1 -> <ASSIGN>
    * f2 -> ( <INTEGER_LITERAL> | BooleanLiteral() | <STRING_LITERAL> )
    * f3 -> <SEMICOLON>
    */
   public void visit(OptionBinding n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> JavaCodeProduction()
    *       | RegularExprProduction()
    *       | BNFProduction()
    *       | TokenManagerDecls()
    */
   public void visit(Production n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <JAVACODE_TK>
    * f1 -> ResultType()
    * f2 -> <IDENTIFIER>
    * f3 -> FormalParameters()
    * f4 -> Block()
    */
   public void visit(JavaCodeProduction n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
   }

   /**
    * f0 -> ResultType()
    * f1 -> <IDENTIFIER>
    * f2 -> FormalParameters()
    * f3 -> <COLON>
    * f4 -> "{"
    * f5 -> ( BlockStatement() )*
    * f6 -> "}"
    * f7 -> <LBRACE>
    * f8 -> ExpansionChoices()
    * f9 -> <RBRACE>
    */
   public void visit(BNFProduction n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
   }

   /**
    * f0 -> [ LexicalStateList() ]
    * f1 -> RegExprKind()
    * f2 -> [ <LBRACKET> <IGNORE_CASE_TK> <RBRACKET> ]
    * f3 -> <COLON>
    * f4 -> <LBRACE>
    * f5 -> RegExprSpec()
    * f6 -> ( <BIT_OR> RegExprSpec() )*
    * f7 -> <RBRACE>
    */
   public void visit(RegularExprProduction n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
   }

   /**
    * f0 -> <TOKEN_MGR_DECLS_TK>
    * f1 -> <COLON>
    * f2 -> ClassBody()
    */
   public void visit(TokenManagerDecls n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> <LT> <STAR> <GT>
    *       | <LT> <IDENTIFIER> ( <COMMA> <IDENTIFIER> )* <GT>
    */
   public void visit(LexicalStateList n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <TOKEN_TK>
    *       | <SPECIAL_TOKEN_TK>
    *       | <SKIP_TK>
    *       | <MORE_TK>
    */
   public void visit(RegExprKind n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> RegularExpression()
    * f1 -> [ Block() ]
    * f2 -> [ <COLON> <IDENTIFIER> ]
    */
   public void visit(RegExprSpec n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
   }

   /**
    * f0 -> Expansion()
    * f1 -> ( <BIT_OR> Expansion() )*
    */
   public void visit(ExpansionChoices n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( ExpansionUnit() )*
    */
   public void visit(Expansion n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> LocalLookahead()
    *       | Block()
    *       | <LPAREN> ExpansionChoices() <RPAREN> [ <PLUS> | <STAR> | <HOOK> ]
    *       | <LBRACKET> ExpansionChoices() <RBRACKET>
    *       | [ PrimaryExpression() <ASSIGN> ] ExpansionUnitTerm()
    */
   public void visit(ExpansionUnit n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> RegularExpression()
    *       | <IDENTIFIER> Arguments()
    */
   public void visit(ExpansionUnitTerm n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <LOOKAHEAD_TK>
    * f1 -> <LPAREN>
    * f2 -> [ <INTEGER_LITERAL> ]
    * f3 -> [ <COMMA> ]
    * f4 -> ExpansionChoices()
    * f5 -> [ <COMMA> ]
    * f6 -> [ <LBRACE> Expression() <RBRACE> ]
    * f7 -> <RPAREN>
    */
   public void visit(LocalLookahead n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
   }

   /**
    * f0 -> <STRING_LITERAL>
    *       | <LT> [ [ <POUND> ] <IDENTIFIER> <COLON> ] ComplexRegularExpressionChoices() <GT>
    *       | <LT> <IDENTIFIER> <GT>
    *       | <LT> <EOF_TK> <GT>
    */
   public void visit(RegularExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> ComplexRegularExpression()
    * f1 -> ( <BIT_OR> ComplexRegularExpression() )*
    */
   public void visit(ComplexRegularExpressionChoices n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

   /**
    * f0 -> ( ComplexRegularExpressionUnit() )*
    */
   public void visit(ComplexRegularExpression n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> <STRING_LITERAL>
    *       | <LT> <IDENTIFIER> <GT>
    *       | CharacterList()
    *       | <LPAREN> ComplexRegularExpressionChoices() <RPAREN> [ <PLUS> | <STAR> | <HOOK> ]
    */
   public void visit(ComplexRegularExpressionUnit n, A argu) {
      n.f0.accept(this, argu);
   }

   /**
    * f0 -> [ <TILDE> ]
    * f1 -> <LBRACKET>
    * f2 -> [ CharacterDescriptor() ( <COMMA> CharacterDescriptor() )* ]
    * f3 -> <RBRACKET>
    */
   public void visit(CharacterList n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
   }

   /**
    * f0 -> <STRING_LITERAL>
    * f1 -> [ <MINUS> <STRING_LITERAL> ]
    */
   public void visit(CharacterDescriptor n, A argu) {
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
   }

}