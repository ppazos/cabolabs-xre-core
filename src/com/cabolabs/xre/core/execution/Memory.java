package com.cabolabs.xre.core.execution;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memory
{
   private Map<String, RuleExecution> memory;
   private static Memory instance = null;

   public static Memory getInstance()
   {
      if (instance == null) instance = new Memory();
      return instance;
   }

   private Memory()
   {
      // ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) 
      // http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/ConcurrentHashMap.html
      // http://www.docjar.com/html/api/java/util/concurrent/ConcurrentHashMap.java.html
      this.memory = new ConcurrentHashMap<String, RuleExecution>(10, 0.8f, 2);
   }

   public String add(RuleExecution rule)
   {
      this.memory.put(rule.getId(), rule);
      return rule.getId();
   }

   public RuleExecution get(String id)
   {
      return (RuleExecution)this.memory.get(id);
   }

   public RuleExecution drop(String id)
   {
      return (RuleExecution)this.memory.remove(id);
   }

   public boolean hasValue(String id, String name)
   {
      RuleExecution re = (RuleExecution)this.memory.get(id);

      if (re == null) return false;

      return re.hasValue(name);
   }

   public Collection<RuleExecution> getAll()
   {
      return this.memory.values();
   }
}