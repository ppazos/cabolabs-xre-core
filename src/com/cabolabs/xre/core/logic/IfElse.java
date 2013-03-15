/*    */ package com.cabolabs.xre.core.logic;
/*    */ 
/*    */ import com.cabolabs.xre.core.execution.VariableInstance;
/*    */ import com.cabolabs.xre.core.logic.bool.BooleanFunction;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class IfElse extends AbstractBlock
/*    */ {
/*    */   private BooleanFunction condition;
/*    */   private AbstractBlock ifBlock;
/*    */   private AbstractBlock elseBlock;
/*    */ 
/*    */   public IfElse(BooleanFunction condition, AbstractBlock ifBlock)
/*    */   {
/* 29 */     this.condition = condition;
/* 30 */     this.ifBlock = ifBlock;
/*    */   }
/*    */ 
/*    */   public IfElse(BooleanFunction condition, AbstractBlock ifBlock, AbstractBlock elseBlock)
/*    */   {
/* 35 */     this.condition = condition;
/* 36 */     this.ifBlock = ifBlock;
/* 37 */     this.elseBlock = elseBlock;
/*    */   }
/*    */ 
/*    */   public List<VariableInstance> execute(String sessionId)
/*    */     throws Exception
/*    */   {
/* 45 */     List bool = this.condition.execute(sessionId);
/*    */ 
/* 48 */     if (((Boolean)((VariableInstance)bool.get(0)).getValue()).booleanValue())
/*    */     {
/* 50 */       System.out.println("IF");
/* 51 */       return this.ifBlock.execute(sessionId);
/*    */     }
/* 53 */     if (this.elseBlock != null)
/*    */     {
/* 55 */       System.out.println("ELSE");
/* 56 */       return this.elseBlock.execute(sessionId);
/*    */     }
/*    */ 
/* 59 */     System.out.println("IF sin else");
/* 60 */     return new ArrayList();
/*    */   }
/*    */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.IfElse
 * JD-Core Version:    0.6.2
 */