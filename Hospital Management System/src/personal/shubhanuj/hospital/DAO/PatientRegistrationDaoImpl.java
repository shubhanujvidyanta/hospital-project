/**
 * 
 */
package personal.shubhanuj.hospital.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.model.PatientRegistration;
import personal.shubhanuj.hospital.utils.DBConnection;
import personal.shubhanuj.hospital.utils.DatabaseTableUtils;



/**
 * @author Shubhanuj
 *
 */
public class PatientRegistrationDaoImpl implements PatientRegistrationDao {
	
	Connection connection;
	String memberId;
	String mbrroleId;
	String addressId;
	ResultSet rSet=null;
	
	public PatientRegistrationDaoImpl() throws ApplicationException{
	setConnection(DBConnection.getConnection());
	}
	
	public void insertMember() throws ApplicationException{
		setMemberId(DatabaseTableUtils.getLastIdforTable(connection,"member"));
		setMbrroleId(DatabaseTableUtils.getMbrroleId(connection,"patient"));
		setAddressId(DatabaseTableUtils.getLastAddressId(connection));
		try{
		String insertMemberQuery="insert into member values(?,?,?,?)";
		PreparedStatement prepStatement=connection.prepareStatement(insertMemberQuery);
		prepStatement.setString(1,memberId);
		prepStatement.setString(2,mbrroleId);
		prepStatement.setString(3,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		prepStatement.setString(4,null);
		prepStatement.executeUpdate();
		}catch(SQLException exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
	}
	public String insertPatient(PatientRegistration registrationBean) throws ApplicationException{
		String insertPatientQuery=null;
		String insertPatientDetailsQuery=null;
		String updateTablekeysMember=null;
		String updateTablekeysAddress=null;
		setMemberId(DatabaseTableUtils.getLastIdforTable(connection,"member"));
		setMbrroleId(DatabaseTableUtils.getMbrroleId(connection,"patient"));
		setAddressId(DatabaseTableUtils.getLastAddressId(connection));
		try{
		insertPatientQuery="insert into patient values(?,?,?,?,?)";
		PreparedStatement insertPatientStatement=connection.prepareStatement(insertPatientQuery);
		insertPatientStatement.setString(1,memberId);
		insertPatientStatement.setString(2,"1");
		insertPatientStatement.setString(3,"5");
		insertPatientStatement.setString(4,"0");
		insertPatientStatement.setString(5,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		insertPatientStatement.executeUpdate();
		DatabaseTableUtils.insertAddress(connection, addressId);
		insertPatientDetailsQuery="insert into patientdetails values(?,?,?,?,?,?,?,?)";
		PreparedStatement insertPatientDetailsStatement=connection.prepareStatement(insertPatientDetailsQuery);
		insertPatientDetailsStatement.setString(1,memberId);
		insertPatientDetailsStatement.setString(2,registrationBean.getFirstName());
		insertPatientDetailsStatement.setString(3,registrationBean.getLastName());
		insertPatientDetailsStatement.setString(4,addressId);
		insertPatientDetailsStatement.setString(5,registrationBean.getPhone());
		insertPatientDetailsStatement.setString(6,null);
		insertPatientDetailsStatement.setString(7,registrationBean.getEmail());
		insertPatientDetailsStatement.setString(8,"N/A");
		insertPatientDetailsStatement.executeUpdate();
		DatabaseTableUtils.insertRegistration(connection,memberId,registrationBean.getEmail(),registrationBean.getPassword());
		updateTablekeysMember="update tablekeys set id_value="+memberId+" where table_name='member'";
		updateTablekeysAddress="update tablekeys set id_value="+addressId+" where table_name='address'";
		connection.prepareStatement(updateTablekeysMember).executeUpdate();
		connection.prepareStatement(updateTablekeysAddress).executeUpdate();
		}catch(SQLException exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		return memberId;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @param mbrroleId the mbrroleId to set
	 */
	public void setMbrroleId(String mbrroleId) {
		this.mbrroleId = mbrroleId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
}
