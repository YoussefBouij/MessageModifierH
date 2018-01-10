package org.immregistries.dqa.message_modifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.message_modifier.script.CharStream;
import org.immregistries.dqa.message_modifier.script.Issue;
import org.immregistries.dqa.message_modifier.script.IssueType;
import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.script.NewScript;
import org.immregistries.dqa.message_modifier.script.SimpleNode;
import org.immregistries.dqa.message_modifier.transform.Command;
import org.immregistries.dqa.message_modifier.transform.SetCommand;
import org.immregistries.dqa.message_modifier.transform.CallCommand;

public class ModifierService {
  public ModifierService() {
    // anything to do?
  }

  public void modify(ModificationDetails modifyRequest) {
    String messageText = modifyRequest.getMessageOriginal();
    List<Command> commandList = new ArrayList<>();
    try {

    		NewScript parser = new NewScript(new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8")));
        SimpleNode n = parser.ExpressionList();
        n.testPrint();      
        commandList = n.createCommandList(messageText);
      
      // Need to generate a list of commands

      modifyRequest.setMessageFinal(modifyRequest.getMessageOriginal());
      
      for (Command command : commandList) {
        if (command instanceof SetCommand) {
          command.doTransform(modifyRequest);
          
        }else if (command instanceof CallCommand) {
            command.doTransform(modifyRequest); 
          } 
      }
    } catch (Exception ioe) {
    		Issue issue = new Issue(IssueType.Error,ioe.getMessage());
    		modifyRequest.addIssue(issue);
		System.out.println(issue);
      ioe.printStackTrace();
    }
   // modifyRequest.setMessageFinal(messageText);
  }

}
