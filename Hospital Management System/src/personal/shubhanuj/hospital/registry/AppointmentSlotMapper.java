/**
 * 
 */
package personal.shubhanuj.hospital.registry;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Shubhanuj
 *
 */
public class AppointmentSlotMapper implements RowMapper<AppointmentSlots>{
	public AppointmentSlots mapRow(ResultSet rs, int rowNum) throws SQLException {
	      AppointmentSlots appointmentSlot = new AppointmentSlots();
	      appointmentSlot.setAppointmentSlotId(rs.getString("appointmentslots_id"));
	      appointmentSlot.setAppointmentStartTime(rs.getString("strttime"));
	      appointmentSlot.setAppointmentEndTime(rs.getString("endtime"));
	      
	      return appointmentSlot;
	   }
}
