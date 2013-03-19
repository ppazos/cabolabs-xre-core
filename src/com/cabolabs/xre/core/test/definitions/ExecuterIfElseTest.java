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
import com.cabolabs.xre.core.logic.IfElse;
import com.cabolabs.xre.core.logic.LogicBlock;
import com.cabolabs.xre.core.logic.bool.BooleanFunction;
import com.cabolabs.xre.core.resolution.VariableResolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExecuterIfElseTest
{
   private RuleDefinition rule1;

   @BeforeClass
   public static void setUpBeforeClass() throws Exception
   {
   }

   @AfterClass
   public static void tearDownAfterClass()throws Exception
   {
   }

   @Before
   public void setUp() throws Exception
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
            return new VariableInstance(getDeclaration(), Integer.valueOf(321));  // Asigna valor a var1
         }
      });
      resolutions.add(new VariableResolution((VariableDeclaration)declarations.get(1))
      {
         public VariableInstance getValue(String sessionId, List<VariableInstance> params)
         {
            return new VariableInstance(getDeclaration(), Integer.valueOf(123)); // Asigna valor a var2
         }
      });
      this.rule1.addAllToResolutions(resolutions);

      List<AbstractBlock> functions = new ArrayList<AbstractBlock>();
      functions.add(
            new Function("resultadoSuma", DataType.INT32)
            {
               public List<VariableInstance> execute(String sessionId) throws Exception
               {
                  RuleExecution re = Memory.getInstance().get(sessionId);

                  VariableInstance par1 = re.getValue((VariableDeclaration)this.params.get(0));
                  VariableInstance par2 = re.getValue((VariableDeclaration)this.params.get(1));
                  VariableInstance par3 = re.getValue((VariableDeclaration)this.params.get(2));

                  System.out.println(par1.getValue() + " + " + par2.getValue() + " + " + par3.getValue());

                  return functionReturnValue(Integer.valueOf(((Integer)par1.getValue()).intValue() + ((Integer)par2.getValue()).intValue() + ((Integer)par3.getValue()).intValue()), re);
               }
            });
      List<AbstractBlock> blocks = new ArrayList<AbstractBlock>();
      blocks.add(
         new IfElse(
                  new BooleanFunction()
                  {
                     //protected List<VariableDeclaration> params; // Se setean abajo (params_if)

                     @SuppressWarnings({ "unchecked", "rawtypes" })
                     public List<VariableInstance> execute(String sessionId) throws Exception
                     {
                        RuleExecution re = Memory.getInstance().get(sessionId);

                        VariableInstance par1 = re.getValue((VariableDeclaration)this.params.get(0));
                        VariableInstance par2 = re.getValue((VariableDeclaration)this.params.get(1));

                        System.out.println(par1.getValue() + " > " + par2.getValue());

                        return functionReturnValue(Boolean.valueOf(((Comparable)par1.getValue()).compareTo((Comparable)par2.getValue()) > 0), re);
                     }
                  },
                  new LogicBlock(functions, Arrays.asList(new String[] { "resultadoSuma" })) // suma de 3
        )
     );

      this.rule1.setLogic(new LogicBlock(blocks));

      
      // Params para la suma de 3
      List<VariableDeclaration> params = new ArrayList<VariableDeclaration>();

      params.add((VariableDeclaration)declarations.get(0));
      params.add((VariableDeclaration)declarations.get(1));
      params.add((VariableDeclaration)input.get(0));

      ((Function)functions.get(0)).setParams(params);
      
      
      // Params para el if (condition >)
      List<VariableDeclaration> params_if = new ArrayList<VariableDeclaration>();

      params_if.add((VariableDeclaration)declarations.get(0));
      params_if.add((VariableDeclaration)declarations.get(1));

      ((IfElse)blocks.get(0)).getCondition().setParams(params_if);
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testExecute() throws Exception
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

      System.out.println(((VariableInstance)results.get(0)).getValue());

      if (((Integer)((VariableInstance)results.get(0)).getValue()).intValue() != input_value + 123 + 321)
      {
         Assert.fail("El resultado esperado es: " + (input_value + 123 + 321) + " y se obtuvo: " + ((VariableInstance)results.get(0)).getValue());
      }
   }
}