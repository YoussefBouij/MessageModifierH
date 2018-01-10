package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;

public interface CallFunction {
  public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException;
}
