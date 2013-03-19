package com.cabolabs.xre.core.resolution;

import com.cabolabs.xre.core.definitions.VariableDeclaration;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.ArrayList;
import java.util.List;

public abstract class VariableResolution
{
   private VariableDeclaration declaration;
   private List<Exception> errors = new ArrayList<Exception>();

   public VariableResolution(VariableDeclaration declaration)
   {
      this.declaration = declaration;
   }

   public abstract VariableInstance getValue(String paramString, List<VariableInstance> paramList);

   public VariableDeclaration getDeclaration()
   {
      return this.declaration;
   }

   public List<Exception> getErrors()
   {
      return this.errors;
   }
}