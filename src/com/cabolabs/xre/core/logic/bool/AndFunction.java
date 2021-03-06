package com.cabolabs.xre.core.logic.bool;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.List;

public class AndFunction extends BooleanFunction
{
   private List<BooleanFunction> fs;

   public AndFunction(String returnName, DataType returnType)
   {
      super(returnName, returnType);
   }

   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      RuleExecution re = Memory.getInstance().get(sessionId);

      for (BooleanFunction bf : this.fs)
      {
         List<VariableInstance> res = bf.execute(sessionId);
         if (BooleanFunction.isFalse((VariableInstance)res.get(0)))
         {
            return functionReturnValue(Boolean.FALSE, re);
         }
      }

      return functionReturnValue(Boolean.TRUE, re);
   }

   public void setBooleanFunctions(List<BooleanFunction> fs) throws Exception
   {
      if ((fs == null) || (fs.size() < 2))
      {
         throw new Exception("Se esperan 2 o mas funciones booleanas para evaluar el AND");
      }

      this.fs = fs;
   }
}