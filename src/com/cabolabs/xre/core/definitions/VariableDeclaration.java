/*    */ package com.cabolabs.xre.core.definitions;
/*    */ 
/*    */ public class VariableDeclaration
/*    */ {
/*    */   protected String name;
/*    */   protected DataType type;
/*    */   protected String namespace;
/*    */ 
/*    */   public VariableDeclaration(String name, DataType type)
/*    */   {
/* 12 */     this.name = name;
/* 13 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public VariableDeclaration(String name, DataType type, String namespace)
/*    */   {
/* 18 */     this.name = name;
/* 19 */     this.type = type;
/* 20 */     this.namespace = namespace;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   public DataType getType()
/*    */   {
/* 30 */     return this.type;
/*    */   }
/*    */ 
/*    */   public String getNamespace()
/*    */   {
/* 35 */     return this.namespace;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.VariableDeclaration
 * JD-Core Version:    0.6.2
 */