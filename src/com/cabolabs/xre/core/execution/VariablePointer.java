package com.cabolabs.xre.core.execution;

public class VariablePointer
{
   private VariableInstance variable;

   public VariablePointer(VariableInstance var)
   {
      this.variable = var;
   }

   public VariableInstance getVariable()
   {
      return this.variable;
   }
}