package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.transform.function.FunctionFactory;
import org.immregistries.dqa.message_modifier.transform.function.general.CallFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.CleanFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.ClearFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertAfterFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertBeforeFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertFirstFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.InsertLastFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.MapFunction;
import org.immregistries.dqa.message_modifier.transform.function.general.TruncFunction;;

public class CallCommand extends Command {
	private ReferenceParsed targetReference = null;

	private String name = "";
	private Map<String, String> parameterMap = new LinkedHashMap<>();

	public ReferenceParsed getTargetReference() {
		return targetReference;
	}

	public void setTargetReference(ReferenceParsed targetReference) {
		this.targetReference = targetReference;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameters) {
		this.parameterMap = parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static FunctionFactory functionFactory = null;

	private FunctionFactory getFunctionFactory() {
		if (functionFactory == null) {
			functionFactory = new FunctionFactory();
		}
		return functionFactory;
	}

	@Override
	public void doTransform(ModificationDetails modifyRequest) throws IOException {
		// TODO need to call the function that is associated with the name,
		// but we also have to think about "context"
		try {
			CallFunction function = getFunctionFactory().createFunction(this.name);
			function.doTransform(modifyRequest, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
