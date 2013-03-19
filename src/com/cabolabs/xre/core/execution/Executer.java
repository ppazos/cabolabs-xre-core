package com.cabolabs.xre.core.execution;

import com.cabolabs.xre.core.definitions.RuleDefinition;
import com.cabolabs.xre.core.definitions.VariableDeclaration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Executer
{
   static Memory memory = Memory.getInstance();

   static Executer instance = null;

   public static Executer getInstance()
   {
      if (instance == null) instance = new Executer();
      return instance;
   }

   public String add(RuleDefinition rule)
   {
      RuleExecution re = new RuleExecution(rule);

      return memory.add(re);
   }

   public boolean contains(String sessionId)
   {
      return memory.get(sessionId) != null;
   }

   public RuleExecution get(String sessionId)
   {
      return memory.get(sessionId);
   }

   public List<String> getExecutionIds()
   {
      List<String> ids = new ArrayList<String>();

      Collection<RuleExecution> res = memory.getAll();
      for (RuleExecution re : res)
      {
         ids.add(re.getId());
      }

      return ids;
   }

   public void remove(String sessionId)
   {
      memory.drop(sessionId);
   }

   public boolean init(String sessionId, List<VariableInstance> params)
   {
      RuleExecution re = memory.get(sessionId);

      if (re == null)
      {
         return false;
      }

      return re.resolve(params);
   }

   public Collection<VariableInstance> getValues(String sessionId)
   {
      RuleExecution re = memory.get(sessionId);
      return re.getValues();
   }

   public boolean execute(String sessionId, List<VariableInstance> input)
   {
      RuleExecution re = memory.get(sessionId);

      if (re == null)
      {
         return false;
      }

      return re.execute(input);
   }

   public List<VariableInstance> getResults(String sessionId) throws Exception
   {
      RuleExecution re = memory.get(sessionId);

      if (re == null)
      {
         return null;
      }

      return re.getResults();
   }

   public List<VariableInstance> getResultsAndDrop(String sessionId) throws Exception
   {
      RuleExecution re = memory.drop(sessionId);

      if (re == null)
      {
         return null;
      }

      return re.getResults();
   }

   public List<Exception> getErrors(String sessionId)
   {
      RuleExecution re = memory.get(sessionId);

      if (re == null)
      {
         return null;
      }

      return re.getErrors();
   }

   public VariableInstance createInstance(String sessionId, String name, Object value)
   {
      RuleExecution re = memory.get(sessionId);

      if (re == null)
      {
         return null;
      }

      VariableDeclaration vd = re.getDeclaration(name);

      if (vd == null)
      {
         return null;
      }

      return new VariableInstance(vd, value);
   }
}