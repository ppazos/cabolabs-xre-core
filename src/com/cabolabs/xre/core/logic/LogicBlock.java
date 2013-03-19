package com.cabolabs.xre.core.logic;

import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.ArrayList;
import java.util.List;

public class LogicBlock extends AbstractBlock
{
   private List<AbstractBlock> blocks;
   private List<String> returnNames;

   public LogicBlock()
   {
      this.blocks = new ArrayList<AbstractBlock>();
      this.returnNames = new ArrayList<String>();
   }

   public LogicBlock(List<AbstractBlock> blocks)
   {
      this.blocks = blocks;
      this.returnNames = new ArrayList<String>();
   }

   public LogicBlock(List<AbstractBlock> blocks, List<String> returnNames)
   {
      this.blocks = blocks;

      if (returnNames == null) returnNames = new ArrayList<String>();

      this.returnNames = returnNames;
   }

   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      if (this.blocks.size() == 0)
      {
         throw new Exception("LogicBlock debe tener algun bloque para ejecutar y no tiene ninguno");
      }

      RuleExecution re = Memory.getInstance().get(sessionId);

      List<VariableInstance> resParcial = new ArrayList<VariableInstance>();

      for (AbstractBlock block : this.blocks)
      {
         resParcial = block.execute(sessionId);

         if (resParcial != null)
         {
            re.addValues(resParcial);
         }
      }

      List<VariableInstance> res = new ArrayList<VariableInstance>();

      for (String name : this.returnNames)
      {
         VariableInstance retval = re.getValue(name);

         if (retval == null)
         {
            throw new Exception("No existe el valor con nombre '" + name + "' para retornar, verifique que el nombre corresponda a una de las variables declaradas en la regla " + re.getRule().getId());
         }

         re.addToResults(retval);

         res.add(retval);
      }

      return res;
   }

   public void addToBlocks(AbstractBlock block) throws Exception
   {
      if ((block instanceof LogicBlock))
      {
         throw new Exception("LogicBlock no debe contener otros LogicBlocks, solo puede contener IfElse, ActionBlock o Function");
      }

      this.blocks.add(block);
   }

   public void addToReturnNames(String name)
   {
      this.returnNames.add(name);
   }
}