/*    */ package com.cabolabs.xre.core.logic.bool;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.DataType;
/*    */ import com.cabolabs.xre.core.execution.Memory;
/*    */ import com.cabolabs.xre.core.execution.RuleExecution;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import java.util.List;
/*    */ 
/*    */ public class NotFunction extends BooleanFunction
/*    */ {
/*    */   private BooleanFunction f;
/*    */ 
/*    */   public NotFunction(String returnName, DataType returnType)
/*    */   {
/* 18 */     super(returnName, returnType);
/*    */   }
/*    */ 
/*    */   public List<VariableInstance> execute(String sessionId)
/*    */     throws Exception
/*    */   {
/* 24 */     RuleExecution re = Memory.getInstance().get(sessionId);
/*    */ 
/* 26 */     List res = this.f.execute(sessionId);
/*    */ 
/* 31 */     return functionReturnValue(Boolean.valueOf(BooleanFunction.isFalse((VariableInstance)res.get(0))), re);
/*    */   }
/*    */ 
/*    */   public void setBooleanFunction(BooleanFunction f) throws Exception
/*    */   {
/* 36 */     if (f == null)
/*    */     {
/* 38 */       throw new Exception("Se esperan 1 funcion booleana para evaluar el NOT");
/*    */     }
/*    */ 
/* 41 */     this.f = f;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.bool.NotFunction
 * JD-Core Version:    0.6.2
 */