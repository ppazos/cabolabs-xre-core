/*    */ package com.cabolabs.xre.core.execution;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*    */ 
/*    */ public class VariableInstance
/*    */ {
/*    */   private VariableDeclaration declaration;
/*    */   private Object value;
/*    */ 
/*    */   public VariableInstance(VariableDeclaration declaration)
/*    */   {
/* 24 */     this.declaration = declaration;
/*    */   }
/*    */ 
/*    */   public VariableInstance(VariableDeclaration declaration, Object value) {
/* 28 */     this.declaration = declaration;
/* 29 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public VariableDeclaration getDeclaration()
/*    */   {
/* 34 */     return this.declaration;
/*    */   }
/*    */ 
/*    */   public Object getValue() {
/* 38 */     return this.value;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.execution.VariableInstance
 * JD-Core Version:    0.6.2
 */