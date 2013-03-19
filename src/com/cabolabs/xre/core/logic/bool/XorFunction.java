package com.cabolabs.xre.core.logic.bool;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.List;

public class XorFunction extends BooleanFunction
{
   private BooleanFunction f1;
   private BooleanFunction f2;

   public XorFunction(String returnName, DataType returnType)
   {
      super(returnName, returnType);
   }

   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      RuleExecution re = Memory.getInstance().get(sessionId);

      List<VariableInstance> res1 = this.f1.execute(sessionId);
      List<VariableInstance> res2 = this.f2.execute(sessionId);

      return functionReturnValue(Boolean.valueOf(((Boolean)((VariableInstance)res1.get(0)).getValue()).booleanValue() ^ ((Boolean)((VariableInstance)res2.get(0)).getValue()).booleanValue()), re);
   }

   public void setBooleanFunctions(BooleanFunction f1, BooleanFunction f2) throws Exception
   {
      this.f1 = f1;
      this.f2 = f2;
   }
}