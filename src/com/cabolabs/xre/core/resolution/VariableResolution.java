/*    */ package com.cabolabs.xre.core.resolution;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public abstract class VariableResolution
/*    */ {
/*    */   private VariableDeclaration declaration;
/* 20 */   private List<Exception> errors = new ArrayList();
/*    */ 
/*    */   public VariableResolution(VariableDeclaration declaration)
/*    */   {
/* 28 */     this.declaration = declaration;
/*    */   }
/*    */ 
/*    */   public abstract VariableInstance getValue(String paramString, List<VariableInstance> paramList);
/*    */ 
/*    */   public VariableDeclaration getDeclaration()
/*    */   {
/* 44 */     return this.declaration;
/*    */   }
/*    */ 
/*    */   public List<Exception> getErrors()
/*    */   {
/* 49 */     return this.errors;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.resolution.VariableResolution
 * JD-Core Version:    0.6.2
 */