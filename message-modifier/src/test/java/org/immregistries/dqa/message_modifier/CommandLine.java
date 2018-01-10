package org.immregistries.dqa.message_modifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.immregistries.dqa.message_modifier.script.ModificationDetails;

public class CommandLine {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.err.println(
					"usage: org.immregistries.dqa.message_modifier.CommandLine [original HL7 file] [transformation file] [final file]");
			System.exit(1);
		}
		File originalHL7File = new File(args[0]);
		File transformationsFile = new File(args[1]);
		File finalFile = new File(args[2]);

		String messageOriginal = readFile(originalHL7File);
		String modificationScript = readFile(transformationsFile);

		ModifierService modifierService = new ModifierService();
		ModificationDetails modifyRequest = new ModificationDetails();
		modifyRequest.setMessageOriginal(messageOriginal);
		modifyRequest.setModificationScript(modificationScript);
		modifierService.modify(modifyRequest);
		String finalMessage[] = modifyRequest.getMessageFinal().split("\\r");
		BufferedWriter bw = new BufferedWriter(new FileWriter(finalFile));
		for (String s : finalMessage) {
			bw.write(s);
			bw.newLine();
			System.out.println(s);
		}
		bw.close();
	}

	private static String readFile(File file) throws FileNotFoundException, IOException {
		StringBuffer sbuf = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			sbuf.append(line);
			sbuf.append("\r");
		}
		String s = sbuf.toString();
		return s;
	}
}
