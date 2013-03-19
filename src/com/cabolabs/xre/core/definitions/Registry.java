package com.cabolabs.xre.core.definitions;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Registry
{
   private Map<String, RuleDefinition> registry;
   private static Registry instance = null;

   public static Registry getInstance()
   {
      if (instance == null) instance = new Registry();
      return instance;
   }

   private Registry()
   {
      this.registry = new ConcurrentHashMap<String, RuleDefinition>();
   }

   public String add(RuleDefinition rule)
   {
      this.registry.put(rule.getId(), rule);
      return rule.getId();
   }

   public RuleDefinition get(String key)
   {
      return (RuleDefinition)this.registry.get(key);
   }

   public RuleDefinition drop(String key)
   {
      return (RuleDefinition)this.registry.remove(key);
   }

   public Collection<RuleDefinition> getAll()
   {
      return this.registry.values();
   }
}