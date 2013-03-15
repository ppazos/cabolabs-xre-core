/*    */ package com.cabolabs.xre.core.definitions;
/*    */ 
/*    */ public abstract class ManagedResource
/*    */ {
/*    */   String id;
/*    */   String concept;
/*    */   String version;
/*    */   String name;
/*    */   String description;
/*    */   String keywords;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 18 */     return this.id;
/*    */   }
/*    */ 
/*    */   public String getConcept()
/*    */   {
/* 27 */     return this.concept;
/*    */   }
/*    */ 
/*    */   public String getVersion()
/*    */   {
/* 36 */     return this.version;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 41 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 45 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description)
/*    */   {
/* 50 */     this.description = description;
/*    */   }
/*    */ 
/*    */   public String getDescription() {
/* 54 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setKeywords(String keywords)
/*    */   {
/* 59 */     this.keywords = keywords;
/*    */   }
/*    */ 
/*    */   public String getKeywords() {
/* 63 */     return this.keywords;
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.ManagedResource
 * JD-Core Version:    0.6.2
 */