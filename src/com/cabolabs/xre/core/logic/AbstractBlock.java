package com.cabolabs.xre.core.logic;

import com.cabolabs.xre.core.execution.VariableInstance;
import java.util.List;

public abstract class AbstractBlock
{
   public abstract List<VariableInstance> execute(String paramString) throws Exception;
}