/**
 * 
 */
package personal.shubhanuj.hospital.validation;

import java.util.HashMap;
import java.util.List;

import personal.shubhanuj.hospital.DAO.PatientAppointmentJDBCTemplate;
import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.exception.BusinessException;
import personal.shubhanuj.hospital.model.PatientAppointment;

/**
 * @author Shubhanuj
 *
 */
public class AppointmentValidation {
	private PatientAppointmentJDBCTemplate patientAppObj;	
	private HashMap<String,String> appointmentDetails;
	
	public HashMap<String,String> validateAppointment(PatientAppointment appointment)throws ApplicationException,BusinessException {
		appointmentDetails=new HashMap<String,String>();
		String selectedDepartment=appointment.getDepartment();
		String doctorId=appointment.getDoctor();
		String appointmentDate=appointment.getAppointmentDate();
		String appointmentSlot=appointment.getAppointmentSlot();
		if(validateDoctorForDepartment(selectedDepartment, doctorId) &&  validateAppointmentSlotForDoctor(doctorId, appointmentDate, appointmentSlot)){
			appointmentDetails=patientAppObj.createAppointment(appointment);
		}
		return appointmentDetails;
	}
	public boolean validateDoctorForDepartment(String departmentId,String doctorId){
		boolean isValidDoctorForSelectedDepartment=false;
		if(patientAppObj.validateDoctorAgainstDepartment(departmentId, doctorId)){
			isValidDoctorForSelectedDepartment=true;
		}
		return isValidDoctorForSelectedDepartment;		
	}
	public boolean validateAppointmentSlotForDoctor(String doctorId,String appointmentDate,String appointmentSlot) throws BusinessException{
		boolean isValidAppointmentForSelectedDoctor=false;
		List<String> appointmentSlotIds=patientAppObj.checkSlotAvailabilityForDoctor(doctorId, appointmentDate);
		if(appointmentSlotIds.contains(appointmentSlot)){
			throw new BusinessException("The selected appointment slot is not available, please select another slot.");
		}
		else{
			isValidAppointmentForSelectedDoctor=true;
		}
		return isValidAppointmentForSelectedDoctor;
	}
	/**
	 * @param patientAppObj the patientAppObj to set
	 */
	public void setPatientAppObj(PatientAppointmentJDBCTemplate patientAppObj) {
		this.patientAppObj = patientAppObj;
	}

}
