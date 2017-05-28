package personal.shubhanuj.hospital.registry;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class AllDepartmentsAndDoctors {
	
	 private DataSource dataSource;
	 private JdbcTemplate jdbcTemplateObject;
	  
	 public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	   }
	public List<Departments> getAllDepartments(){
		String allDepartmentSQL="select departments_id,details from departments";
		List<Departments> departments=jdbcTemplateObject.query(allDepartmentSQL, new DepartmentMapper());
		return departments;
	}
	public List<Doctors> getAllDoctors(){
		String allDoctorsSQL="select a.doctor_id as doctor_id,a.departments_id as departments_id,b.first_name as first_name,b.last_name as last_name from doctor a join doctordetails b on a.doctor_id=b.doctor_id";
		List<Doctors> doctors=jdbcTemplateObject.query(allDoctorsSQL, new DoctorMapper());
		return doctors;
	}
	public List<AppointmentSlots> getAllAppointmentSlots(){
		String allAppointmentSlotsSQL="select appointmentslots_id,strttime,endtime from appointmentslots";
		List<AppointmentSlots> appointmentSlots=jdbcTemplateObject.query(allAppointmentSlotsSQL, new AppointmentSlotMapper());
		return appointmentSlots;
	}
}
