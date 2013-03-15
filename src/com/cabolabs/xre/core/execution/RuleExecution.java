/*     */ package com.cabolabs.xre.core.execution;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.ConstantValue;
/*     */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*     */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*     */ import com.cabolabs.xre.core.logic.LogicBlock;
/*     */ import com.cabolabs.xre.core.resolution.VariableResolution;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class RuleExecution
/*     */ {
/*     */   private String id;
/*     */   private RuleDefinition rule;
/*     */   private List<Exception> errors;
/*     */   private Map<String, VariableInstance> values;
/*     */   private List<VariableInstance> results;
/*     */ 
/*     */   public RuleExecution(RuleDefinition rule)
/*     */   {
/*  40 */     this.id = UUID.randomUUID().toString();
/*  41 */     this.rule = rule;
/*     */ 
/*  43 */     this.errors = new ArrayList();
/*     */ 
/*  48 */     this.results = new ArrayList();
/*     */ 
/*  50 */     this.values = new HashMap();
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */   public RuleDefinition getRule()
/*     */   {
/*  60 */     return this.rule;
/*     */   }
/*     */ 
/*     */   public boolean resolve(List<VariableInstance> params)
/*     */   {
/*  97 */     boolean ok = true;
/*     */ 
/* 103 */     for (VariableInstance vi : params)
/*     */     {
/* 105 */       this.values.put(vi.getDeclaration().getName(), vi);
/*     */     }
/*     */ 
/* 112 */     for (VariableDeclaration vd : this.rule.getDeclarations())
/*     */     {
/* 114 */       if ((vd instanceof ConstantValue))
/*     */       {
/* 117 */         VariableInstance value = Executer.getInstance().createInstance(this.id, vd.getName(), ((ConstantValue)vd).getValue());
/* 118 */         this.values.put(vd.getName(), value);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 126 */     for (VariableResolution r : this.rule.getResolutions())
/*     */     {
/* 129 */       System.out.println(" = resolution: " + r.getClass() + " para " + r.getDeclaration().getName());
/*     */       try
/*     */       {
/* 153 */         VariableInstance value = r.getValue(this.id, new ArrayList());
/*     */ 
/* 160 */         this.values.put(value.getDeclaration().getName(), value);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 164 */         ok = false;
/*     */ 
/* 167 */         System.out.println("error resolve message: " + e.getMessage());
/* 168 */         System.out.println("error resolve: localizedMessage" + e.getLocalizedMessage());
/* 169 */         e.printStackTrace();
/*     */ 
/* 173 */         this.errors.add(e);
/*     */       }
/*     */     }
/*     */ 
/* 177 */     return ok;
/*     */   }
/*     */ 
/*     */   public boolean execute(List<VariableInstance> input)
/*     */   {
/* 184 */     if (this.errors.size() != 0)
/*     */     {
/* 186 */       this.errors.add(new Exception("Can't execute a rule that has initialization errors"));
/* 187 */       return false;
/*     */     }
/*     */ 
/* 198 */     for (VariableInstance v : input)
/*     */     {
/* 203 */       this.values.put(v.getDeclaration().getName(), v);
/*     */     }
/*     */ 
/* 206 */     boolean ok = true;
/*     */     try
/*     */     {
/* 216 */       this.rule.getLogic().execute(this.id);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 220 */       ok = false;
/*     */ 
/* 224 */       System.out.println("execute error message: " + e.getMessage());
/* 225 */       e.printStackTrace();
/*     */ 
/* 228 */       this.errors.add(e);
/*     */     }
/*     */ 
/* 231 */     return ok;
/*     */   }
/*     */ 
/*     */   public List<VariableInstance> getResults()
/*     */     throws Exception
/*     */   {
/* 243 */     if (this.errors.size() != 0) throw new Exception("Cant't get results from a rule that has execution errors");
/* 244 */     if (this.results == null) throw new Exception("Can't get results from a rule that hasn't been executed, call execute() before getResults()");
/* 245 */     return this.results;
/*     */   }
/*     */ 
/*     */   public void addToResults(VariableInstance val)
/*     */   {
/* 254 */     this.results.add(val);
/*     */   }
/*     */ 
/*     */   public void addValue(VariableInstance value)
/*     */   {
/* 283 */     this.values.put(value.getDeclaration().getName(), value);
/*     */   }
/*     */ 
/*     */   public void addValues(List<VariableInstance> values)
/*     */   {
/* 290 */     for (VariableInstance val : values)
/*     */     {
/* 292 */       this.values.put(val.getDeclaration().getName(), val);
/*     */     }
/*     */   }
/*     */ 
/*     */   public VariableInstance getValue(String name)
/*     */     throws Exception
/*     */   {
/* 308 */     if (!this.values.containsKey(name))
/*     */     {
/* 310 */       throw new Exception("El valor solicitado '" + name + "' no ha sido definido, verifique que en la regla se declara la variable y que se define su resolucion");
/*     */     }
/*     */ 
/* 313 */     return (VariableInstance)this.values.get(name);
/*     */   }
/*     */ 
/*     */   public VariableInstance getValue(VariableDeclaration var)
/*     */     throws Exception
/*     */   {
/* 325 */     if ((var instanceof ConstantValue)) return new VariableInstance(var, ((ConstantValue)var).getValue());
/*     */ 
/* 327 */     if (!this.values.containsKey(var.getName()))
/*     */     {
/* 329 */       throw new Exception("El valor solicitado '" + var.getName() + "' no ha sido definido, verifique que en la regla se declara la variable y que se define su resolucion");
/*     */     }
/*     */ 
/* 332 */     return (VariableInstance)this.values.get(var.getName());
/*     */   }
/*     */ 
/*     */   public boolean hasValue(String name)
/*     */   {
/* 337 */     return this.values.containsKey(name);
/*     */   }
/*     */ 
/*     */   public boolean hasValue(VariableDeclaration var) {
/* 341 */     return this.values.containsKey(var.getName());
/*     */   }
/*     */ 
/*     */   public Collection<VariableInstance> getValues() {
/* 345 */     return this.values.values();
/*     */   }
/*     */ 
/*     */   public VariableDeclaration getDeclaration(String name)
/*     */   {
/* 354 */     return this.rule.getDeclaration(name);
/*     */   }
/*     */ 
/*     */   public VariablePointer getPointer(String name)
/*     */   {
/* 366 */     VariableInstance var = (VariableInstance)this.values.get(name);
/* 367 */     return new VariablePointer(var);
/*     */   }
/*     */ 
/*     */   public List<Exception> getErrors()
/*     */   {
/* 385 */     return this.errors;
/*     */   }
/*     */ 
/*     */   public boolean hasErrors() {
/* 389 */     return this.errors.size() > 0;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 396 */     return "RuleExecution (" + this.id + ") for " + this.rule.getId();
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.execution.RuleExecution
 * JD-Core Version:    0.6.2
 */