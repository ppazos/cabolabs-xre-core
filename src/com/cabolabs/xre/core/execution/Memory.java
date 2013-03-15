/*    */ package com.cabolabs.xre.core.execution;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class Memory
/*    */ {
/*    */   private Map<String, RuleExecution> memory;
/* 17 */   private static Memory instance = null;
/*    */ 
/*    */   public static Memory getInstance()
/*    */   {
/* 21 */     if (instance == null) instance = new Memory();
/* 22 */     return instance;
/*    */   }
/*    */ 
/*    */   private Memory()
/*    */   {
/* 27 */     this.memory = new ConcurrentHashMap();
/*    */   }
/*    */ 
/*    */   public String add(RuleExecution rule)
/*    */   {
/* 37 */     this.memory.put(rule.getId(), rule);
/* 38 */     return rule.getId();
/*    */   }
/*    */ 
/*    */   public RuleExecution get(String id)
/*    */   {
/* 43 */     return (RuleExecution)this.memory.get(id);
/*    */   }
/*    */ 
/*    */   public RuleExecution drop(String id)
/*    */   {
/* 48 */     return (RuleExecution)this.memory.remove(id);
/*    */   }
/*    */ 
/*    */   public boolean hasValue(String id, String name)
/*    */   {
/* 53 */     RuleExecution re = (RuleExecution)this.memory.get(id);
/*    */ 
/* 55 */     if (re == null) return false;
/*    */ 
/* 57 */     return re.hasValue(name);
/*    */   }
/*    */ 
/*    */   public Collection<RuleExecution> getAll()
/*    */   {
/* 62 */     return this.memory.values();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.execution.Memory
 * JD-Core Version:    0.6.2
 */