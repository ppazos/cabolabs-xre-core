package com.cabolabs.xre.core.definitions;

import com.cabolabs.xre.core.logic.LogicBlock;
import com.cabolabs.xre.core.resolution.VariableResolution;
import java.util.ArrayList;
import java.util.List;

public class RuleDefinition extends ManagedResource
{
   List<VariableDeclaration> input;
   List<VariableDeclaration> declarations;
   List<VariableResolution> resolutions;
   LogicBlock logic;

   public RuleDefinition(String id) throws Exception
   {
      this.id = id;

      int idx = id.lastIndexOf(".v");

      if (idx < 0)
      {
         throw new Exception("The rule identifier should include the version number, like rule_name.v1.2.3, and the passed rule id is: " + id);
      }

      this.concept = id.substring(0, idx);
      this.version = id.substring(idx + 1);

      this.resolutions = new ArrayList<VariableResolution>();
   }

   public void setInput(List<VariableDeclaration> input)
   {
      this.input = input;
   }

   public List<VariableDeclaration> getInput() {
      return this.input;
   }

   public void setDeclarations(List<VariableDeclaration> declarations)
   {
      this.declarations = declarations;
   }

   public List<VariableDeclaration> getDeclarations() {
      return this.declarations;
   }

   public VariableDeclaration getDeclaration(String name)
   {
      for (VariableDeclaration vd : this.declarations)
      {
         if (vd.getName().equals(name))
         {
            return vd;
         }
      }
      for (VariableDeclaration vd : this.input)
      {
         if (vd.getName().equals(name))
         {
            return vd;
         }
      }
      return null;
   }

   public void addToResolutions(VariableResolution resolution)
   {
      this.resolutions.add(resolution);
   }

   public void addAllToResolutions(List<VariableResolution> resolutions)
   {
      this.resolutions.addAll(resolutions);
   }

   public List<VariableResolution> getResolutions()
   {
      return this.resolutions;
   }

   public void setLogic(LogicBlock logic)
   {
      this.logic = logic;
   }

   public LogicBlock getLogic() {
      return this.logic;
   }

   public boolean hasVariable(String name)
   {
      for (VariableDeclaration v : this.input)
      {
         if (v.getName().equals(name)) return true;
      }
      for (VariableDeclaration v : this.declarations)
      {
         if (v.getName().equals(name)) return true;
      }
      return false;
   }
}