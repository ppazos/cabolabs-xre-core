/*     */ package com.cabolabs.xre.core.execution;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*     */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Executer
/*     */ {
/*  12 */   static Memory memory = Memory.getInstance();
/*     */ 
/*  14 */   static Executer instance = null;
/*     */ 
/*     */   public static Executer getInstance()
/*     */   {
/*  22 */     if (instance == null) instance = new Executer();
/*  23 */     return instance;
/*     */   }
/*     */ 
/*     */   public String add(RuleDefinition rule)
/*     */   {
/*  34 */     RuleExecution re = new RuleExecution(rule);
/*     */ 
/*  37 */     return memory.add(re);
/*     */   }
/*     */ 
/*     */   public boolean contains(String sessionId)
/*     */   {
/*  47 */     return memory.get(sessionId) != null;
/*     */   }
/*     */ 
/*     */   public RuleExecution get(String sessionId)
/*     */   {
/*  53 */     return memory.get(sessionId);
/*     */   }
/*     */ 
/*     */   public List<String> getExecutionIds()
/*     */   {
/*  58 */     List ids = new ArrayList();
/*     */ 
/*  60 */     Collection res = memory.getAll();
/*  61 */     for (RuleExecution re : res)
/*     */     {
/*  63 */       ids.add(re.getId());
/*     */     }
/*     */ 
/*  66 */     return ids;
/*     */   }
/*     */ 
/*     */   public void remove(String sessionId)
/*     */   {
/*  75 */     memory.drop(sessionId);
/*     */   }
/*     */ 
/*     */   public boolean init(String sessionId, List<VariableInstance> params)
/*     */   {
/*  94 */     RuleExecution re = memory.get(sessionId);
/*     */ 
/*  96 */     if (re == null)
/*     */     {
/*  99 */       return false;
/*     */     }
/*     */ 
/* 102 */     return re.resolve(params);
/*     */   }
/*     */ 
/*     */   public Collection<VariableInstance> getValues(String sessionId)
/*     */   {
/* 113 */     RuleExecution re = memory.get(sessionId);
/* 114 */     return re.getValues();
/*     */   }
/*     */ 
/*     */   public boolean execute(String sessionId, List<VariableInstance> input)
/*     */   {
/* 125 */     RuleExecution re = memory.get(sessionId);
/*     */ 
/* 133 */     if (re == null)
/*     */     {
/* 136 */       return false;
/*     */     }
/*     */ 
/* 139 */     return re.execute(input);
/*     */   }
/*     */ 
/*     */   public List<VariableInstance> getResults(String sessionId)
/*     */     throws Exception
/*     */   {
/* 150 */     RuleExecution re = memory.get(sessionId);
/*     */ 
/* 152 */     if (re == null)
/*     */     {
/* 155 */       return null;
/*     */     }
/*     */ 
/* 158 */     return re.getResults();
/*     */   }
/*     */ 
/*     */   public List<VariableInstance> getResultsAndDrop(String sessionId)
/*     */     throws Exception
/*     */   {
/* 169 */     RuleExecution re = memory.drop(sessionId);
/*     */ 
/* 171 */     if (re == null)
/*     */     {
/* 174 */       return null;
/*     */     }
/*     */ 
/* 177 */     return re.getResults();
/*     */   }
/*     */ 
/*     */   public List<Exception> getErrors(String sessionId)
/*     */   {
/* 187 */     RuleExecution re = memory.get(sessionId);
/*     */ 
/* 189 */     if (re == null)
/*     */     {
/* 192 */       return null;
/*     */     }
/*     */ 
/* 195 */     return re.getErrors();
/*     */   }
/*     */ 
/*     */   public VariableInstance createInstance(String sessionId, String name, Object value)
/*     */   {
/* 208 */     RuleExecution re = memory.get(sessionId);
/*     */ 
/* 210 */     if (re == null)
/*     */     {
/* 213 */       return null;
/*     */     }
/*     */ 
/* 216 */     VariableDeclaration vd = re.getDeclaration(name);
/*     */ 
/* 218 */     if (vd == null)
/*     */     {
/* 221 */       return null;
/*     */     }
/*     */ 
/* 224 */     return new VariableInstance(vd, value);
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.execution.Executer
 * JD-Core Version:    0.6.2
 */