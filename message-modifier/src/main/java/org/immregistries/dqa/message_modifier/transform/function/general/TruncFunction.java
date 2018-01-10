package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;
import org.immregistries.dqa.message_modifier.transform.SetCommand;

public class TruncFunction implements CallFunction {

	public static final String PARAM_MAX = "MAX";
	public static final String PARAM_CUT = "CUT";

	public static final String SIDE_RIGHT = "RIGHT";
	public static final String SIDE_LEFT = "LEFT";

	public void doTransform(ModificationDetails modifyRequest, CallCommand callCommand) throws IOException {
		String maxString = callCommand.getParameterMap().get(PARAM_MAX);
		if (maxString != null) {
			int max = -1;
			try {
				max = Integer.parseInt(maxString);
			} catch (NumberFormatException nfe) {
				// ignore
			}
			if (max > -1) {
				ReferenceParsed targetReference = callCommand.getTargetReference();
				String resultText = modifyRequest.getMessageFinal();
				String value = SetCommand.getValueFromHL7(resultText, targetReference, modifyRequest);
				if (value.length() > max) {
					String side;
					try{
						side = callCommand.getParameterMap().get(PARAM_CUT).toUpperCase();
					}
					catch(Exception e){
						side = SIDE_LEFT;
					}
					if (side.equals(SIDE_RIGHT)) {
						// trunc(4, right) Hello --> ello 
						value = value.substring(value.length() - max);
					} else {
						// trunc(4, left) Hello --> Hell
						value = value.substring(0, max);
					}
					resultText = SetCommand.setValueInHL7(value, resultText, targetReference, modifyRequest);
					modifyRequest.setMessageFinal(resultText);
				}
			}
		}
	}

}
