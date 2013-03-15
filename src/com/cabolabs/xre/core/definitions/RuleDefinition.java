/*     */ package com.cabolabs.xre.core.definitions;
/*     */ 
/*     */ import com.cabolabs.xre.core.logic.LogicBlock;
/*     */ import com.cabolabs.xre.core.resolution.VariableResolution;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RuleDefinition extends ManagedResource
/*     */ {
/*     */   List<VariableDeclaration> input;
/*     */   List<VariableDeclaration> declarations;
/*     */   List<VariableResolution> resolutions;
/*     */   LogicBlock logic;
/*     */ 
/*     */   public RuleDefinition(String id)
/*     */     throws Exception
/*     */   {
/*  43 */     this.id = id;
/*     */ 
/*  46 */     int idx = id.lastIndexOf(".v");
/*     */ 
/*  48 */     if (idx < 0)
/*     */     {
/*  50 */       throw new Exception("The rule identifier should include the version number, like rule_name.v1.2.3, and the passed rule id is: " + id);
/*     */     }
/*     */ 
/*  53 */     this.concept = id.substring(0, idx);
/*  54 */     this.version = id.substring(idx + 1);
/*     */ 
/*  56 */     this.resolutions = new ArrayList();
/*     */   }
/*     */ 
/*     */   public void setInput(List<VariableDeclaration> input)
/*     */   {
/*  62 */     this.input = input;
/*     */   }
/*     */ 
/*     */   public List<VariableDeclaration> getInput() {
/*  66 */     return this.input;
/*     */   }
/*     */ 
/*     */   public void setDeclarations(List<VariableDeclaration> declarations)
/*     */   {
/*  71 */     this.declarations = declarations;
/*     */   }
/*     */ 
/*     */   public List<VariableDeclaration> getDeclarations() {
/*  75 */     return this.declarations;
/*     */   }
/*     */ 
/*     */   public VariableDeclaration getDeclaration(String name)
/*     */   {
/*  85 */     for (VariableDeclaration vd : this.declarations)
/*     */     {
/*  87 */       if (vd.getName().equals(name))
/*     */       {
/*  89 */         return vd;
/*     */       }
/*     */     }
/*  92 */     for (VariableDeclaration vd : this.input)
/*     */     {
/*  94 */       if (vd.getName().equals(name))
/*     */       {
/*  96 */         return vd;
/*     */       }
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   public void addToResolutions(VariableResolution resolution)
/*     */   {
/* 105 */     this.resolutions.add(resolution);
/*     */   }
/*     */ 
/*     */   public void addAllToResolutions(List<VariableResolution> resolutions)
/*     */   {
/* 110 */     this.resolutions.addAll(resolutions);
/*     */   }
/*     */ 
/*     */   public List<VariableResolution> getResolutions()
/*     */   {
/* 115 */     return this.resolutions;
/*     */   }
/*     */ 
/*     */   public void setLogic(LogicBlock logic)
/*     */   {
/* 122 */     this.logic = logic;
/*     */   }
/*     */ 
/*     */   public LogicBlock getLogic() {
/* 126 */     return this.logic;
/*     */   }
/*     */ 
/*     */   public boolean hasVariable(String name)
/*     */   {
/* 138 */     for (VariableDeclaration v : this.input)
/*     */     {
/* 140 */       if (v.getName().equals(name)) return true;
/*     */     }
/* 142 */     for (VariableDeclaration v : this.declarations)
/*     */     {
/* 144 */       if (v.getName().equals(name)) return true;
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.RuleDefinition
 * JD-Core Version:    0.6.2
 */