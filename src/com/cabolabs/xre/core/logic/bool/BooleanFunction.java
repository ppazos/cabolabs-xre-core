/*    */ package com.cabolabs.xre.core.logic.bool;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.DataType;
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import com.cabolabs.xre.core.logic.Function;
/*    */ 
/*    */ public abstract class BooleanFunction extends Function
/*    */ {
/*    */   public static boolean isTrue(VariableInstance v)
/*    */   {
/* 15 */     return ((Boolean)v.getValue()).booleanValue();
/*    */   }
/*    */ 
/*    */   public static boolean isFalse(VariableInstance v) {
/* 19 */     return !((Boolean)v.getValue()).booleanValue();
/*    */   }
/*    */ 
/*    */   public BooleanFunction()
/*    */   {
/* 28 */     super(null, DataType.BOOLEAN);
/*    */   }
/*    */ 
/*    */   public BooleanFunction(String returnName)
/*    */   {
/* 33 */     super(returnName, DataType.BOOLEAN);
/*    */   }
/*    */ 
/*    */   public BooleanFunction(String returnName, DataType returnType)
/*    */   {
/* 38 */     super(returnName, DataType.BOOLEAN);
/*    */   }
/*    */ 
/*    */   public void setReturnType(DataType returnType)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.bool.BooleanFunction
 * JD-Core Version:    0.6.2
 */