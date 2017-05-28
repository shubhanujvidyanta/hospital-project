package personal.shubhanuj.hospital.registry;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper implements RowMapper<Departments>{
	public Departments mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Departments department = new Departments();
	      department.setDepartmentId(((Integer)(rs.getInt("departments_id"))).toString());
	      department.setDepartmentName(rs.getString("details"));
	      
	      return department;
	   }
}
