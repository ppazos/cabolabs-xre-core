package com.cabolabs.xre.core.test.definitions;

import com.cabolabs.xre.core.definitions.RuleDefinition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RuleDefinitionTest
{
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
   }

   @After
   public void tearDown()
   throws Exception
   {
   }

   @Test
   public void testRuleDefinition()
   throws Exception
   {
      String id = "un_concepto.v1.2.3";
      RuleDefinition rule = new RuleDefinition(id);
      if (rule.getId() != id)
      {
         Assert.fail("Error de id");
      }
      if (!rule.getConcept().equals("un_concepto"))
      {
         Assert.fail("getConcept deberia ser 'un_concepto' y es '" + rule.getConcept() + "'");
      }
      if (!rule.getVersion().equals("v1.2.3"))
      {
         Assert.fail("getVersion deberia ser 'v1.2.3' y es '" + rule.getVersion() + "'");
      }
   }

   @Test
   public void testGetId()
   {
   }

   @Test
   public void testGetConcept()
   {
   }

   @Test
   public void testGetVersion()
   {
   }

   @Test
   public void testSetName()
   {
   }

   @Test
   public void testGetName()
   {
   }

   @Test
   public void testSetDescription()
   {
   }

   @Test
   public void testGetDescription()
   {
   }

   @Test
   public void testSetKeywords()
   {
   }

   @Test
   public void testGetKeywords()
   {
   }
}