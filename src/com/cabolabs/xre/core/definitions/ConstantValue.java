/*    */ package com.cabolabs.xre.core.definitions;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.SecureRandom;
/*    */ 
/*    */ public class ConstantValue extends VariableDeclaration
/*    */ {
/* 20 */   private static SecureRandom random = new SecureRandom();
/*    */   protected Object value;
/*    */ 
/*    */   public ConstantValue(String name, DataType type, String namespace, Object value)
/*    */   {
/* 26 */     super(name, type, namespace);
/* 27 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public ConstantValue(String name, DataType type, Object value)
/*    */   {
/* 32 */     super(name, type);
/* 33 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public ConstantValue(DataType type, String namespace, Object value)
/*    */   {
/* 40 */     super(new BigInteger(130, random).toString(32), type, namespace);
/* 41 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public ConstantValue(DataType type, Object value)
/*    */   {
/* 48 */     super(new BigInteger(130, random).toString(32), type);
/* 49 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Object getValue()
/*    */   {
/* 54 */     return this.value;
/*    */   }
/*    */ 
/*    */   public static ConstantValue fromValue(Object value)
/*    */     throws Exception
/*    */   {
/* 67 */     ConstantValue cval = new ConstantValue(
/* 68 */       DataType.get(value.getClass()), 
/* 69 */       value);
/*    */ 
/* 71 */     return cval;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.ConstantValue
 * JD-Core Version:    0.6.2
 */