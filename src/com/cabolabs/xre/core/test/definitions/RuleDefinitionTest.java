/*    */ package com.cabolabs.xre.core.test.definitions;
/*    */ 
/*    */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*    */ import org.junit.After;
/*    */ import org.junit.AfterClass;
/*    */ import org.junit.Assert;
/*    */ import org.junit.Before;
/*    */ import org.junit.BeforeClass;
/*    */ import org.junit.Test;
/*    */ 
/*    */ public class RuleDefinitionTest
/*    */ {
/*    */   @BeforeClass
/*    */   public static void setUpBeforeClass()
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   @AfterClass
/*    */   public static void tearDownAfterClass()
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   @Before
/*    */   public void setUp()
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   @After
/*    */   public void tearDown()
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testRuleDefinition()
/*    */     throws Exception
/*    */   {
/* 61 */     String id = "un_concepto.v1.2.3";
/* 62 */     RuleDefinition rule = new RuleDefinition(id);
/* 63 */     if (rule.getId() != id)
/*    */     {
/* 65 */       Assert.fail("Error de id");
/*    */     }
/* 67 */     if (!rule.getConcept().equals("un_concepto"))
/*    */     {
/* 69 */       Assert.fail("getConcept deberia ser 'un_concepto' y es '" + rule.getConcept() + "'");
/*    */     }
/* 71 */     if (!rule.getVersion().equals("v1.2.3"))
/*    */     {
/* 73 */       Assert.fail("getVersion deberia ser 'v1.2.3' y es '" + rule.getVersion() + "'");
/*    */     }
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetId()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetConcept()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetVersion()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testSetName()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetName()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testSetDescription()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetDescription()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testSetKeywords()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Test
/*    */   public void testGetKeywords()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.test.definitions.RuleDefinitionTest
 * JD-Core Version:    0.6.2
 */