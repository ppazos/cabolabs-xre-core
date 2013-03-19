package com.cabolabs.xre.core.logic.bool;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.execution.VariableInstance;
import com.cabolabs.xre.core.logic.Function;

public abstract class BooleanFunction extends Function
{
   public static boolean isTrue(VariableInstance v)
   {
      return ((Boolean)v.getValue()).booleanValue();
   }

   public static boolean isFalse(VariableInstance v) {
      return !((Boolean)v.getValue()).booleanValue();
   }

   public BooleanFunction()
   {
      super(null, DataType.BOOLEAN);
   }

   public BooleanFunction(String returnName)
   {
      super(returnName, DataType.BOOLEAN);
   }

   public BooleanFunction(String returnName, DataType returnType)
   {
      super(returnName, DataType.BOOLEAN);
   }

   public void setReturnType(DataType returnType)
   {
   }
}