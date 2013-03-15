/*     */ package com.cabolabs.xre.core.test.definitions;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.DataType;
/*     */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*     */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*     */ import com.cabolabs.xre.core.execution.Executer;
/*     */ import com.cabolabs.xre.core.execution.Memory;
/*     */ import com.cabolabs.xre.core.execution.RuleExecution;
/*     */ import com.cabolabs.xre.core.execution.VariableInstance;
/*     */ import com.cabolabs.xre.core.logic.Function;
/*     */ import com.cabolabs.xre.core.logic.LogicBlock;
/*     */ import com.cabolabs.xre.core.resolution.VariableResolution;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.junit.After;
/*     */ import org.junit.AfterClass;
/*     */ import org.junit.Assert;
/*     */ import org.junit.Before;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.Test;
/*     */ 
/*     */ public class ExecuterTest
/*     */ {
/*     */   private RuleDefinition rule1;
/*     */ 
/*     */   @BeforeClass
/*     */   public static void setUpBeforeClass()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   @AfterClass
/*     */   public static void tearDownAfterClass()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   @Before
/*     */   public void setUp()
/*     */     throws Exception
/*     */   {
/*  49 */     this.rule1 = new RuleDefinition("rule1.v1");
/*     */ 
/*  51 */     List declarations = new ArrayList();
/*  52 */     declarations.add(new VariableDeclaration("var1", DataType.INT32, "res1"));
/*  53 */     declarations.add(new VariableDeclaration("var2", DataType.INT32, "res2"));
/*     */ 
/*  55 */     this.rule1.setDeclarations(declarations);
/*     */ 
/*  58 */     List input = new ArrayList();
/*  59 */     input.add(new VariableDeclaration("param1", DataType.INT32));
/*     */ 
/*  61 */     this.rule1.setInput(input);
/*     */ 
/*  64 */     List resolutions = new ArrayList();
/*     */ 
/*  68 */     resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(0))
/*     */     {
/*     */       public VariableInstance getValue(String sessionId, List<VariableInstance> params)
/*     */       {
/*  74 */         return new VariableInstance(getDeclaration(), Integer.valueOf(1));
/*     */       }
/*     */     });
/*  77 */     resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(1))
/*     */     {
/*     */       public VariableInstance getValue(String sessionId, List<VariableInstance> params)
/*     */       {
/*  82 */         return new VariableInstance(getDeclaration(), Integer.valueOf(2));
/*     */       }
/*     */     });
/*  86 */     this.rule1.addAllToResolutions(resolutions);
/*     */ 
/*  89 */     List functions = new ArrayList();
/*  90 */     functions.add(
/*  91 */       new Function("resultadoSuma", DataType.INT32)
/*     */     {
/*     */       public List<VariableInstance> execute(String sessionId)
/*     */         throws Exception
/*     */       {
/* 105 */         RuleExecution re = Memory.getInstance().get(sessionId);
/*     */ 
/* 108 */         VariableInstance par1 = re.getValue(((VariableDeclaration)this.params.get(0)).getName());
/* 109 */         VariableInstance par2 = re.getValue(((VariableDeclaration)this.params.get(1)).getName());
/* 110 */         VariableInstance par3 = re.getValue(((VariableDeclaration)this.params.get(2)).getName());
/*     */ 
/* 131 */         return functionReturnValue(Integer.valueOf(((Integer)par1.getValue()).intValue() + ((Integer)par2.getValue()).intValue() + ((Integer)par3.getValue()).intValue()), re);
/*     */       }
/*     */     });
/* 138 */     List returnNames = new ArrayList();
/* 139 */     returnNames.add("resultadoSuma");
/*     */ 
/* 141 */     this.rule1.setLogic(
/* 142 */       new LogicBlock(functions, returnNames));
/*     */ 
/* 148 */     List params = new ArrayList();
/*     */ 
/* 152 */     params.add((VariableDeclaration)declarations.get(0));
/* 153 */     params.add((VariableDeclaration)declarations.get(1));
/* 154 */     params.add((VariableDeclaration)input.get(0));
/*     */ 
/* 156 */     ((Function)functions.get(0)).setParams(params);
/*     */   }
/*     */ 
/*     */   @After
/*     */   public void tearDown()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   @Test
/*     */   public void testExecute()
/*     */     throws Exception
/*     */   {
/* 188 */     int input_value = 456;
/*     */ 
/* 190 */     Executer ex = Executer.getInstance();
/*     */ 
/* 194 */     String sessionId = ex.add(this.rule1);
/*     */ 
/* 198 */     if (!ex.contains(sessionId))
/*     */     {
/* 200 */       Assert.fail("1. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
/*     */     }
/*     */ 
/* 204 */     List params = null;
/* 205 */     if (!ex.init(sessionId, params))
/*     */     {
/* 207 */       for (Exception e : ex.getErrors(sessionId))
/*     */       {
/* 209 */         System.out.println(e.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 214 */     if (!ex.contains(sessionId))
/*     */     {
/* 216 */       Assert.fail("2. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
/*     */     }
/*     */ 
/* 220 */     List ex_input = new ArrayList();
/*     */ 
/* 223 */     ex_input.add(ex.createInstance(sessionId, "param1", new Integer(input_value)));
/*     */ 
/* 225 */     if (!ex.execute(sessionId, ex_input))
/*     */     {
/* 227 */       for (Exception e : ex.getErrors(sessionId))
/*     */       {
/* 229 */         System.out.println(e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/* 233 */     List results = ex.getResults(sessionId);
/*     */ 
/* 235 */     if (results.size() != 1) Assert.fail("Deberia haber un unico resultado y hay: " + results.size());
/*     */ 
/* 244 */     if (((Integer)((VariableInstance)results.get(0)).getValue()).intValue() != input_value + 1 + 2)
/*     */     {
/* 246 */       Assert.fail("El resultado esperado es: " + (input_value + 1 + 2) + " y se obtuvo: " + ((VariableInstance)results.get(0)).getValue());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.test.definitions.ExecuterTest
 * JD-Core Version:    0.6.2
 */