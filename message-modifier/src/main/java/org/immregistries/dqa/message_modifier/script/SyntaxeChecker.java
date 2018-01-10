package org.immregistries.dqa.message_modifier.script;

import java.io.ByteArrayInputStream;


public class SyntaxeChecker {
	
	private String modificationScript;
	
	
	public void checkSyntax(String modificationScript) {
	    ModificationDetails modifyRequest = new ModificationDetails();
	    modifyRequest.setModificationScript(modificationScript);
	    
	    try {
	    NewScript parser = new NewScript(new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8")));
        SimpleNode n = parser.ExpressionList();
        System.out.println("The script syntaxe is valid");
	}catch (Exception ioe) {
		Issue issue = new Issue(IssueType.Error,ioe.getMessage());
		modifyRequest.addIssue(issue);
	System.out.println(issue);
}

	    
}
}