package org.immregistries.dqa.message_modifier.legacy.procedure;

import java.io.IOException;
import java.util.LinkedList;

import org.immregistries.dqa.message_modifier.legacy.TransformRequest;
import org.immregistries.dqa.message_modifier.legacy.Transformer;

public interface ProcedureInterface
{
  public void doProcedure(TransformRequest transformRequest, LinkedList<String> tokenList) throws IOException ;
  
  public void setTransformer(Transformer transformer);
}
