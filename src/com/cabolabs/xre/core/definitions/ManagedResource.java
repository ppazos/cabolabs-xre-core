package com.cabolabs.xre.core.definitions;

public abstract class ManagedResource
{
   String id;
   String concept;
   String version;
   String name;
   String description;
   String keywords;

   public String getId()
   {
      return this.id;
   }

   public String getConcept()
   {
      return this.concept;
   }

   public String getVersion()
   {
      return this.version;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getDescription() {
      return this.description;
   }

   public void setKeywords(String keywords)
   {
      this.keywords = keywords;
   }

   public String getKeywords() {
      return this.keywords;
   }
}