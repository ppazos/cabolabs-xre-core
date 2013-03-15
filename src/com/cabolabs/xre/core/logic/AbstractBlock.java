package com.cabolabs.xre.core.logic;

import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.List;

public abstract class AbstractBlock
{
  public abstract List<VariableInstance> execute(String paramString)
    throws Exception;
}

/* Location:           C:\Documents and Settings\Administrator\My Documents\workspace\rule-engine\lib\rule-engine-core.jar
 * Qualified Name:     com.cabolabs.xre.core.logic.AbstractBlock
 * JD-Core Version:    0.6.2
 */