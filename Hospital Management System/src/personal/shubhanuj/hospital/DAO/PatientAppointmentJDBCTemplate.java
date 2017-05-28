package personal.shubhanuj.hospital.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.exception.BusinessException;
import personal.shubhanuj.hospital.model.PatientAppointment;
import personal.shubhanuj.hospital.utils.ApplicationUtils;
import personal.shubhanuj.hospital.utils.DatabaseTableUtils;

public class PatientAppointmentJDBCTemplate implements PatientAppointmentDAO{
	   private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;
	   private String transactionId;
	   
	   public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	   }
	   public HashMap<String,String> createAppointment(PatientAppointment appointment) throws ApplicationException,BusinessException{
		    String patientId=appointment.getPatientId();
		    String appointmentSlotsId=appointment.getAppointmentSlot();
		    String doctorId=appointment.getDoctor();
		    Date appointmentDate=null;
			try {
				appointmentDate = new SimpleDateFormat("yyyy-MM-dd").parse(appointment.getAppointmentDate());
			} catch (ParseException e) {
				throw new ApplicationException();
			}
			String paymentDetailsId;
			String paymentTypeId;
			String billId;
			HashMap<String,String> bill;
			String updateTableKeysPaymentDetails;
			String updateTableKeysPaymentType;
			String updateTableKeysBill;
			String updateTableKeysAppointment;
			HashMap<String,String> appointmentDetails=null;
				transactionId=ApplicationUtils.generateTransactionId();				
				paymentDetailsId=DatabaseTableUtils.getLastIdforTable(DataSourceUtils.getConnection(dataSource),"paymentdetails");
				String paymentDetailsSQL="insert into paymentdetails(paymentdetails_id,comments) values (?,?)";
				jdbcTemplateObject.update(paymentDetailsSQL, paymentDetailsId, transactionId);
				paymentTypeId=DatabaseTableUtils.getLastIdforTable(DataSourceUtils.getConnection(dataSource),"paymenttype");
				String paymentTypeSQL="insert into paymenttype values (?,?,?)";
				jdbcTemplateObject.update(paymentTypeSQL,paymentTypeId,paymentDetailsId,null);
				billId=DatabaseTableUtils.getLastIdforTable(DataSourceUtils.getConnection(dataSource), "bill");
				bill=ApplicationUtils.calculateBillAmount("250", "8.5");
				String billSQL="insert into bill values (?,?,?,?,?,?,?)";
				jdbcTemplateObject.update(billSQL,billId,"3",bill.get("billAmount"),bill.get("taxAmount"),patientId,paymentTypeId,transactionId);
				String appointmentId=DatabaseTableUtils.getLastIdforTable(DataSourceUtils.getConnection(dataSource), "appointment");
				String appointmentSQL="insert into appointment values(?,?,?,?,?,?,?)";
				jdbcTemplateObject.update(appointmentSQL,appointmentId,appointmentSlotsId,appointmentDate,patientId,doctorId,"1",billId);
				updateTableKeysPaymentDetails="update tablekeys set id_value=? where table_name='paymentdetails'";
				updateTableKeysPaymentType="update tablekeys set id_value=? where table_name='paymenttype'";
				updateTableKeysBill="update tablekeys set id_value=? where table_name='bill'";
				updateTableKeysAppointment="update tablekeys set id_value=? where table_name='appointment'";
				jdbcTemplateObject.update(updateTableKeysPaymentDetails,paymentDetailsId);
				jdbcTemplateObject.update(updateTableKeysPaymentType,paymentTypeId);
				jdbcTemplateObject.update(updateTableKeysBill,billId);
				jdbcTemplateObject.update(updateTableKeysAppointment,appointmentId);
				appointmentDetails=new HashMap<String,String>();
				appointmentDetails.put("billId",billId);
				appointmentDetails.put("transactionId",transactionId);
				appointmentDetails.putAll(bill);
				appointmentDetails.put("appointmentId",appointmentId);
				appointmentDetails.put("appointmentDate", appointmentDate.toString());
				appointmentDetails.put("appointmentSlot",appointmentSlotsId);
			return appointmentDetails;
		}
	   public boolean validateDoctorAgainstDepartment(String departmentId,String doctorId){
		   String validateDoctorDepartmentSQL="select departments_id from doctor where doctor_id=?";
		   String departmentIdOfDoctor=jdbcTemplateObject.queryForObject(validateDoctorDepartmentSQL,new Object[]{doctorId},String.class);
		   if(departmentIdOfDoctor.equals(departmentId)){
			   return true;
		   }else{
		   return false;
		   }
	   }
	    public List<String> checkSlotAvailabilityForDoctor(String doctorId,String appointmentDate){
	    	List<String> slotIds=new ArrayList<String>();
	    	String checkFilledSlotForDoctorAndDate="select appointmentslots_id from appointment where doctor_id=? and date=?";
	    	slotIds=jdbcTemplateObject.queryForList(checkFilledSlotForDoctorAndDate,String.class, new Object[]{doctorId,appointmentDate});
	    	return slotIds;
	    }

}
