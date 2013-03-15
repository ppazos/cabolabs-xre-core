/*    */ package com.cabolabs.xre.core.logic.bool;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.DataType;
/*    */ import com.cabolabs.xre.core.execution.Memory;
/*    */ import com.cabolabs.xre.core.execution.RuleExecution;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import java.util.List;
/*    */ 
/*    */ public class AndFunction extends BooleanFunction
/*    */ {
/*    */   private List<BooleanFunction> fs;
/*    */ 
/*    */   public AndFunction(String returnName, DataType returnType)
/*    */   {
/* 18 */     super(returnName, returnType);
/*    */   }
/*    */ 
/*    */   public List<VariableInstance> execute(String sessionId)
/*    */     throws Exception
/*    */   {
/* 25 */     RuleExecution re = Memory.getInstance().get(sessionId);
/*    */ 
/* 34 */     for (BooleanFunction bf : this.fs)
/*    */     {
/* 36 */       List res = bf.execute(sessionId);
/* 37 */       if (BooleanFunction.isFalse((VariableInstance)res.get(0)))
/*    */       {
/* 39 */         return functionReturnValue(Boolean.FALSE, re);
/*    */       }
/*    */     }
/*    */ 
/* 43 */     return functionReturnValue(Boolean.TRUE, re);
/*    */   }
/*    */ 
/*    */   public void setBooleanFunctions(List<BooleanFunction> fs) throws Exception
/*    */   {
/* 48 */     if ((fs == null) || (fs.size() < 2))
/*    */     {
/* 50 */       throw new Exception("Se esperan 2 o mas funciones booleanas para evaluar el AND");
/*    */     }
/*    */ 
/* 53 */     this.fs = fs;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.bool.AndFunction
 * JD-Core Version:    0.6.2
 */