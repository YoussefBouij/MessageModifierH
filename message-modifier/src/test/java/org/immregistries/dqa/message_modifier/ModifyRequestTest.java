package org.immregistries.dqa.message_modifier;

import org.immregistries.dqa.message_modifier.ModifierService;
import org.immregistries.dqa.message_modifier.script.ModificationDetails;
import org.immregistries.dqa.message_modifier.script.SyntaxeChecker;

import junit.framework.TestCase;

public class ModifyRequestTest extends TestCase {
	private int count=0;
  public void testInitial() {
    // use context
    {
      String modificationScript = "use context::Immunization;";
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String messageFinal =    messageOriginal;
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Assignments
    {
      String modificationScript = "PID-5=\"\";";
      
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5=\"\";\rPID-6=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
      }
    {
        String modificationScript = "PID-5=\"\";\rPID-6=\"\";\rPID-7=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
      }
    {
        String modificationScript = "PID-5=\"\";\rPID-6=\"\";\rPID-7=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|^Arden||M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
      }
    {
        String modificationScript = "PID-5.1=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[2].1=\"Hello\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Hello^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[2]-5.1=\"\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
      		  				     "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
  				  			     "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.1=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5.1;";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5.1;";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5.2 = $PID-5[2].1;";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Holmes^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    //To the stars
    
    {
        String modificationScript = "PID-5.*=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[*]-5.*=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
	  			     			 "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Watson^Watson^Watson^Watson^Watson^Watson|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
		//runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID-5[*].1=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "PID[*]-5=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n"+
        						 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n"+
        					  "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        //String messageFinal =    "PID|Watson|Watson|Watson^^^AIRA-TEST^MR|Watson|Watson^Jeramiah^Z^IV^^^L~Holmes^Jeramiah^Z^IV^^^L|Watson^Arden|Watson|Watson|Watson|Watson|Watson^^Cadmus^MI^49221^USA^P|Watson|Watson^PRN^PH^^^517^3004208|Watson";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Mapping
    {
        String modificationScript = "for $RXA-5.2 call map(\"PCV 13\" => \"03\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^03^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-4 call map(\"MMR\" => \"03\", \"Default\" => \"Unknown\");";
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104|Unknown|133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^ABC 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Truncate
    {
        String modificationScript = "for $RXA-9.2 call trunc(\"max\" => \"5\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Admin^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-1 call trunc(\"Max\" => \"5\", \"cut\"=>\"left\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"1\", \"cut\"=>\"right\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^3^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $RXA-5.2 call trunc(\"MAX\" => \"2\", \"cut\" => \"right\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.3 call trunc(\"MAX\" => \"2\", \"cut\" => \"left\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^CV|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA-5.3 call trunc(\"MAX\" => \"1\", \"cut\" => \"left\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||133^PCV 13^C|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Truncate and mapping
    
    {
        String modificationScript = "for $RXA-5.1 call trunc(\"MAX\" => \"1\", \"cut\"=>\"right\");\rfor $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
        
        String messageOriginal = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        String messageFinal =    "RXA|0|1|20170104||3^ABC 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Clearing fields
    
    {
        String modificationScript = "for $PID call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // Testing the segment repeat with clear
    {
        String modificationScript = "for $PID[2] call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" + 
        							"PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" + 
								"PID|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR|||Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5.1 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-3 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-5.2 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
   /* This two examples cause problems. Combining repetitionSelector with Clear function ?. Ask Nathan.
  
    {
        String modificationScript = "for $PID-3[2] call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR~Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR~^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID-3[2].1 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR~Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR~^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    \\ This one shouldn't pass but still does.
    {
        String modificationScript = "for $PID-5[2] call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
        
    } 
    */ 
    // for some reason, considers $PID-3.[4] call clear() similar to $PID-3.4 call clear()
    {
        String modificationScript = "for $PID-3.4 call clear();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
        
    }
    
    //Cleaning fields
    {
        String modificationScript = "call clean();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call clean(\"no last slash\" => \"true\");";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call clean(\"no last slash\" => \"false\");";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|||||||||\n" +
				     			 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
    			 				 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    // Questions about fixes : call fixAmpersand();  call fixEscape(); what does they do?
    // For fixAmpersand the any & becomes \T\ (except for in heading)
    // For fixEscape() any single \ becomes \E\ (except for in heading)
    {
        String modificationScript = "call fixAmpersand();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\T\\Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "call fixEscape();";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus\\E\\^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    
    // Adding and removing segments
    
    {
        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\");";
        // Do we put the right number of pipes?
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID call insertBefore(\"Segment Id\" => \"ABC\");";
        // Do we put the right number of pipes?
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "ABC|\n" +
        						 "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $RXA[2] call insertAfter(\"Segment Id\" => \"RXR\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "RXR|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA call insertAfter(\"Segment Id\" => \"RXR\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "RXR|\n"+
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
							    
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
    {
        String modificationScript = "call insertLast(\"Segment Id\" => \"PV1\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "PV1|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call insertFirst(\"Segment Id\" => \"PV1\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PV1|\n" +
        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
    {
        String modificationScript = "call insertFirst(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "call insertLast(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" +
							    "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PD1 call insertBefore(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        						"PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $RXA[2] call insertAfter(\"Segment Id\" => \"BHS\", \"copy values from\" => \"MSH\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
							    "MSH|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
							    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
							    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
							    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
							    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" +
							    "BHS|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
        String modificationScript = "for $PID call insertAfter(\"Segment Id\" => \"PD1\", \"if missing\" => \"true\");";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    
    // If then else
    
    {
        String modificationScript = "for $PID if(PID-7 == \"20160626\") then call clear();";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" ;
        String messageFinal =    "PID|" ;
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    {
        String modificationScript = "for $PID-5.2 if (PID-5.2 == \"Jeramiah\") then call trunc(\"max\" => \"2\");";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Je^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						 "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
   
    //Combining commands
    
    {
        String modificationScript = "for $PID-5.2 if (PID-5.2 == \"Jeramiah\") then call trunc(\"max\" => \"2\");\rPID-3.1=\"\";\rcall insertFirst(\"Segment Id\" => \"PV1\");";
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
				 				 "PD1||||||||||||||||||";
        String messageFinal =  "PV1|\n" +  
        						  "PID|||^^^AIRA-TEST^MR||Holmes^Je^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|\n" +
        						  "PD1||||||||||||||||||";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    // test 40 IF must be followed by then call can't be then + assignment
    
  /*  
    {
        String modificationScript = "for $RXA-4.1 if (RXA-5 == \"94\" and RXA-6 != \"06\") then RXA-4.1 = \"Hello\");";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
	        String messageFinal =   "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204|Hello|94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n";
        runTest(messageOriginal, modificationScript, messageFinal);
    }
    
    */
    // Referencing nested repeats
    {
        String modificationScript = "RXA-5.2 = \"Hello\";";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =  	    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
        								"NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        								"ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
        								"RXA|0|1|20131204||94^Hello^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
        								"ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
        								"RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
							    
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
    
    {
        String modificationScript = "PD1-2 = \"Hello\";";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA|\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n";
        String messageFinal =  	    "PD1||Hello|||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
        								"NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        								"ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
        								"RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n";
							    
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
    
    {
        String modificationScript = "RXA[2]-5.2 = \"Hello\";";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =  	    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
        								"NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        								"ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
        								"RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
        								"ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
        								"RXA|0|1|20161204||94^Hello^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
							    
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
/* {
        String modificationScript = "ORC[2] :: RXA-5.2 = \"Hello\";";        
        String messageOriginal =    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
								    "NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
								    "ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
								    "RXA|0|1|20131204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
								    "ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
								    "RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
        String messageFinal =  	    "PD1|||||||||||02^Reminder/Recall - any method^HL70215|N|20161204|||A|20161204|20161204\n" +
        								"NK1|1|Wilson^Beckham^Marion^^^^L|MTH^Mother^HL70063|274 Simmingsen Cir^^Simplicity Pattern^MI^49121^USA^P|^PRN^PH^^^269^6751060\n" +
        								"ORC|RE|AJ68O9.1^AIRA|BJ68O9.1^AIRA\n" +
        								"RXA|0|1|20131204||94^Hello^CVX|999|||01^Historical^NIP001|||||||||||CP|A\n" + 
        								"ORC|RE|AJ68O9.2^AIRA|BJ68O9.2^AIRA\n" + 
        								"RXA|0|1|20161204||94^MMRV^CVX|999|||01^Historical^NIP001|||||||||||CP|A";
							    
        runTest(messageOriginal, modificationScript, messageFinal);
    }  
     
 */
    {
        String modificationScript = "PID-5.10=\"Watson\";";
        
        String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        String messageFinal =    "PID|||Q63W1^^^AIRA-TEST^MR||Watson^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
        runTest(messageOriginal, modificationScript, messageFinal);
    }

   
  }	  



  /**
 * @param messageOriginal something
 * @param modificationScript
 * @param messageFinal
 */
private void runTest(String messageOriginal, String modificationScript, String messageFinal) {
	    ModifierService modifierService = new ModifierService();
	    ModificationDetails modifyRequest = new ModificationDetails();
	    modifyRequest.setMessageOriginal(messageOriginal);
	    modifyRequest.setModificationScript(modificationScript);	
	    
	    /*
	    SyntaxeChecker sc = new SyntaxeChecker();	    
	    sc.checkSyntax(modificationScript);
	    */
	    
	    modifierService.modify(modifyRequest);
	    // empty character at the end of messageFinal in modifyRequest fails the test
	    String FinalMessage = modifyRequest.getMessageFinal().trim();
	    // \n are replaced with \r during modifications
	    FinalMessage = FinalMessage.replace("\r", "\n");
	    assertEquals(messageFinal, FinalMessage);
	    count++;
	    System.out.print("Test "+count+" sucessful\n\n");
	    System.out.print(FinalMessage);

	    //assertEquals(messageFinal,modifyRequest.getMessageFinal()); 
	  }
}
