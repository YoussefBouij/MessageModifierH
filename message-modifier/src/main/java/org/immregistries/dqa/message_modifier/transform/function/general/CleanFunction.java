package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;

public class CleanFunction implements CallFunction {
	
	public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException{
	    String resultText = modifyRequest.getMessageFinal();
	    Boolean noLastSlash = false;
	    if(callCommand.getParameterMap().size() > 0){
	    	noLastSlash = Boolean.valueOf(callCommand.getParameterMap().get("NO LAST SLASH"));
	    }
	    BufferedReader inResult = new BufferedReader(new StringReader(resultText));
	    resultText = "";
	    String lineResult;
	    while ((lineResult = inResult.readLine()) != null) {
	      lineResult = lineResult.trim();
	      if (lineResult.length() > 0) {
	        String finalLine = "";

	        String headerStart = null;
	        if (lineResult.startsWith("MSH|^~\\&|") || lineResult.startsWith("BHS|^~\\&|") || lineResult.startsWith("FHS|^~\\&|")) {
	          headerStart = lineResult.substring(0, 9);
	          lineResult = lineResult.substring(9);
	        }
	        
	        boolean foundFieldData = false;
	        boolean foundCompData = false;
	        boolean foundRepData = false;

	        for (int i = lineResult.length() - 1; i >= 0; i--) {
	          char c = lineResult.charAt(i);

	          if (!foundFieldData) {
	            if (c != '|' && c != '^' && c != '~') {
	              foundFieldData = true;
	              foundRepData = true;
	              foundCompData = true;
	            }
	          } else if (!foundRepData) {
	            if (c != '^' && c != '~') {
	              foundRepData = true;
	              foundCompData = true;
	            }
	          } else if (!foundCompData) {
	            if (c != '^') {
	              foundCompData = true;
	            }
	          }
	          if (foundFieldData) {
	              if (c == '|') {
	                foundRepData = false;
	                foundCompData = false;
	                finalLine = c + finalLine;
	              } else if (c == '~') {
	                if (foundRepData) {
	                  finalLine = c + finalLine;
	                }
	                foundCompData = false;
	              } else if (c == '^') {
	                if (foundCompData) {
	                  finalLine = c + finalLine;
	                }
	              } else {
	                finalLine = c + finalLine;
	              }
	            }
	          }
	        if (noLastSlash) {
	            resultText += finalLine + modifyRequest.getSegmentSeparator();
	          } else {
	            resultText += finalLine + "|" + modifyRequest.getSegmentSeparator();
	          }

	          if (headerStart != null) {
	            resultText = headerStart + resultText;
	          }
	        }
	      }
	      modifyRequest.setMessageFinal(resultText);
	}

}
