/*    */ package com.cabolabs.xre.core.logic.bool;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.DataType;
/*    */ import com.cabolabs.xre.core.execution.Memory;
/*    */ import com.cabolabs.xre.core.execution.RuleExecution;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import java.util.List;
/*    */ 
/*    */ public class XorFunction extends BooleanFunction
/*    */ {
/*    */   private BooleanFunction f1;
/*    */   private BooleanFunction f2;
/*    */ 
/*    */   public XorFunction(String returnName, DataType returnType)
/*    */   {
/* 20 */     super(returnName, returnType);
/*    */   }
/*    */ 
/*    */   public List<VariableInstance> execute(String sessionId)
/*    */     throws Exception
/*    */   {
/* 26 */     RuleExecution re = Memory.getInstance().get(sessionId);
/*    */ 
/* 28 */     List res1 = this.f1.execute(sessionId);
/* 29 */     List res2 = this.f2.execute(sessionId);
/*    */ 
/* 31 */     return functionReturnValue(Boolean.valueOf(((Boolean)((VariableInstance)res1.get(0)).getValue()).booleanValue() ^ ((Boolean)((VariableInstance)res2.get(0)).getValue()).booleanValue()), re);
/*    */   }
/*    */ 
/*    */   public void setBooleanFunctions(BooleanFunction f1, BooleanFunction f2) throws Exception
/*    */   {
/* 36 */     this.f1 = f1;
/* 37 */     this.f2 = f2;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.bool.XorFunction
 * JD-Core Version:    0.6.2
 */