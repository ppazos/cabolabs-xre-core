/*     */ package com.cabolabs.xre.core.logic;
/*     */ 
/*     */ import com.cabolabs.xre.core.definitions.DataType;
/*     */ import com.cabolabs.xre.core.definitions.VariableDeclaration;
/*     */ import com.cabolabs.xre.core.execution.RuleExecution;
/*     */ import com.cabolabs.xre.core.execution.VariableInstance;
/*     */ import java.math.BigInteger;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class Function extends AbstractBlock
/*     */ {
/*  46 */   protected String returnName = null;
/*  47 */   protected DataType returnType = null;
/*     */   protected List<VariableDeclaration> params;
/*  61 */   private static SecureRandom random = new SecureRandom();
/*     */ 
/*     */   public Function(String returnName, DataType returnType)
/*     */   {
/*  69 */     if (returnName == null) returnName = new BigInteger(130, random).toString(32);
/*     */ 
/*  71 */     this.returnName = returnName;
/*  72 */     this.returnType = returnType;
/*     */   }
/*     */ 
/*     */   public VariableInstance returnValue(Object value, RuleExecution re)
/*     */     throws Exception
/*     */   {
/*  90 */     if (this.returnType == null)
/*     */     {
/*  93 */       this.returnType = DataType.get(value.getClass());
/*     */     }
/*     */ 
/*  99 */     VariableDeclaration vd = re.getDeclaration(this.returnName);
/*     */ 
/* 102 */     if (vd == null)
/*     */     {
/* 104 */       return new VariableInstance(new VariableDeclaration(this.returnName, this.returnType), value);
/*     */     }
/*     */ 
/* 108 */     if (vd.getType() != this.returnType)
/*     */     {
/* 110 */       throw new Exception("La funcion " + getClass().getSimpleName() + " asigna un tipo de retorno (" + this.returnType + ") distinto al tipo (" + vd.getType() + ") de la variable declarada '" + vd.getName() + "'");
/*     */     }
/*     */ 
/* 120 */     return new VariableInstance(vd, value);
/*     */   }
/*     */ 
/*     */   public List<VariableInstance> functionReturnValue(Object value, RuleExecution re)
/*     */     throws Exception
/*     */   {
/* 137 */     List ret = new ArrayList();
/* 138 */     ret.add(returnValue(value, re));
/* 139 */     return ret;
/*     */   }
/*     */ 
/*     */   public void setReturnName(String returnName)
/*     */   {
/* 144 */     this.returnName = returnName;
/*     */   }
/*     */ 
/*     */   public String getReturnName() {
/* 148 */     return this.returnName;
/*     */   }
/*     */ 
/*     */   public void setReturnType(DataType returnType)
/*     */   {
/* 153 */     this.returnType = returnType;
/*     */   }
/*     */ 
/*     */   public DataType getReturnType() {
/* 157 */     return this.returnType;
/*     */   }
/*     */ 
/*     */   public void setParams(List<VariableDeclaration> params)
/*     */   {
/* 166 */     this.params = params;
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.Function
 * JD-Core Version:    0.6.2
 */