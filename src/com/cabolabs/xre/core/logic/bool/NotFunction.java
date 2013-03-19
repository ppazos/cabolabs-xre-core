package com.cabolabs.xre.core.logic.bool;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.List;

public class NotFunction extends BooleanFunction
{
   private BooleanFunction f;

   public NotFunction(String returnName, DataType returnType)
   {
      super(returnName, returnType);
   }

   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      RuleExecution re = Memory.getInstance().get(sessionId);

      List<VariableInstance> res = this.f.execute(sessionId);

      return functionReturnValue(Boolean.valueOf(BooleanFunction.isFalse((VariableInstance)res.get(0))), re);
   }

   public void setBooleanFunction(BooleanFunction f) throws Exception
   {
      if (f == null)
      {
         throw new Exception("Se esperan 1 funcion booleana para evaluar el NOT");
      }

      this.f = f;
   }
}