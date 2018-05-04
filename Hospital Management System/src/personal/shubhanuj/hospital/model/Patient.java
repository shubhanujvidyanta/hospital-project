package personal.shubhanuj.hospital.model;

import javax.persistence.Entity;

@Entity
public class Patient {
	private int patientId;
	private int patientTypeId;
	private int admitTypeId;
	private int admit;
	private String RegistrationDate;
	
	public Patient(int a,int b,int c,int d,String e){
		patientId=a;
		patientTypeId=b;
		admitTypeId=c;
		admit=d;
		RegistrationDate=e;
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @return the patientTypeId
	 */
	public int getPatientTypeId() {
		return patientTypeId;
	}

	/**
	 * @return the admitTypeId
	 */
	public int getAdmitTypeId() {
		return admitTypeId;
	}

	/**
	 * @return the admit
	 */
	public int getAdmit() {
		return admit;
	}

	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return RegistrationDate;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @param patientTypeId the patientTypeId to set
	 */
	public void setPatientTypeId(int patientTypeId) {
		this.patientTypeId = patientTypeId;
	}

	/**
	 * @param admitTypeId the admitTypeId to set
	 */
	public void setAdmitTypeId(int admitTypeId) {
		this.admitTypeId = admitTypeId;
	}

	/**
	 * @param admit the admit to set
	 */
	public void setAdmit(int admit) {
		this.admit = admit;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		RegistrationDate = registrationDate;
	}
	
	
}
