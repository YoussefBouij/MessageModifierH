package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;

public class InsertLastFunction implements CallFunction {
	
	public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException{
		String resultText = modifyRequest.getMessageFinal();
		ReferenceParsed targetReference = callCommand.getTargetReference();
		
		String segIDToInsert = callCommand.getParameterMap().get("SEGMENT ID");
		String segIDToCopyFrom = callCommand.getParameterMap().get("COPY VALUES FROM");
		
		 if(callCommand.getParameterMap().containsKey("COPY VALUES FROM")){
			
			BufferedReader inResult = new BufferedReader(new StringReader(resultText));
	        String line = inResult.readLine();
	        String lineToCopy = "";
	        
             while(line != null){
	        	if(line.startsWith(segIDToCopyFrom)){
	        		lineToCopy = line.substring(3, line.length()) + "\n" ;
	        	}
	        	line = inResult.readLine();
	        }
	        resultText = resultText + "\n" + segIDToInsert + lineToCopy ;
			
		}else{
			resultText = resultText + "\n"+ segIDToInsert + "|" + "\n";    
	} 
		modifyRequest.setMessageFinal(resultText);

	}
}


