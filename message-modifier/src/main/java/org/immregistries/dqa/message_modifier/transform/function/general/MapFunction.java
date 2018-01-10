package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;
import org.immregistries.dqa.message_modifier.transform.SetCommand;

public class MapFunction implements CallFunction {
	
	public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException{

		ReferenceParsed targetReference = callCommand.getTargetReference();
		
		Iterator<Entry<String, String>> it = callCommand.getParameterMap().entrySet().iterator();
		while (it.hasNext()) {
			
		    Map.Entry<String, String> entry = (Map.Entry)it.next();
		    String key = entry.getKey();
		    
			String resultText = modifyRequest.getMessageFinal();
			String value = SetCommand.getValueFromHL7(resultText, targetReference, modifyRequest);
		    
		    int mapPos = value.toUpperCase().indexOf(key.toUpperCase());
		    if (mapPos == -1 && !(key.toUpperCase().equals("DEFAULT"))) {
		    	String newValue = callCommand.getParameterMap().get("DEFAULT");
			    resultText = SetCommand.setValueInHL7(newValue, resultText, targetReference, modifyRequest);
			    modifyRequest.setMessageFinal(resultText);
		    }
		    if (mapPos != -1) {
		    	int startIndex = value.indexOf(key);
		    	int endIndex = startIndex + key.length();
		    	String value1 = value.substring(0, startIndex);
		    	String value2 = value.substring(endIndex, value.length());
		    	String newValue = value1 + callCommand.getParameterMap().get(key) + value2;
		    	resultText = SetCommand.setValueInHL7(newValue, resultText, targetReference, modifyRequest);
		    	modifyRequest.setMessageFinal(resultText);
		    }
		}
		
	   
	}

}
