/*    */ package com.cabolabs.xre.core.definitions;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class Registry
/*    */ {
/*    */   private Map<String, RuleDefinition> registry;
/* 22 */   private static Registry instance = null;
/*    */ 
/*    */   public static Registry getInstance()
/*    */   {
/* 26 */     if (instance == null) instance = new Registry();
/* 27 */     return instance;
/*    */   }
/*    */ 
/*    */   private Registry()
/*    */   {
/* 40 */     this.registry = new ConcurrentHashMap();
/*    */   }
/*    */ 
/*    */   public String add(RuleDefinition rule)
/*    */   {
/* 51 */     this.registry.put(rule.getId(), rule);
/* 52 */     return rule.getId();
/*    */   }
/*    */ 
/*    */   public RuleDefinition get(String key)
/*    */   {
/* 62 */     return (RuleDefinition)this.registry.get(key);
/*    */   }
/*    */ 
/*    */   public RuleDefinition drop(String key)
/*    */   {
/* 72 */     return (RuleDefinition)this.registry.remove(key);
/*    */   }
/*    */ 
/*    */   public Collection<RuleDefinition> getAll()
/*    */   {
/* 81 */     return this.registry.values();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.Registry
 * JD-Core Version:    0.6.2
 */