package com.cabolabs.xre.core.test.definitions;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.definitions.RuleDefinition;
import com.cabolabs.xre.core.definitions.VariableDeclaration;
import com.cabolabs.xre.core.execution.Executer;
import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import com.cabolabs.xre.core.logic.AbstractBlock;
import com.cabolabs.xre.core.logic.Function;
import com.cabolabs.xre.core.logic.LogicBlock;
import com.cabolabs.xre.core.resolution.VariableResolution;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExecuterTest
{
   private RuleDefinition rule1;

   @BeforeClass
   public static void setUpBeforeClass()
   throws Exception
   {
   }

   @AfterClass
   public static void tearDownAfterClass()
   throws Exception
   {
   }

   @Before
   public void setUp()
   throws Exception
   {
      this.rule1 = new RuleDefinition("rule1.v1");

      List<VariableDeclaration> declarations = new ArrayList<VariableDeclaration>();
      declarations.add(new VariableDeclaration("var1", DataType.INT32, "res1"));
      declarations.add(new VariableDeclaration("var2", DataType.INT32, "res2"));

      this.rule1.setDeclarations(declarations);

      List<VariableDeclaration> input = new ArrayList<VariableDeclaration>();
      input.add(new VariableDeclaration("param1", DataType.INT32));

      this.rule1.setInput(input);

      List<VariableResolution> resolutions = new ArrayList<VariableResolution>();

      resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(0))
      {
         public VariableInstance getValue(String sessionId, List<VariableInstance> params)
         {
            return new VariableInstance(getDeclaration(), Integer.valueOf(1));
         }
      });
      resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(1))
      {
         public VariableInstance getValue(String sessionId, List<VariableInstance> params)
         {
            return new VariableInstance(getDeclaration(), Integer.valueOf(2));
         }
      });
      this.rule1.addAllToResolutions(resolutions);

      List<AbstractBlock> functions = new ArrayList<AbstractBlock>();
      functions.add(
      new Function("resultadoSuma", DataType.INT32)
      {
         public List<VariableInstance> execute(String sessionId)
         throws Exception
         {
            RuleExecution re = Memory.getInstance().get(sessionId);

            VariableInstance par1 = re.getValue(((VariableDeclaration)this.params.get(0)).getName());
            VariableInstance par2 = re.getValue(((VariableDeclaration)this.params.get(1)).getName());
            VariableInstance par3 = re.getValue(((VariableDeclaration)this.params.get(2)).getName());

            return functionReturnValue(Integer.valueOf(((Integer)par1.getValue()).intValue() + ((Integer)par2.getValue()).intValue() + ((Integer)par3.getValue()).intValue()), re);
         }
      });
      List<String> returnNames = new ArrayList<String>();
      returnNames.add("resultadoSuma");

      this.rule1.setLogic(
      new LogicBlock(functions, returnNames));

      List<VariableDeclaration> params = new ArrayList<VariableDeclaration>();

      params.add((VariableDeclaration)declarations.get(0));
      params.add((VariableDeclaration)declarations.get(1));
      params.add((VariableDeclaration)input.get(0));

      ((Function)functions.get(0)).setParams(params);
   }

   @After
   public void tearDown()
   throws Exception
   {
   }

   @Test
   public void testExecute()
   throws Exception
   {
      int input_value = 456;

      Executer ex = Executer.getInstance();

      String sessionId = ex.add(this.rule1);

      if (!ex.contains(sessionId))
      {
         Assert.fail("1. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
      }

      List<VariableInstance> params = null;
      if (!ex.init(sessionId, params))
      {
         for (Exception e : ex.getErrors(sessionId))
         {
            System.out.println(e.getMessage());
         }

      }

      if (!ex.contains(sessionId))
      {
         Assert.fail("2. Executer doesn't contain a rule execution with id: " + sessionId + " solo contiene los ids: " + ex.getExecutionIds().toString());
      }

      List<VariableInstance> ex_input = new ArrayList<VariableInstance>();

      ex_input.add(ex.createInstance(sessionId, "param1", new Integer(input_value)));

      if (!ex.execute(sessionId, ex_input))
      {
         for (Exception e : ex.getErrors(sessionId))
         {
            System.out.println(e.getMessage());
         }
      }

      List<VariableInstance> results = ex.getResults(sessionId);

      if (results.size() != 1) Assert.fail("Deberia haber un unico resultado y hay: " + results.size());

      if (((Integer)((VariableInstance)results.get(0)).getValue()).intValue() != input_value + 1 + 2)
      {
         Assert.fail("El resultado esperado es: " + (input_value + 1 + 2) + " y se obtuvo: " + ((VariableInstance)results.get(0)).getValue());
      }
   }
}