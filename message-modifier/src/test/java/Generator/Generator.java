package Generator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.immregistries.dqa.message_modifier.legacy.PatientType;

public class Generator {

	private static final String FILEPATIENT = "/Users/ynb4/Documents/PatientData";
	private static final String FILEVACCINATION = "/Users/ynb4/Documents/VaccinationData";
	private static final int VACCINE_VIS_PUB_DATE = 11;
	static Random random = new Random();


	public static void main(String[] args) {

		BufferedWriter bwp = null;
		FileWriter fwp = null;

		BufferedWriter bwv = null;
		FileWriter fwv = null;


		try {

			String[] dataContents = generateRandomContent(80000);



			fwp = new FileWriter(FILEPATIENT);
			bwp = new BufferedWriter(fwp);
			bwp.write(dataContents[0]);

			fwv = new FileWriter(FILEVACCINATION);
			bwv = new BufferedWriter(fwv);
			bwv.write(dataContents[1]);

			System.out.println(dataContents[1]);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bwp != null)
					bwp.close();

				if (fwp != null)
					fwp.close();

				if (bwv != null)
					bwv.close();

				if (fwv != null)
					fwv.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}





	}
	
	private static Map<String, List<String[]>> conceptMap = null;
	private static Map<String, List<String[]>> testDataMap = null;
	private static String[] dataContents = new String[2];
	

	public static String[] generateRandomContent(int n) {
		String patientContent = "";	
		String vaccinationContent = "";	
		Patient patient = new Patient();
		Vaccine vaccine = new Vaccine();
		
		for(int i = 0; i < n; i++) {
		
		
		patient.setID(generateRandomID());
		patient.setPatientFirstName(getRandomValue("BOY"));
		patient.setPatientMiddleName(getRandomValue("BOY"));
		patient.setPatientLastName(getRandomValue("LAST_NAME"));
		patient.setPatientMotherMaidenName(getRandomValue("GIRL"));
		patient.setPatientMotherFirstName(getRandomValue("GIRL"));
		patient.setPatientMotherMiddleName(getRandomValue("GIRL"));
		patient.setPatientMotherLastName(getRandomValue("LAST_NAME"));
		patient.setPatientDateOfBirth(generateRandomDOB());
		patient.setPatientGender(generateRandomGender());
		String[] patientAdress = getValueArray("ADDRESS", 4);
		patient.setPatientAdressStreet((random.nextInt(400) + 1) + " " + getRandomValue("LAST_NAME") + " " + getRandomValue("STREET_ABBREVIATION"));
		patient.setPatientCity(patientAdress[0]);
		patient.setPatientState(patientAdress[1]);
		patient.setPatientCountry("USA");
		patient.setPatientZip(patientAdress[2]);
		patient.setPatientNumberOfRaceCodeStored(generateZeroOrOne());
		patient.setPatientNumberOfEthnicityCodeStored(generateZeroOrOne());
		patient.setPatientPhone("(" + patientAdress[3] + ")" +"" + (random.nextInt(8) + 2) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
        + random.nextInt(10) + random.nextInt(10));
		patient.setPatientEmailAdress(getRandomValue("BOY").toLowerCase() + "." + getRandomValue("LAST_NAME").toLowerCase() + "@madeupemailaddress.com");
		patient.setPatientPrimaryLangage(getRandomValue("LANGUAGE") );
		patient.setPatientAliasFirstName(getRandomValue("BOY"));
		patient.setPatientAliasMiddleName(getRandomValue("BOY"));
		patient.setPatientAliasLastName(getRandomValue("LAST_NAME"));
		patient.setPatientResponsibleFirstName(getRandomValue("GIRL"));
		patient.setPatientResponsibleMiddleName (getRandomValue("GIRL"));
		patient.setPatientResponsibleLastName(getRandomValue("LAST_NAME"));
		patient.setPatientResponsibleRelationship(generateRandomRelationship());
		patient.setPatientBirthingFacilityName(getRandomValue("LAST_NAME") + "Facility") ;
		patient.setPatientMultipleBirthIndicator(generateRandomBoolean());
		patient.setPatientMultipleBirthOrder(generateRandomBirthCount());
		patient.setPatientProviderfacilitylevel("[[NOT_EXTRACTED]]");
		patient.setPatientIISLevel(generateRandomImmuRegistryStatus());

		
		String patientLine = patient.getID() +"\t"+ patient.getPatientFirstName() +"\t"+ patient.getPatientMiddleName() +"\t"+ patient.getPatientLastName()+"\t"+patient.getPatientMotherMaidenName()+"\t"+patient.getPatientMotherFirstName()+"\t"+patient.getPatientMotherMiddleName()+"\t"+patient.getPatientMotherLastName()+"\t"+patient.getPatientDateOfBirth()+"\t"+patient.getPatientGender()+"\t"+patient.getPatientAdressStreet()+"\t"+patient.getPatientCity()+"\t"+patient.getPatientState()+"\t"+patient.getPatientCountry()+"\t"+patient.getPatientZip()+"\t"+patient.getPatientNumberOfRaceCodeStored()+"\t"+patient.getPatientNumberOfEthnicityCodeStored()+"\t"+patient.getPatientPhone()+"\t"+patient.getPatientEmailAdress()+"\t"+patient.getPatientPrimaryLangage()+"\t"+patient.getPatientAliasFirstName()+"\t"+patient.getPatientAliasMiddleName()+"\t"+patient.getPatientAliasLastName()+"\t"+patient.getPatientResponsibleFirstName()+"\t"+patient.getPatientResponsibleMiddleName()+"\t"+patient.getPatientResponsibleLastName()+"\t"+patient.getPatientResponsibleRelationship()+"\t"+patient.getPatientBirthingFacilityName()+"\t"+patient.getPatientMultipleBirthIndicator()+"\t"+patient.getPatientMultipleBirthOrder()+"\t"+patient.getPatientProviderfacilitylevel()+"\t"+patient.getPatientIISLevel()+"\n";
		 patientContent = patientContent + patientLine;

	    int vacBoucle = random.nextInt(6);
		for(int j = 0; j < vacBoucle ; j++) {

		
		

	    String[] dates = new String[4];
		String[] vaccineCodeAndDate = {"????","????"};
		vaccine.setVaccineCodeAndDate(vaccineCodeAndDate);
		vaccine.setVaccineCvxCode(generateRandomCvx());
		String vaccinationNDCCode = "[[NOT_EXTRACTED]]";
		vaccine.setVaccineAdministratedDate(generateRandomAdministratedDate());
		vaccine.setManufacturer(getRandomValue("LAST_NAME") + "Corporation");
		vaccine.setLotNumber(generateRandomID());
		vaccine.setEventInformationSource(generateRandomEventInformationSource());
		vaccine.setAdministeringProvider("[[NOT_EXTRACTED]]");
		vaccine.setAdministeringAtLocation(generateRandomAtLocationID());
		vaccine.setAdministrationRoute(generateRandomAdministrationRoute());
		vaccine.setAdministrationSite(generateRandomAdministrationSite());
		vaccine.setExpirationDate(generateRandomExpirationDate());
		vaccine.setDoseVolume(generateRandomDoze());
		vaccine.setOrderingProviderFirstName(getRandomValue("BOY"));
		vaccine.setOrderingProviderMiddleName(getRandomValue("BOY"));
		vaccine.setOrderingProviderLastName(getRandomValue("LAST_NAME"));
		vaccine.setVisInformationType("[[NOT_EXTRACTED]]");
		vaccine.setVisInformationPublicationDate("[[NOT_EXTRACTED]]");
		vaccine.setVisInformationDateGivenToPatient("[[NOT_EXTRACTED]]");
		vaccine.setVaccineEligibility(generateRandomElligibiltyAtDose());
		vaccine.setCompleteStatus("CP");
		
		
		 
		 String vaccinationLine = patient.getID() +"\t" +  vaccine.getVaccineCvxCode() +"\t"+ vaccinationNDCCode + "\t" + vaccine.getVaccineAdministratedDate()+ "\t" + vaccine.getManufacturer()+"\t" + vaccine.getLotNumber()+"\t"+vaccine.getEventInformationSource()+"\t"+ vaccine.getAdministeringProvider()+"\t"+ vaccine.getAdministeringAtLocation()+"\t"+vaccine.getAdministrationRoute() +"\t"+vaccine.getAdministrationSite()+"\t"+vaccine.getExpirationDate()+"\t"+ vaccine.getDoseVolume() +"\t"+vaccine.getOrderingProviderFirstName()+"\t"+vaccine.getOrderingProviderMiddleName()+"\t"+vaccine.getOrderingProviderLastName()+"\t"+vaccine.getVisInformationType()+"\t"+vaccine.getVisInformationPublicationDate()+"\t"+vaccine.getVisInformationDateGivenToPatient()+"\t"+vaccine.getVaccineEligibility()+"\t"+vaccine.getCompleteStatus()+  "\n";

		 vaccinationContent = vaccinationContent + vaccinationLine;

		 dataContents[0] = patientContent ;
		 dataContents[1] = vaccinationContent ;

	}
		}
		
		
		return dataContents;

		
	}
	
	public static String generateRandomID() {

		char c = (char)(random.nextInt(26) + 'A');
		int  n = random.nextInt(89999999) + 10000000;
		String patientID= c + String.valueOf(n);
		return patientID;
	}

	public static String generateRandomGender() {
		String gender;
		
		if(random.nextBoolean()){
		     gender = "M";
		}
		else gender = "F";
		return gender;
	}
	
	public static String generateRandomBoolean() {

        String [] arr = {"Yes", "No"};
        int select = random.nextInt(arr.length); 
        return arr[select]; 
   }
	
	public static String generateRandomRelationship() {

        String [] arr = {"Father", "Mother", "Guardien"};
        int select = random.nextInt(arr.length); 
        return arr[select]; 
   }
	
	public static String generateRandomImmuRegistryStatus() {
		String patientIISLevel = "Active" ;
        String [] arr = {"Active", "Inactive--Unspecified" , "Inactive-Lost to follow-up " , "Inactive-Moved or gone elsewhere"};
        int hat = random.nextInt(100);
        if (hat < 91) {
        	patientIISLevel = "Active";
        }else if(90< hat & hat<94) {
        	patientIISLevel =  "Inactive--Unspecified";      	
        }else if(93< hat & hat<97) {
        	patientIISLevel =  "Inactive-Lost to follow-up";      	
        }else if(96< hat & hat<101) {
        	patientIISLevel =  "Inactive-Moved or gone elsewhere";      	
        }
        
          return patientIISLevel;
            
	}
	
	public static String generateRandomEventInformationSource() {
		 String [] arr = {"00", "01"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	public static String generateRandomBirthCount() {
	    int birthCount = 1;
	    int hat = random.nextInt(100000);
	    if (hat < 3220 + 149) {
	      // chances for twin are 32.2 in 1,000 or 3220 in 100,000
	      birthCount = 2;
	      if (hat < 149) {
	        // chances for triplet or higher is is 148.9 in 100,000
	        birthCount = 3;
	        if (hat < 10) {
	          birthCount = 4;
	          if (hat < 2) {
	            birthCount = 5;
	          }
	        }
	      }
	    }
	    return String.valueOf(birthCount);
	  }
	
	public static String generateRandomAtLocationID() {
		String facilityID;
		char c = (char)(random.nextInt(26) + 'A');
		int  n = random.nextInt(100);
		 facilityID = c + String.valueOf(n);
		return facilityID;
	}
	public static String generateRandomDOB() {
		String DOB;
		int year;
		int month;
		int day;
		
		year = random.nextInt(118) + 1900;
		month = random.nextInt(12) + 1;
		day = random.nextInt(30) + 1;

		if(month<10) {
				DOB=year+"-"+"0"+month+"-"+day;
		}
		if(day<10) {
			DOB=year+"-"+month+"-"+"0"+day;

		}
		
		else{
			DOB=year+"-"+month+"-"+day;

		}
	    return DOB;
	}
	
	public static String generateRandomAdministratedDate() {
		String DOB;
		int year;
		int month;
		int day;
		
		year = random.nextInt(15) + 2000;
		month = random.nextInt(12) + 1;
		day = random.nextInt(30) + 1;

		if(month<10) {
			DOB=year+"-"+"0"+month+"-"+day;
	}
		else if(day<10) {
		DOB=year+"-"+month+"-"+"0"+day;
		}
	
	
	else{
		DOB=year+"-"+month+"-"+day;

	}
	    return DOB;
	}
	
	public static String generateRandomExpirationDate() {
		String expritationDate;
		int year;
		int month;
		int day;
		
		year = random.nextInt(7) + 2015;
		month = random.nextInt(12) + 1;
		day = random.nextInt(30) + 1;

		if(month<10) {
			expritationDate=year+"-"+"0"+month+"-"+day;
		}else {
			expritationDate=year+"-"+month+"-"+day;

		}
	    return expritationDate;
	}
	
	
	public static String generateRandomAdministrationRoute() {
		 String [] arr = {"Intradermal", "Intramuscular", "Nasal", "Intravenous", "Oral", "Other", "Percutaneous", "Subcutaneous", "Transdermal"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	public static String generateRandomElligibiltyAtDose() {
		 String [] arr = {"vo1", "vo2", "vo3", "vo4"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	public static String generateRandomAdministrationSite() {
		 String [] arr = {"Left Arm", "Right Arm"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	public static String generateZeroOrOne() {
		 String [] arr = {"0", "1"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	
	public static String generateRandomDoze() {
		int n = random.nextInt(10);
		String doze = "0," + String.valueOf(n);
		return doze;
	}
	
	public static String getRandomValue(String concept) {
		try {
			return getValue(concept, 0);
		} catch (IOException ioe) {
			return "Unable to get value: " + ioe.getMessage();
		}
	}

	public static String generateRandomCvx() {
		 String [] arr = {"116", "20", "49", "03", "52", "155", "21"};
	        int select = random.nextInt(arr.length); 
	        return arr[select]; 
	}
	
	public static String getValue(String concept, int pos) throws IOException {
		if (conceptMap == null) {
			init();
		}
		if (testDataMap != null) {
			List<String[]> valueList = testDataMap.get(concept);
			if (valueList != null) {
				return getRandomValue(pos, valueList);
			}
		}
		List<String[]> valueList = conceptMap.get(concept);
		if (valueList != null) {
			return getRandomValue(pos, valueList);
		}
		return "";
	}


	public static String getRandomValue(int pos, List<String[]> valueList) {
		String[] values = valueList.get(random.nextInt(valueList.size()));
		if (pos < values.length) {
			return values[pos];
		}
		return "";
	}

	public static String[] getValueArray(String concept, int size) {
	    if (conceptMap == null) {
	      init();
	    }
	    String[] valueSourceList = null;
	    List<String[]> valueList = null;
	    if (testDataMap != null) {
	      valueList = testDataMap.get(concept);
	    }
	    if (valueList == null) {
	      valueList = conceptMap.get(concept);
	    }
	    if (valueList != null) {
	      valueSourceList = valueList.get(random.nextInt(valueList.size()));
	    }
	    String[] values = new String[size];
	    for (int i = 0; i < values.length; i++) {
	      if (valueSourceList != null && i < valueSourceList.length) {
	        values[i] = valueSourceList[i];
	      } else {
	        values[i] = "";
	      }
	    }
	    return values;
	  }


	protected static void init() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(Generator.class.getResourceAsStream("transform.txt")));
			conceptMap = readDataIn(in);
		} catch (IOException e) {
			e.printStackTrace();
			conceptMap = new HashMap<String, List<String[]>>();
		}
	}


	public static HashMap<String, List<String[]>> readDataIn(BufferedReader in) throws IOException {
		HashMap<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		String line;
		while ((line = in.readLine()) != null) {
			int equals = line.indexOf("=");
			if (equals != -1) {
				String concept = line.substring(0, equals);
				String[] values = line.substring(equals + 1).split("\\,");
				List<String[]> valueList = map.get(concept);
				if (valueList == null) {
					valueList = new ArrayList<String[]>();
					map.put(concept, valueList);
				}
				valueList.add(values);
			}
		}
		return map;
	}
	
	
	
	protected static PatientType createDates(String[] dates, PatientType type) {
	    {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	      if (type == PatientType.BABY || (type == PatientType.ANY_CHILD && random.nextBoolean())) {
	        // Setting up baby, 6 months old today
	        // 6 month appointment
	        Calendar cal6Month = Calendar.getInstance();
	        dates[3] = sdf.format(cal6Month.getTime());

	        // Born about 6 months before
	        Calendar calBorn = Calendar.getInstance();
	        calBorn.add(Calendar.MONTH, -6);
	        calBorn.add(Calendar.DAY_OF_MONTH, 3 - random.nextInt(17));
	        dates[0] = sdf.format(calBorn.getTime());

	        // 4 month appointment
	        Calendar cal4Month = Calendar.getInstance();
	        cal4Month.setTime(calBorn.getTime());
	        cal4Month.add(Calendar.MONTH, 4);
	        cal4Month.add(Calendar.DAY_OF_MONTH, random.nextInt(12) - 3);
	        dates[2] = sdf.format(cal4Month.getTime());

	        // 2 month appointment
	        Calendar cal2Month = Calendar.getInstance();
	        cal2Month.setTime(calBorn.getTime());
	        cal2Month.add(Calendar.MONTH, 2);
	        cal2Month.add(Calendar.DAY_OF_MONTH, random.nextInt(10) - 3);
	        if (cal2Month.after(cal6Month.getTime())) {
	          dates[1] = dates[3];
	        } else {
	          dates[1] = sdf.format(cal2Month.getTime());
	        }

	        return PatientType.BABY;
	      } else if (type == PatientType.TWO_MONTHS_OLD || type == PatientType.TWO_YEARS_OLD || type == PatientType.FOUR_YEARS_OLD
	          || type == PatientType.TWELVE_YEARS_OLD) {
	        // Setting up baby, 2 months old today
	        // 2 month appointment
	        // This type will always be at least two and the appointment will always
	        // be when the patient was at least two
	        Calendar calToday = Calendar.getInstance();
	        dates[3] = sdf.format(calToday.getTime());

	        int months = 0;
	        int years = 0;
	        if (type == PatientType.TWO_MONTHS_OLD) {
	          months = 2;
	        } else if (type == PatientType.TWO_YEARS_OLD) {
	          years = 2;
	        } else if (type == PatientType.FOUR_YEARS_OLD) {
	          years = 4;
	        } else if (type == PatientType.TWELVE_YEARS_OLD) {
	          years = 12;
	        }
	        // set birth date
	        Calendar calBorn = Calendar.getInstance();
	        calBorn.add(Calendar.MONTH, -months);
	        calBorn.add(Calendar.YEAR, -years);
	        calBorn.add(Calendar.DAY_OF_MONTH, -random.nextInt(17));
	        dates[0] = sdf.format(calBorn.getTime());

	        // 2 month appointment
	        Calendar cal2Month = Calendar.getInstance();
	        cal2Month.setTime(calBorn.getTime());
	        cal2Month.add(Calendar.MONTH, months);
	        cal2Month.add(Calendar.YEAR, years);
	        cal2Month.add(Calendar.DAY_OF_MONTH, random.nextInt(10));
	        if (cal2Month.getTime().after(calToday.getTime())) {
	          dates[1] = dates[3];
	          dates[2] = dates[3];
	        } else {
	          dates[1] = sdf.format(cal2Month.getTime());
	          dates[2] = dates[1];
	        }

	        if (type == PatientType.TWO_MONTHS_OLD) {
	          return PatientType.BABY;
	        } else if (type == PatientType.TWO_YEARS_OLD) {
	          return PatientType.TODDLER;
	        } else if (type == PatientType.FOUR_YEARS_OLD) {
	          return PatientType.TODDLER;
	        } else if (type == PatientType.TWELVE_YEARS_OLD) {
	          return PatientType.TWEEN;
	        }
	        return type;
	      } else {
	        if (type == PatientType.TODDLER || (type == PatientType.ANY_CHILD && random.nextBoolean())) {
	          // Setting up toddler
	          Calendar calendar = Calendar.getInstance();
	          // 4 years (today) - 48 months
	          dates[3] = sdf.format(calendar.getTime());
	          // 19 months
	          calendar.add(Calendar.MONTH, 19 - 48);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[2] = sdf.format(calendar.getTime());
	          // 12 months
	          calendar.add(Calendar.MONTH, 12 - 19);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[1] = sdf.format(calendar.getTime());
	          // birth
	          calendar.add(Calendar.MONTH, -12);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[0] = sdf.format(calendar.getTime());
	          return PatientType.TODDLER;
	        } else if (type == PatientType.ANY_CHILD || type == PatientType.TWEEN) {
	          // Setting up tween
	          Calendar calendar = Calendar.getInstance();
	          // 13 years (today)
	          dates[3] = sdf.format(calendar.getTime());
	          // 11 years
	          calendar.add(Calendar.YEAR, -2);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[2] = sdf.format(calendar.getTime());
	          // 9 years
	          calendar.add(Calendar.YEAR, -2);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[1] = sdf.format(calendar.getTime());
	          // birth
	          calendar.add(Calendar.YEAR, -9);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[0] = sdf.format(calendar.getTime());
	          return PatientType.TWEEN;
	        } else {
	          // Setting up adult
	          Calendar calendar = Calendar.getInstance();
	          // 67 years (today)
	          dates[3] = sdf.format(calendar.getTime());
	          // last year
	          calendar.add(Calendar.YEAR, -1);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[2] = sdf.format(calendar.getTime());
	          // two years before that
	          calendar.add(Calendar.YEAR, -2);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[1] = sdf.format(calendar.getTime());
	          // birth
	          calendar.add(Calendar.YEAR, -64);
	          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
	          dates[0] = sdf.format(calendar.getTime());
	          return PatientType.ADULT;

	        }
	      }
	    }
	  }
}