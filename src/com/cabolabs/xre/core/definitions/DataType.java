package com.cabolabs.xre.core.definitions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

public enum DataType
{
   BOOLEAN(Boolean.class), 
   INT32(Integer.class), 
   INT64(Long.class), 
   FLOAT32(Float.class), 
   FLOAT64(Double.class), 
   DECIMAL(BigDecimal.class), 
   STRING(String.class), 
   DATE(Date.class), 
   COLLECTION(Collection.class);

   public static final String BOOLEAN_TYPE = "boolean";
   public static final String INT32_TYPE = "int32";
   public static final String INT64_TYPE = "int64";
   public static final String FLOAT32_TYPE = "float32";
   public static final String FLOAT64_TYPE = "float64";
   public static final String DECIMAL_TYPE = "decimal";
   public static final String STRING_TYPE = "string";
   public static final String DATE_TYPE = "date";
   public static final String COLLECTION_TYPE = "collection";
   
   @SuppressWarnings("serial")
   private static Map<String, DataType> map1 = new HashMap<String, DataType>() {
   {
	  put(BOOLEAN_TYPE, BOOLEAN);
	  put(INT32_TYPE, INT32);
	  put(INT64_TYPE, INT64);
	  put(FLOAT32_TYPE, FLOAT32);
	  put(FLOAT64_TYPE, FLOAT64);
	  put(DECIMAL_TYPE, DECIMAL);
	  put(STRING_TYPE, STRING);
	  put(DATE_TYPE, DATE);
	  put(COLLECTION_TYPE, COLLECTION);
   }};

   @SuppressWarnings("serial")
   private static Map<DataType, String> map2 = new HashMap<DataType, String>() {{

      put(BOOLEAN, BOOLEAN_TYPE);
      put(INT32, INT32_TYPE);
      put(INT64, INT64_TYPE);
      put(FLOAT32, FLOAT32_TYPE);
      put(FLOAT64, FLOAT64_TYPE);
      put(DECIMAL, DECIMAL_TYPE);
      put(STRING, STRING_TYPE);
      put(DATE, DATE_TYPE);
      put(COLLECTION, COLLECTION_TYPE);
   }};

   @SuppressWarnings({ "rawtypes", "serial" })
   private static Map<Class, String> classType = new HashMap<Class, String>() {{
     
      put(Boolean.class,    BOOLEAN_TYPE);
      put(Integer.class,    INT32_TYPE);
      put(Long.class,       INT64_TYPE);
      put(Float.class,      FLOAT32_TYPE);
      put(Double.class,     FLOAT64_TYPE);
      put(BigDecimal.class, DECIMAL_TYPE);
      put(String.class,     STRING_TYPE);
      put(Date.class,       DATE_TYPE);
      
      // Collection y todas sus subclases concretas:
      // http://www.holub.com/goodies/java.collections.html
      put(Collection.class, COLLECTION_TYPE);
      put(LinkedList.class, COLLECTION_TYPE);
      put(ArrayList.class,  COLLECTION_TYPE);
      put(Vector.class,     COLLECTION_TYPE);
      put(HashSet.class,    COLLECTION_TYPE);
      put(TreeSet.class,    COLLECTION_TYPE);
   }};
   
   
   @SuppressWarnings("rawtypes")
   public final Class clazz;

   @SuppressWarnings("rawtypes")
   private DataType(Class clazz)
   {
      this.clazz = clazz;
   }

   public static DataType get(String type) throws Exception
   {
      DataType dt = (DataType)map1.get(type);
      if (dt == null) throw new Exception("el tipo '" + type + "' no es soportado");
      return dt;
   }

   @SuppressWarnings("rawtypes")
   public static DataType get(Class clazz) throws Exception
   {
      String type = (String)classType.get(clazz);
      if (type == null) throw new Exception("el tipo '" + clazz + "' no es soportado");

      DataType dt = (DataType)map1.get(type);
      return dt;
   }

   public String toString()
   {
      return (String)map2.get(this);
   }
}