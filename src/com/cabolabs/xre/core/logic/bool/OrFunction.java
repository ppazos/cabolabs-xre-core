/*    */ package com.cabolabs.xre.core.logic.bool;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.DataType;
/*    */ import com.cabolabs.xre.core.execution.Memory;
/*    */ import com.cabolabs.xre.core.execution.RuleExecution;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OrFunction extends BooleanFunction
/*    */ {
/*    */   private List<BooleanFunction> fs;
/*    */ 
/*    */   public OrFunction(String returnName, DataType returnType)
/*    */   {
/* 18 */     super(returnName, returnType);
/*    */   }
/*    */ 
/*    */   public List<VariableInstance> execute(String sessionId)
/*    */     throws Exception
/*    */   {
/* 24 */     RuleExecution re = Memory.getInstance().get(sessionId);
/*    */ 
/* 27 */     for (BooleanFunction bf : this.fs)
/*    */     {
/* 29 */       List res = bf.execute(sessionId);
/* 30 */       if (BooleanFunction.isTrue((VariableInstance)res.get(0)))
/*    */       {
/* 32 */         return functionReturnValue(Boolean.TRUE, re);
/*    */       }
/*    */     }
/*    */ 
/* 36 */     return functionReturnValue(Boolean.FALSE, re);
/*    */   }
/*    */ 
/*    */   public void setBooleanFunctions(List<BooleanFunction> fs) throws Exception
/*    */   {
/* 41 */     if ((fs == null) || (fs.size() < 2))
/*    */     {
/* 43 */       throw new Exception("Se esperan 2 o mas funciones booleanas para evaluar el OR");
/*    */     }
/*    */ 
/* 46 */     this.fs = fs;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.bool.OrFunction
 * JD-Core Version:    0.6.2
 */