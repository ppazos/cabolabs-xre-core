/*     */ package com.cabolabs.xre.core.logic;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.RuleDefinition;
/*     */ import com.cabolabs.xre.core.execution.Memory;
/*     */ import com.cabolabs.xre.core.execution.RuleExecution;
/*     */ import com.cabolabs.xre.core.execution.VariableInstance;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class LogicBlock extends AbstractBlock
/*     */ {
/*     */   private List<AbstractBlock> blocks;
/*     */   private List<String> returnNames;
/*     */ 
/*     */   public LogicBlock()
/*     */   {
/*  23 */     this.blocks = new ArrayList();
/*  24 */     this.returnNames = new ArrayList();
/*     */   }
/*     */ 
/*     */   public LogicBlock(List<AbstractBlock> blocks)
/*     */   {
/*  29 */     this.blocks = blocks;
/*  30 */     this.returnNames = new ArrayList();
/*     */   }
/*     */ 
/*     */   public LogicBlock(List<AbstractBlock> blocks, List<String> returnNames)
/*     */   {
/*  35 */     this.blocks = blocks;
/*     */ 
/*  37 */     if (returnNames == null) returnNames = new ArrayList();
/*     */ 
/*  39 */     this.returnNames = returnNames;
/*     */   }
/*     */ 
/*     */   public List<VariableInstance> execute(String sessionId)
/*     */     throws Exception
/*     */   {
/*  58 */     if (this.blocks.size() == 0)
/*     */     {
/*  60 */       throw new Exception("LogicBlock debe tener algun bloque para ejecutar y no tiene ninguno");
/*     */     }
/*     */ 
/*  64 */     RuleExecution re = Memory.getInstance().get(sessionId);
/*     */ 
/*  67 */     List resParcial = new ArrayList();
/*     */ 
/*  69 */     for (AbstractBlock block : this.blocks)
/*     */     {
/*  76 */       resParcial = block.execute(sessionId);
/*     */ 
/*  83 */       if (resParcial != null)
/*     */       {
/*  85 */         re.addValues(resParcial);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  97 */     List res = new ArrayList();
/*     */ 
/*  99 */     for (String name : this.returnNames)
/*     */     {
/* 101 */       VariableInstance retval = re.getValue(name);
/*     */ 
/* 107 */       if (retval == null)
/*     */       {
/* 109 */         throw new Exception("No existe el valor con nombre '" + name + "' para retornar, verifique que el nombre corresponda a una de las variables declaradas en la regla " + re.getRule().getId());
/*     */       }
/*     */ 
/* 118 */       re.addToResults(retval);
/*     */ 
/* 121 */       res.add(retval);
/*     */     }
/*     */ 
/* 124 */     return res;
/*     */   }
/*     */ 
/*     */   public void addToBlocks(AbstractBlock block) throws Exception
/*     */   {
/* 129 */     if ((block instanceof LogicBlock))
/*     */     {
/* 131 */       throw new Exception("LogicBlock no debe contener otros LogicBlocks, solo puede contener IfElse, ActionBlock o Function");
/*     */     }
/*     */ 
/* 134 */     this.blocks.add(block);
/*     */   }
/*     */ 
/*     */   public void addToReturnNames(String name)
/*     */   {
/* 139 */     this.returnNames.add(name);
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.LogicBlock
 * JD-Core Version:    0.6.2
 */