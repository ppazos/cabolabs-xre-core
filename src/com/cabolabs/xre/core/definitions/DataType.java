/*     */ package com.cabolabs.xre.core.definitions;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public enum DataType
/*     */ {
/*  14 */   BOOLEAN(
/*  15 */     Boolean.class), 
/*  16 */   INT32(Integer.class), 
/*  17 */   INT64(Long.class), 
/*  18 */   FLOAT32(Float.class), 
/*  19 */   FLOAT64(Double.class), 
/*  20 */   DECIMAL(BigDecimal.class), 
/*  21 */   STRING(String.class), 
/*  22 */   DATE(Date.class), 
/*  23 */   COLLECTION(Collection.class);
/*     */ 
/*     */   public static final String BOOLEAN_TYPE = "boolean";
/*     */   public static final String INT32_TYPE = "int32";
/*     */   public static final String INT64_TYPE = "int64";
/*     */   public static final String FLOAT32_TYPE = "float32";
/*     */   public static final String FLOAT64_TYPE = "float64";
/*     */   public static final String DECIMAL_TYPE = "decimal";
/*     */   public static final String STRING_TYPE = "string";
/*     */   public static final String DATE_TYPE = "date";
/*     */   public static final String COLLECTION_TYPE = "collection";
/*  37 */   private static Map<String, DataType> map1 = new HashMap() { } ;
/*     */ 
/*  50 */   private static Map<DataType, String> map2 = new HashMap() { } ;
/*     */ 
/*  67 */   private static Map<Class, String> classType = new HashMap() { } ;
/*     */   public final Class clazz;
/*     */ 
/*     */   private DataType(Class clazz)
/*     */   {
/*  89 */     this.clazz = clazz;
/*     */   }
/*     */ 
/*     */   public static DataType get(String type)
/*     */     throws Exception
/*     */   {
/*  97 */     DataType dt = (DataType)map1.get(type);
/*  98 */     if (dt == null) throw new Exception("el tipo '" + type + "' no es soportado");
/*  99 */     return dt;
/*     */   }
/*     */ 
/*     */   public static DataType get(Class clazz)
/*     */     throws Exception
/*     */   {
/* 105 */     String type = (String)classType.get(clazz);
/* 106 */     if (type == null) throw new Exception("el tipo '" + clazz + "' no es soportado");
/*     */ 
/* 108 */     DataType dt = (DataType)map1.get(type);
/* 109 */     return dt;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 118 */     return (String)map2.get(this);
/*     */   }
/*     */ }

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.definitions.DataType
 * JD-Core Version:    0.6.2
 */