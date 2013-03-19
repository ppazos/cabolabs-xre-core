package com.cabolabs.xre.core.definitions;

public class VariableDeclaration
{
   protected String name;
   protected DataType type;
   protected String namespace;

   public VariableDeclaration(String name, DataType type)
   {
      this.name = name;
      this.type = type;
   }

   public VariableDeclaration(String name, DataType type, String namespace)
   {
      this.name = name;
      this.type = type;
      this.namespace = namespace;
   }

   public String getName()
   {
      return this.name;
   }

   public DataType getType()
   {
      return this.type;
   }

   public String getNamespace()
   {
      return this.namespace;
   }
}