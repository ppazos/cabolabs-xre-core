package com.cabolabs.xre.core.logic;

import java.util.ArrayList;
import java.util.List;

import com.cabolabs.xre.core.execution.Memory;
import com.cabolabs.xre.core.execution.RuleExecution;
import com.cabolabs.xre.core.execution.VariableInstance;

public class ActionBlock extends LogicBlock {

   private List<String> returnNames; // Nombres de los valores a retornar
   private List<Function> functions; // Funciones contenidas en este bloque, se respeta el orden de ejecucion
   
   
   public ActionBlock(List<Function> functions)
   {
      // TODO: functions no puede ser null o vacia
      this.functions = functions;
      this.returnNames = new ArrayList<String>();
   }
   
   public ActionBlock(List<Function> functions, List<String> returnNames)
   {
      // TODO: functions no puede ser null o vacia
      this.functions = functions;
      
      if (returnNames == null) returnNames = new ArrayList<String>();
      this.returnNames = returnNames;
   }
   
   
   @Override
   public List<VariableInstance> execute(String sessionId) throws Exception
   {
      // Guarda los valores de las variables de la ejecucion actual
      RuleExecution re = Memory.getInstance().get(sessionId);
      
      List<VariableInstance> res;
      VariableInstance retval;
      for (Function f: functions)
      {
         // Las funciones pueden retornar 0..1 valores
         res = f.execute(sessionId);
         
         // FIXME: en lugar de retornar un valor, la funcion podria modificar
         //        directamente el value de la VariableInstance que tenga su
         //        Function.returnName, pidiendo la variable a la memoria.
         //        Por ahora no lo hago asi.
         
         // Si hay valor de retorno
         if (res != null && res.size() == 1)
         {
            re.addValue(res.get(0)); // Agrega el resultado a la memoria, posiblemente sobreescribiendo un resultado anterior
         }
      }
      
      // Veo que valores retornar, y los traigo de la memoria
      res = new ArrayList<VariableInstance>();
      for (String name: this.returnNames)
      {
         retval = re.getValue(name);
         
         // Si no se obtuvo un valor con ese nombre, hay un error en la regla
         // porque se usa un nombre que no existe en la lista de valores
         // calculados en memoria de ejecucion y si se devuelve un valor
         // deberia existir como una instancia en memoria.
         if (retval == null)
         {
            String names = "";
            for (VariableInstance vi: re.getValues())
            {
               names += vi.getDeclaration().getName() + ", ";
            }
            if (names == "") names = "no hay variables declaradas";
            else names = names.substring(0, names.length()-2);
            
            throw new Exception("No existe el valor con nombre '"+ name +"' para retornar del ActionBlock, verifique que el nombre corresponda a una de las variables declaradas: "+ names);
         }
         
         res.add( retval );
      }
      
      return res;
   }

}