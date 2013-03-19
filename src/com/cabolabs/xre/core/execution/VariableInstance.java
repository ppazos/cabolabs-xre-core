package com.cabolabs.xre.core.execution;

import com.cabolabs.xre.core.definitions.VariableDeclaration;

public class VariableInstance
{
   private VariableDeclaration declaration;
   private Object value;

   public VariableInstance(VariableDeclaration declaration)
   {
      this.declaration = declaration;
   }

   public VariableInstance(VariableDeclaration declaration, Object value) {
      this.declaration = declaration;
      this.value = value;
   }

   public VariableDeclaration getDeclaration()
   {
      return this.declaration;
   }

   public Object getValue() {
      return this.value;
   }
}