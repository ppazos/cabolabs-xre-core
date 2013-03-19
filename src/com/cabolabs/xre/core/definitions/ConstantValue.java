package com.cabolabs.xre.core.definitions;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ConstantValue extends VariableDeclaration
{
   private static SecureRandom random = new SecureRandom();
   protected Object value;

   public ConstantValue(String name, DataType type, String namespace, Object value)
   {
      super(name, type, namespace);
      this.value = value;
   }

   public ConstantValue(String name, DataType type, Object value)
   {
      super(name, type);
      this.value = value;
   }

   public ConstantValue(DataType type, String namespace, Object value)
   {
      super(new BigInteger(130, random).toString(32), type, namespace);
      this.value = value;
   }

   public ConstantValue(DataType type, Object value)
   {
      super(new BigInteger(130, random).toString(32), type);
      this.value = value;
   }

   public Object getValue()
   {
      return this.value;
   }

   public static ConstantValue fromValue(Object value) throws Exception
   {
      ConstantValue cval = new ConstantValue(
            DataType.get(value.getClass()), 
            value);

      return cval;
   }
}