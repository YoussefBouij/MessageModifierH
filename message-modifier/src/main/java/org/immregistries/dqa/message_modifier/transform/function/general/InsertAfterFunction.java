package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;

public class InsertAfterFunction implements CallFunction {
	
	public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException{
		
		String resultText = modifyRequest.getMessageFinal();
		ReferenceParsed targetReference = callCommand.getTargetReference();
		int repeat = targetReference.getSegmentRepeat();
		int compteur = 1;
		
		String segID = callCommand.getParameterMap().get("SEGMENT ID");
		String segIDToCopyFrom = callCommand.getParameterMap().get("COPY VALUES FROM");

        BufferedReader inResult = new BufferedReader(new StringReader(resultText));
        String line = inResult.readLine();
        
        if(callCommand.getParameterMap().containsKey("COPY VALUES FROM")){
        	 
	        String lineToCopy = "";
	        
             while(line != null){
	        	if(line.startsWith(segIDToCopyFrom)){
	        		lineToCopy = line.substring(3, line.length());
	        	}
	        	line = inResult.readLine();
	        }
             inResult = new BufferedReader(new StringReader(resultText));
             resultText = "";
             line = inResult.readLine();
             
             while(line != null){
            	resultText += line + "\n";
             	if(line.startsWith(targetReference.getSegmentName())){
             		if(compteur == repeat){
             			resultText += segID + lineToCopy + "\n";
             		}
             		compteur++;
             	}
             	line = inResult.readLine();
             }
		}else{
			resultText = "";
	        while(line != null){
	        	resultText += line+"\n";
	        	if(line.startsWith(targetReference.getSegmentName())){
	        		if(compteur == repeat){
	        			resultText += segID + "|"+"\n";
	        		}
	        		compteur++;
	        	}
	        	line = inResult.readLine();
	        }
		}
        resultText.substring(0, resultText.length()-1);
		modifyRequest.setMessageFinal(resultText);
	}

}
