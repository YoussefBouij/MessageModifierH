package Generator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author nathan
 */
public class Patient {
	private String ID = "";
	private String patientFirstName;
	private String patientMiddleName;
	private String patientLastName;
	private String patientMotherMaidenName;
	private String patientMotherFirstName;
	private String patientMotherMiddleName;
	private String patientMotherLastName;
	private String patientDateOfBirth;
	private String patientGender;
	private String[] patientAdress;
	private String patientAdressStreet;
	private String patientCity;
	private String patientState;
	private String patientCountry;
	private String patientZip;
	private String patientNumberOfRaceCodeStored;
	private String patientNumberOfEthnicityCodeStored;
	private String patientPhone;
	private String patientEmailAdress;
	private String patientPrimaryLangage;
	private String patientAliasFirstName;
	private String patientAliasMiddleName;
	private String patientAliasLastName;
	private String patientResponsibleFirstName;
	private String patientResponsibleMiddleName;
	private String patientResponsibleLastName;
	private String patientResponsibleRelationship;
	private String patientBirthingFacilityName;
	private String patientMultipleBirthIndicator;
	private String patientMultipleBirthOrder;
	private String patientProviderfacilitylevel;
	private String patientIISLevel;
	
	
	
    private String medicalRecordNumber = "";
    private String medicaidNumber = "";
    private String ssn = "";
    private String wic = "";
    private String boyName = "";
    private String girlName = "";
    private String motherName = "";
    private String motherMaidenName = "";
    private String motherDob = "";
    private String motherSsn = "";
    private String fatherName = "";
    private String lastName = "";
    private String differentLastName = "";
    private String middleNameBoy = "";
    private String middleNameGirl = "";
    private String aliasBoy = "";
    private String aliasGirl = "";
    private String[] datesAny = new String[4];
    private PatientType vaccineType = null;
    private String gender = "";
    private String[] vaccine1 = null;
    private String[] vaccine2 = null;
    private String[] vaccine3 = null;
    private String[] combo = null;
    private String[] race = null;
    private String[] ethnicity = null;
    private String[] language = null;
    private String[] address = null;
    private String[] vfc = null;
    private String suffix = "";
    private String street = "";
    private String street2 = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String phone = "";
    private String phoneArea = "";
    private String phoneLocal = "";
    private String phoneAlt = "";
    private String phoneAltArea = "";
    private String phoneAltLocal = "";
    private int birthCount = 0;
    private String future = "";
    private String email = "";
    private String enteredByFirstName = "";
    private String enteredByLastName = "";
    private String enteredByMiddleName = "";
    private String enteredByNPI = "";
    private String orderedByFirstName = "";
    private String orderedByLastName = "";
    private String orderedByMiddleName = "";
    private String orderedByNPI = "";
    private String adminByFirstName = "";
    private String adminByLastName = "";
    private String adminByMiddleName = "";
    private String adminByNPI = "";
    private String[] responsibleOrg = null;
    private String[] adminOrg1 = null;
    private String[] adminOrg2 = null;
    
    public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientMiddleName() {
		return patientMiddleName;
	}

	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getPatientMotherMaidenName() {
		return patientMotherMaidenName;
	}

	public void setPatientMotherMaidenName(String patientMotherMaidenName) {
		this.patientMotherMaidenName = patientMotherMaidenName;
	}

	public String getPatientMotherFirstName() {
		return patientMotherFirstName;
	}

	public void setPatientMotherFirstName(String patientMotherFirstName) {
		this.patientMotherFirstName = patientMotherFirstName;
	}

	public String getPatientMotherMiddleName() {
		return patientMotherMiddleName;
	}

	public void setPatientMotherMiddleName(String patientMotherMiddleName) {
		this.patientMotherMiddleName = patientMotherMiddleName;
	}

	public String getPatientMotherLastName() {
		return patientMotherLastName;
	}

	public void setPatientMotherLastName(String patientMotherLastName) {
		this.patientMotherLastName = patientMotherLastName;
	}

	public String getPatientDateOfBirth() {
		return patientDateOfBirth;
	}

	public void setPatientDateOfBirth(String patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String[] getPatientAdress() {
		return patientAdress;
	}

	public void setPatientAdress(String[] patientAdress) {
		this.patientAdress = patientAdress;
	}

	public String getPatientAdressStreet() {
		return patientAdressStreet;
	}

	public void setPatientAdressStreet(String patientAdressStreet) {
		this.patientAdressStreet = patientAdressStreet;
	}

	public String getPatientCity() {
		return patientCity;
	}

	public void setPatientCity(String patientCity) {
		this.patientCity = patientCity;
	}

	public String getPatientState() {
		return patientState;
	}

	public void setPatientState(String patientState) {
		this.patientState = patientState;
	}

	public String getPatientCountry() {
		return patientCountry;
	}

	public void setPatientCountry(String patientCountry) {
		this.patientCountry = patientCountry;
	}

	public String getPatientZip() {
		return patientZip;
	}

	public void setPatientZip(String patientZip) {
		this.patientZip = patientZip;
	}

	public String getPatientNumberOfRaceCodeStored() {
		return patientNumberOfRaceCodeStored;
	}

	public void setPatientNumberOfRaceCodeStored(String patientNumberOfRaceCodeStored) {
		this.patientNumberOfRaceCodeStored = patientNumberOfRaceCodeStored;
	}

	public String getPatientNumberOfEthnicityCodeStored() {
		return patientNumberOfEthnicityCodeStored;
	}

	public void setPatientNumberOfEthnicityCodeStored(String patientNumberOfEthnicityCodeStored) {
		this.patientNumberOfEthnicityCodeStored = patientNumberOfEthnicityCodeStored;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmailAdress() {
		return patientEmailAdress;
	}

	public void setPatientEmailAdress(String patientEmailAdress) {
		this.patientEmailAdress = patientEmailAdress;
	}

	public String getPatientPrimaryLangage() {
		return patientPrimaryLangage;
	}

	public void setPatientPrimaryLangage(String patientPrimaryLangage) {
		this.patientPrimaryLangage = patientPrimaryLangage;
	}

	public String getPatientAliasFirstName() {
		return patientAliasFirstName;
	}

	public void setPatientAliasFirstName(String patientAliasFirstName) {
		this.patientAliasFirstName = patientAliasFirstName;
	}

	public String getPatientAliasMiddleName() {
		return patientAliasMiddleName;
	}

	public void setPatientAliasMiddleName(String patientAliasMiddleName) {
		this.patientAliasMiddleName = patientAliasMiddleName;
	}

	public String getPatientAliasLastName() {
		return patientAliasLastName;
	}

	public void setPatientAliasLastName(String patientAliasLastName) {
		this.patientAliasLastName = patientAliasLastName;
	}

	public String getPatientResponsibleFirstName() {
		return patientResponsibleFirstName;
	}

	public void setPatientResponsibleFirstName(String patientResponsibleFirstName) {
		this.patientResponsibleFirstName = patientResponsibleFirstName;
	}

	public String getPatientResponsibleMiddleName() {
		return patientResponsibleMiddleName;
	}

	public void setPatientResponsibleMiddleName(String patientResponsibleMiddleName) {
		this.patientResponsibleMiddleName = patientResponsibleMiddleName;
	}

	public String getPatientResponsibleLastName() {
		return patientResponsibleLastName;
	}

	public void setPatientResponsibleLastName(String patientResponsibleLastName) {
		this.patientResponsibleLastName = patientResponsibleLastName;
	}

	public String getPatientResponsibleRelationship() {
		return patientResponsibleRelationship;
	}

	public void setPatientResponsibleRelationship(String patientResponsibleRelationship) {
		this.patientResponsibleRelationship = patientResponsibleRelationship;
	}

	public String getPatientBirthingFacilityName() {
		return patientBirthingFacilityName;
	}

	public void setPatientBirthingFacilityName(String patientBirthingFacilityName) {
		this.patientBirthingFacilityName = patientBirthingFacilityName;
	}

	public String getPatientMultipleBirthIndicator() {
		return patientMultipleBirthIndicator;
	}

	public void setPatientMultipleBirthIndicator(String patientMultipleBirthIndicator) {
		this.patientMultipleBirthIndicator = patientMultipleBirthIndicator;
	}

	public String getPatientMultipleBirthOrder() {
		return patientMultipleBirthOrder;
	}

	public void setPatientMultipleBirthOrder(String patientMultipleBirthOrder) {
		this.patientMultipleBirthOrder = patientMultipleBirthOrder;
	}

	public String getPatientProviderfacilitylevel() {
		return patientProviderfacilitylevel;
	}

	public void setPatientProviderfacilitylevel(String patientProviderfacilitylevel) {
		this.patientProviderfacilitylevel = patientProviderfacilitylevel;
	}

	public String getPatientIISLevel() {
		return patientIISLevel;
	}

	public void setPatientIISLevel(String patientIISLevel) {
		this.patientIISLevel = patientIISLevel;
	}

	public String[] getDatesAny() {
		return datesAny;
	}

	public void setDatesAny(String[] datesAny) {
		this.datesAny = datesAny;
	}

	public String getID() {
 		return ID;
 	}

 	public void setID(String iD) {
 		ID = iD;
 	}
 	
    
    public String getAliasBoy() {
      return aliasBoy;
    }

    public void setAliasBoy(String aliasBoy) {
      this.aliasBoy = aliasBoy;
    }

    public String getAliasGirl() {
      return aliasGirl;
    }

    public void setAliasGirl(String aliasGirl) {
      this.aliasGirl = aliasGirl;
    }

    public String getMotherSsn() {
      return motherSsn;
    }

    public void setMotherSsn(String motherSsn) {
      this.motherSsn = motherSsn;
    }

    public String getMotherDob() {
      return motherDob;
    }

    public void setMotherDob(String motherDob) {
      this.motherDob = motherDob;
    }

    public String getAdminByFirstName() {
      return adminByFirstName;
    }

    public void setAdminByFirstName(String adminByFirstName) {
      this.adminByFirstName = adminByFirstName;
    }

    public String getAdminByLastName() {
      return adminByLastName;
    }

    public void setAdminByLastName(String adminByLastName) {
      this.adminByLastName = adminByLastName;
    }

    public String getAdminByMiddleName() {
      return adminByMiddleName;
    }

    public void setAdminByMiddleName(String adminByMiddleName) {
      this.adminByMiddleName = adminByMiddleName;
    }

    public String getAdminByNPI() {
      return adminByNPI;
    }

    public void setAdminByNPI(String adminByNPI) {
      this.adminByNPI = adminByNPI;
    }

    public String[] getResponsibleOrg() {
      return responsibleOrg;
    }

    public void setResponsibleOrg(String[] responsibleOrg) {
      this.responsibleOrg = responsibleOrg;
    }

    public String[] getAdminOrg1() {
      return adminOrg1;
    }

    public void setAdminOrg1(String[] adminOrg1) {
      this.adminOrg1 = adminOrg1;
    }

    public String[] getAdminOrg2() {
      return adminOrg2;
    }

    public void setAdminOrg2(String[] adminOrg2) {
      this.adminOrg2 = adminOrg2;
    }

    public String getEnteredByFirstName() {
      return enteredByFirstName;
    }

    public void setEnteredByFirstName(String enteredByFirstName) {
      this.enteredByFirstName = enteredByFirstName;
    }

    public String getEnteredByLastName() {
      return enteredByLastName;
    }

    public void setEnteredByLastName(String enteredByLastName) {
      this.enteredByLastName = enteredByLastName;
    }

    public String getEnteredByMiddleName() {
      return enteredByMiddleName;
    }

    public void setEnteredByMiddleName(String enteredByMiddleName) {
      this.enteredByMiddleName = enteredByMiddleName;
    }

    public String getEnteredByNPI() {
      return enteredByNPI;
    }

    public void setEnteredByNPI(String enteredByNPI) {
      this.enteredByNPI = enteredByNPI;
    }

    public String getOrderedByFirstName() {
      return orderedByFirstName;
    }

    public void setOrderedByFirstName(String orderedByFirstName) {
      this.orderedByFirstName = orderedByFirstName;
    }

    public String getOrderedByLastName() {
      return orderedByLastName;
    }

    public void setOrderedByLastName(String orderedByLastName) {
      this.orderedByLastName = orderedByLastName;
    }

    public String getOrderedByMiddleName() {
      return orderedByMiddleName;
    }

    public void setOrderedByMiddleName(String orderedByMiddleName) {
      this.orderedByMiddleName = orderedByMiddleName;
    }

    public String getOrderedByNPI() {
      return orderedByNPI;
    }

    public void setOrderedByNPI(String orderedByNPI) {
      this.orderedByNPI = orderedByNPI;
    }

    public String getStreet2() {
      return street2;
    }

    public void setStreet2(String street2) {
      this.street2 = street2;
    }

    public String getPhoneAlt() {
      return phoneAlt;
    }

    public void setPhoneAlt(String phoneAlt) {
      this.phoneAlt = phoneAlt;
    }

    public String getPhoneAltArea() {
      return phoneAltArea;
    }

    public void setPhoneAltArea(String phoneAltArea) {
      this.phoneAltArea = phoneAltArea;
    }

    public String getPhoneAltLocal() {
      return phoneAltLocal;
    }

    public void setPhoneAltLocal(String phoneAltLocal) {
      this.phoneAltLocal = phoneAltLocal;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getWic() {
      return wic;
    }

    public void setWic(String wic) {
      this.wic = wic;
    }

    public String getSsn()
    {
      return ssn;
    }

    public void setSsn(String ssn)
    {
      this.ssn = ssn;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public int getBirthCount() {
        return birthCount;
    }

    public void setBirthCount(int birthCount) {
        this.birthCount = birthCount;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public String getMedicaidNumber() {
      return medicaidNumber;
    }

    public void setMedicaidNumber(String medicaidNumber) {
      this.medicaidNumber = medicaidNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getDates() {
        return datesAny;
    }

    public void setDates(String[] dates) {
        this.datesAny = dates;
    }

    public String getDifferentLastName() {
        return differentLastName;
    }

    public void setDifferentLastName(String differentLastName) {
        this.differentLastName = differentLastName;
    }

    public String[] getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String[] ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleNameBoy() {
        return middleNameBoy;
    }

    public void setMiddleNameBoy(String middleNameBoy) {
        this.middleNameBoy = middleNameBoy;
    }

    public String getMiddleNameGirl() {
        return middleNameGirl;
    }

    public void setMiddleNameGirl(String middleNameGirl) {
        this.middleNameGirl = middleNameGirl;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea;
    }

    public String getPhoneLocal() {
        return phoneLocal;
    }

    public void setPhoneLocal(String phoneLocal) {
        this.phoneLocal = phoneLocal;
    }

    public String[] getRace() {
        return race;
    }

    public void setRace(String[] race) {
        this.race = race;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String[] getVaccine1() {
        return vaccine1;
    }

    public void setVaccine1(String[] vaccine1) {
        this.vaccine1 = vaccine1;
    }

    public String[] getVaccine2() {
        return vaccine2;
    }

    public void setVaccine2(String[] vaccine2) {
        this.vaccine2 = vaccine2;
    }

    public String[] getVaccine3() {
        return vaccine3;
    }

    public void setVaccine3(String[] vaccine3) {
        this.vaccine3 = vaccine3;
    }

    public String[] getCombo() {
      return combo;
  }

  public void setCombo(String[] combo) {
      this.combo = combo;
  }

    public PatientType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(PatientType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String[] getVfc() {
        return vfc;
    }

    public void setVfc(String[] vfc) {
        this.vfc = vfc;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMedicalRecordNumber()
    {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber)
    {
        this.medicalRecordNumber = medicalRecordNumber;
    }
}
