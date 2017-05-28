/**
 * 
 */
package personal.shubhanuj.hospital.DAO;

import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.model.PatientRegistration;

/**
 * @author Shubhanuj
 *
 */
public interface PatientRegistrationDao {
	public void insertMember() throws ApplicationException;
	public String insertPatient(PatientRegistration registrationBean) throws ApplicationException;

}
