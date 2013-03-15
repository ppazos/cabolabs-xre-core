/*     */ package com.cabolabs.xre.core.test.definitions;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.ConstantValue;
/*     */ import com.cabolabs.xre.core.definitions.DataType;
/*     */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*     */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*     */ import com.cabolabs.xre.core.execution.Executer;
/*     */ import com.cabolabs.xre.core.execution.Memory;
/*     */ import com.cabolabs.xre.core.execution.RuleExecution;
/*     */ import com.cabolabs.xre.core.execution.VariableInstance;
/*     */ import com.cabolabs.xre.core.logic.Function;
/*     */ import com.cabolabs.xre.core.logic.IfElse;
/*     */ import com.cabolabs.xre.core.logic.LogicBlock;
/*     */ import com.cabolabs.xre.core.logic.bool.BooleanFunction;
/*     */ import com.cabolabs.xre.core.resolution.VariableResolution;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.junit.After;
/*     */ import org.junit.AfterClass;
/*     */ import org.junit.Assert;
/*     */ import org.junit.Before;
/*     */ import org.junit.BeforeClass;
/*     */ import org.junit.Test;
/*     */ 
/*     */ public class ExecuterIfElseTest
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
/*  53 */     this.rule1 = new RuleDefinition("rule1.v1");
/*     */ 
/*  55 */     List declarations = new ArrayList();
/*  56 */     declarations.add(new VariableDeclaration("var1", DataType.INT32, "res1"));
/*  57 */     declarations.add(new VariableDeclaration("var2", DataType.INT32, "res2"));
/*     */ 
/*  59 */     this.rule1.setDeclarations(declarations);
/*     */ 
/*  62 */     List input = new ArrayList();
/*  63 */     input.add(new VariableDeclaration("param1", DataType.INT32));
/*     */ 
/*  65 */     this.rule1.setInput(input);
/*     */ 
/*  68 */     List resolutions = new ArrayList();
/*     */ 
/*  72 */     resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(0))
/*     */     {
/*     */       public VariableInstance getValue(String sessionId, List<VariableInstance> params)
/*     */       {
/*  78 */         return new VariableInstance(getDeclaration(), Integer.valueOf(1));
/*     */       }
/*     */     });
/*  81 */     resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(1))
/*     */     {
/*     */       public VariableInstance getValue(String sessionId, List<VariableInstance> params)
/*     */       {
/*  86 */         return new VariableInstance(getDeclaration(), Integer.valueOf(2));
/*     */       }
/*     */     });
/*  90 */     this.rule1.addAllToResolutions(resolutions);
/*     */ 
/*  93 */     List functions = new ArrayList();
/*  94 */     functions.add(
/*  96 */       new Function("resultadoSuma", DataType.INT32)
/*     */     {
/*     */       public List<VariableInstance> execute(String sessionId)
/*     */         throws Exception
/*     */       {
/* 110 */         RuleExecution re = Memory.getInstance().get(sessionId);
/*     */ 
/* 113 */         VariableInstance par1 = re.getValue((VariableDeclaration)this.params.get(0));
/* 114 */         VariableInstance par2 = re.getValue((VariableDeclaration)this.params.get(1));
/* 115 */         VariableInstance par3 = re.getValue((VariableDeclaration)this.params.get(2));
/*     */ 
/* 117 */         System.out.println(par1.getValue() + " + " + par2.getValue() + " + " + par3.getValue());
/*     */ 
/* 139 */         return functionReturnValue(Integer.valueOf(((Integer)par1.getValue()).intValue() + ((Integer)par2.getValue()).intValue() + ((Integer)par3.getValue()).intValue()), re);
/*     */       }
/*     */     });
/* 149 */     List blocks = new ArrayList();
/* 150 */     blocks.add(
/* 151 */       new IfElse(
/* 155 */       new BooleanFunction()
/*     */     {
/*     */       protected List<VariableDeclaration> params;
/*     */ 
/*     */       public List<VariableInstance> execute(String sessionId)
/*     */         throws Exception
/*     */       {
/* 169 */         RuleExecution re = Memory.getInstance().get(sessionId);
/*     */ 
/* 171 */         VariableInstance par1 = re.getValue((VariableDeclaration)this.params.get(0));
/* 172 */         VariableInstance par2 = re.getValue((VariableDeclaration)this.params.get(1));
/*     */ 
/* 174 */         System.out.println(par1.getValue() + " > " + par2.getValue());
/*     */ 
/* 178 */         return functionReturnValue(Boolean.valueOf(((Comparable)par1.getValue()).compareTo((Comparable)par2.getValue()) > 0), re);
/*     */       }
/*     */     }
/*     */     , new LogicBlock(functions, Arrays.asList(new String[] { "resultadoSuma" }))));
/*     */ 
/* 187 */     this.rule1.setLogic(
/* 188 */       new LogicBlock(blocks));
/*     */ 
/* 194 */     List params = new ArrayList();
/*     */ 
/* 198 */     params.add((VariableDeclaration)declarations.get(0));
/* 199 */     params.add((VariableDeclaration)declarations.get(1));
/* 200 */     params.add((VariableDeclaration)input.get(0));
/*     */ 
/* 202 */     ((Function)functions.get(0)).setParams(params);
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
/* 234 */     int input_value = 456;
/*     */ 
/* 236 */     Executer ex = Executer.getInstance();
/*     */ 
/* 240 */     String sessionId = ex.add(this.rule1);
/*     */ 
/* 244 */     if (!ex.contains(sessionId))
/*     */     {
/* 246 */       Assert.fail("1. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
/*     */     }
/*     */ 
/* 250 */     List params = null;
/* 251 */     if (!ex.init(sessionId, params))
/*     */     {
/* 253 */       for (Exception e : ex.getErrors(sessionId))
/*     */       {
/* 255 */         System.out.println(e.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 260 */     if (!ex.contains(sessionId))
/*     */     {
/* 262 */       Assert.fail("2. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
/*     */     }
/*     */ 
/* 266 */     List ex_input = new ArrayList();
/*     */ 
/* 269 */     ex_input.add(ex.createInstance(sessionId, "param1", new Integer(input_value)));
/*     */ 
/* 271 */     if (!ex.execute(sessionId, ex_input))
/*     */     {
/* 273 */       for (Exception e : ex.getErrors(sessionId))
/*     */       {
/* 275 */         System.out.println(e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/* 279 */     List results = ex.getResults(sessionId);
/*     */ 
/* 281 */     if (results.size() != 1) Assert.fail("Deberia haber un unico resultado y hay: " + results.size());
/*     */ 
/* 290 */     System.out.println(((VariableInstance)results.get(0)).getValue());
/*     */ 
/* 292 */     if (((Integer)((VariableInstance)results.get(0)).getValue()).intValue() != input_value + 1 + 2)
/*     */     {
/* 294 */       Assert.fail("El resultado esperado es: " + (input_value + 1 + 2) + " y se obtuvo: " + ((VariableInstance)results.get(0)).getValue());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.test.definitions.ExecuterIfElseTest
 * JD-Core Version:    0.6.2
 */