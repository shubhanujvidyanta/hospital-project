package personal.shubhanuj.hospital.registry;

public class Doctors {
	private String doctorId;
	private String departmentId;
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorName;
	/**
	 * @return the doctorId
	 */
	public String getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * @return the doctorFirstName
	 */
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	/**
	 * @param doctorFirstName the doctorFirstName to set
	 */
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	/**
	 * @return the doctorLastName
	 */
	public String getDoctorLastName() {
		return doctorLastName;
	}
	/**
	 * @param doctorLastName the doctorLastName to set
	 */
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return (doctorFirstName+" "+doctorLastName);
	}

}
