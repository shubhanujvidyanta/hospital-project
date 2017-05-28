package personal.shubhanuj.hospital.registry;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DoctorMapper implements RowMapper<Doctors>{
	public Doctors mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Doctors doctor = new Doctors();
	      doctor.setDoctorId(rs.getString("doctor_id"));
	      doctor.setDepartmentId(rs.getString("departments_id"));
	      doctor.setDoctorFirstName(rs.getString("first_name"));
	      doctor.setDoctorLastName(rs.getString("last_name"));
	      
	      return doctor;
	   }
}
