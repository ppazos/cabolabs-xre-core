package com.cabolabs.xre.core.logic;

import com.cabolabs.xre.core.definitions.DataType;
import com.cabolabs.xre.core.definitions.VariableDeclaration;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public abstract class Function extends AbstractBlock
{
   protected String returnName = null;
   protected DataType returnType = null;
   protected List<VariableDeclaration> params;
   private static SecureRandom random = new SecureRandom();

   public Function(String returnName, DataType returnType)
   {
      if (returnName == null) returnName = new BigInteger(130, random).toString(32);

      this.returnName = returnName;
      this.returnType = returnType;
   }

   public VariableInstance returnValue(Object value, RuleExecution re) throws Exception
   {
      if (this.returnType == null)
      {
         this.returnType = DataType.get(value.getClass());
      }

      VariableDeclaration vd = re.getDeclaration(this.returnName);

      if (vd == null)
      {
         return new VariableInstance(new VariableDeclaration(this.returnName, this.returnType), value);
      }

      if (vd.getType() != this.returnType)
      {
         throw new Exception("La funcion " + getClass().getSimpleName() + " asigna un tipo de retorno (" + this.returnType + ") distinto al tipo (" + vd.getType() + ") de la variable declarada '" + vd.getName() + "'");
      }

      return new VariableInstance(vd, value);
   }

   public List<VariableInstance> functionReturnValue(Object value, RuleExecution re) throws Exception
   {
      List<VariableInstance> ret = new ArrayList<VariableInstance>();
      ret.add(returnValue(value, re));
      return ret;
   }

   public void setReturnName(String returnName)
   {
      this.returnName = returnName;
   }

   public String getReturnName() {
      return this.returnName;
   }

   public void setReturnType(DataType returnType)
   {
      this.returnType = returnType;
   }

   public DataType getReturnType() {
      return this.returnType;
   }

   public void setParams(List<VariableDeclaration> params)
   {
      this.params = params;
   }
}