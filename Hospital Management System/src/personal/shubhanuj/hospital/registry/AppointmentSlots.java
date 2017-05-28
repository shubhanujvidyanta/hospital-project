/**
 * 
 */
package personal.shubhanuj.hospital.registry;

/**
 * @author Shubhanuj
 *
 */
public class AppointmentSlots {
	private String appointmentSlotId;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private String appointmentTime;
	/**
	 * @return the appointmentSlotId
	 */
	public String getAppointmentSlotId() {
		return appointmentSlotId;
	}
	/**
	 * @param appointmentSlotId the appointmentSlotId to set
	 */
	public void setAppointmentSlotId(String appointmentSlotId) {
		this.appointmentSlotId = appointmentSlotId;
	}
	/**
	 * @return the appointmentStartTime
	 */
	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	/**
	 * @param appointmentStartTime the appointmentStartTime to set
	 */
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	/**
	 * @return the appointmentEndTime
	 */
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	/**
	 * @param appointmentEndTime the appointmentEndTime to set
	 */
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	/**
	 * @return the appointmentTime
	 */
	public String getAppointmentTime() {
		return (appointmentStartTime+"-"+appointmentEndTime);
	}
	

}
