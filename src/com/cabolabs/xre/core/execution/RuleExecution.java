package com.cabolabs.xre.core.execution;

import com.cabolabs.xre.core.definitions.ConstantValue;
import com.cabolabs.xre.core.definitions.RuleDefinition;
import com.cabolabs.xre.core.definitions.VariableDeclaration;
import com.cabolabs.xre.core.resolution.VariableResolution;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RuleExecution
{
   private String id;
   private RuleDefinition rule;
   private List<Exception> errors;
   private Map<String, VariableInstance> values;
   private List<VariableInstance> results;

   public RuleExecution(RuleDefinition rule)
   {
      this.id = UUID.randomUUID().toString();
      this.rule = rule;

      this.errors = new ArrayList<Exception>();

      this.results = new ArrayList<VariableInstance>();

      this.values = new HashMap<String, VariableInstance>();
   }

   public String getId()
   {
      return this.id;
   }

   public RuleDefinition getRule()
   {
      return this.rule;
   }

   public boolean resolve(List<VariableInstance> params)
   {
      boolean ok = true;

      if (params != null)
      {
         for (VariableInstance vi : params)
         {
            this.values.put(vi.getDeclaration().getName(), vi);
         }
      }

      for (VariableDeclaration vd : this.rule.getDeclarations())
      {
         if ((vd instanceof ConstantValue))
         {
            VariableInstance value = Executer.getInstance().createInstance(this.id, vd.getName(), ((ConstantValue)vd).getValue());
            this.values.put(vd.getName(), value);
         }
      }

      for (VariableResolution r : this.rule.getResolutions())
      {
         System.out.println(" = resolution: " + r.getClass() + " para " + r.getDeclaration().getName());
         try
         {
            // FIXME: no deberia pasarle params? o era que cada resolution accede a los params por la memoria?
            VariableInstance value = r.getValue(this.id, new ArrayList<VariableInstance>());

            this.values.put(value.getDeclaration().getName(), value);
         }
         catch (Exception e)
         {
            ok = false;

            System.out.println("error resolve message: " + e.getMessage());
            System.out.println("error resolve: localizedMessage" + e.getLocalizedMessage());
            e.printStackTrace();

            this.errors.add(e);
         }
      }

      return ok;
   }

   public boolean execute(List<VariableInstance> input)
   {
      if (this.errors.size() != 0)
      {
         this.errors.add(new Exception("Can't execute a rule that has initialization errors"));
         return false;
      }

      for (VariableInstance v : input)
      {
         this.values.put(v.getDeclaration().getName(), v);
      }

      boolean ok = true;
      try
      {
         this.rule.getLogic().execute(this.id);
      }
      catch (Exception e)
      {
         ok = false;

         System.out.println("execute error message: " + e.getMessage());
         e.printStackTrace();

         this.errors.add(e);
      }

      return ok;
   }

   public List<VariableInstance> getResults()throws Exception
   {
      if (this.errors.size() != 0) throw new Exception("Cant't get results from a rule that has execution errors");
      if (this.results == null) throw new Exception("Can't get results from a rule that hasn't been executed, call execute() before getResults()");
      return this.results;
   }

   public void addToResults(VariableInstance val)
   {
      this.results.add(val);
   }

   public void addValue(VariableInstance value)
   {
      this.values.put(value.getDeclaration().getName(), value);
   }

   public void addValues(List<VariableInstance> values)
   {
      for (VariableInstance val : values)
      {
         this.values.put(val.getDeclaration().getName(), val);
      }
   }

   public VariableInstance getValue(String name)throws Exception
   {
      if (!this.values.containsKey(name))
      {
         throw new Exception("El valor solicitado '" + name + "' no ha sido definido, verifique que en la regla se declara la variable y que se define su resolucion");
      }

      return (VariableInstance)this.values.get(name);
   }

   public VariableInstance getValue(VariableDeclaration var)
         throws Exception
         {
      if ((var instanceof ConstantValue)) return new VariableInstance(var, ((ConstantValue)var).getValue());

      if (!this.values.containsKey(var.getName()))
      {
         throw new Exception("El valor solicitado '" + var.getName() + "' no ha sido definido, verifique que en la regla se declara la variable y que se define su resolucion");
      }

      return (VariableInstance)this.values.get(var.getName());
         }

   public boolean hasValue(String name)
   {
      return this.values.containsKey(name);
   }

   public boolean hasValue(VariableDeclaration var) {
      return this.values.containsKey(var.getName());
   }

   public Collection<VariableInstance> getValues() {
      return this.values.values();
   }

   public VariableDeclaration getDeclaration(String name)
   {
      return this.rule.getDeclaration(name);
   }

   public VariablePointer getPointer(String name)
   {
      VariableInstance var = (VariableInstance)this.values.get(name);
      return new VariablePointer(var);
   }

   public List<Exception> getErrors()
   {
      return this.errors;
   }

   public boolean hasErrors() {
      return this.errors.size() > 0;
   }

   public String toString()
   {
      return "RuleExecution (" + this.id + ") for " + this.rule.getId();
   }
}