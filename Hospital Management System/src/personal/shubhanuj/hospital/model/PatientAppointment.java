/**
 * 
 */
package personal.shubhanuj.hospital.model;

/**
 * @author Shubhanuj
 *
 */
public class PatientAppointment {
	private String patientId;
	private String department;
	private String doctor;
	private String appointmentDate;
	private String appointmentSlot;
	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the doctor
	 */
	public String getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return appointmentDate;
	}
	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	/**
	 * @return the appointmentSlot
	 */
	public String getAppointmentSlot() {
		return appointmentSlot;
	}
	/**
	 * @param appointmentSlot the appointmentSlot to set
	 */
	public void setAppointmentSlot(String appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}

}
