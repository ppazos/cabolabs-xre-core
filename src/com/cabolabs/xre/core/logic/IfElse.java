package com.cabolabs.xre.core.logic;

import com.cabolabs.xre.core.execution.VariableInstance;
import com.cabolabs.xre.core.logic.bool.BooleanFunction;
import java.util.ArrayList;
import java.util.List;

public class IfElse extends AbstractBlock
{
   private BooleanFunction condition;
   private AbstractBlock ifBlock;
   private AbstractBlock elseBlock;

   public IfElse(BooleanFunction condition, AbstractBlock ifBlock)
   {
      this.condition = condition;
      this.ifBlock = ifBlock;
   }

   public IfElse(BooleanFunction condition, AbstractBlock ifBlock, AbstractBlock elseBlock)
   {
      this.condition = condition;
      this.ifBlock = ifBlock;
      this.elseBlock = elseBlock;
   }

   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      List<VariableInstance> bool = this.condition.execute(sessionId);

      if (((Boolean)((VariableInstance)bool.get(0)).getValue()).booleanValue())
      {
         System.out.println("IF");
         return this.ifBlock.execute(sessionId);
      }
      if (this.elseBlock != null)
      {
         System.out.println("ELSE");
         return this.elseBlock.execute(sessionId);
      }

      System.out.println("IF sin else");
      return new ArrayList<VariableInstance>(); // No retorna nada, los resultados estan en memoria
   }
   
   public BooleanFunction getCondition()
   {
      return this.condition;
   }
}