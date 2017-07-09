/**
 * 
 */
package personal.shubhanuj.hospital.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import personal.shubhanuj.hospital.exception.ApplicationException;

/**
 * @author Shubhanuj
 *
 */
public class DatabaseTableUtils {
	
	public static String getLastIdforTable(Connection connection,String tablename) throws ApplicationException {
		String Id=null;
		try{
		String memberIdQuery="select id_value+1 as id from tablekeys where table_name=?";
		PreparedStatement fetchMemberId=connection.prepareStatement(memberIdQuery);
		fetchMemberId.setString(1,tablename);
		ResultSet rSet=fetchMemberId.executeQuery();
		if (rSet.next()){
			Id=rSet.getString("id");
		}
		}catch(SQLException exp){
			Id=null;
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		return Id;
	}
	public static String getMbrroleId(Connection connection,String role) throws ApplicationException {
		String mbrroleId=null;
		try{
		String mbrroleIdQuery="select mbrrole_id from mbrrole where role=?";
		PreparedStatement fetchMbrroleId=connection.prepareStatement(mbrroleIdQuery);
		fetchMbrroleId.setString(1,"patient");
		ResultSet rSet=fetchMbrroleId.executeQuery();
		if (rSet.next()){
		mbrroleId=rSet.getString("mbrrole_id");}
		}catch(SQLException exp){
			mbrroleId=null;
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		return mbrroleId;
	}
	/*public static String getLastAddressId(Connection connection) throws ApplicationException{
		String addressId=null;
		try{
			String addressIdQuery="select id_value+1 as address_id from tablekeys where table_name=?";
			PreparedStatement fetchAddressId=connection.prepareStatement(addressIdQuery);
			fetchAddressId.setString(1,"address");
			ResultSet rSet=fetchAddressId.executeQuery();
			if (rSet.next()){
			addressId=rSet.getString("address_id");
			}
			
		}
		catch(SQLException exp){
			addressId=null;
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		return addressId;
	}*/
	public static void insertAddress(Connection connection,String addressId) throws ApplicationException{
		try{
			String insertAddressQuery="insert into address values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insertAddressStatement=connection.prepareStatement(insertAddressQuery);
			insertAddressStatement.setString(1, addressId);
			insertAddressStatement.setString(2, null);
			insertAddressStatement.setString(3,null );
			insertAddressStatement.setString(4,null );
			insertAddressStatement.setString(5,null );
			insertAddressStatement.setString(6, null);
			insertAddressStatement.setString(7, null);
			insertAddressStatement.setString(8, null);
			insertAddressStatement.setString(9, null);
			insertAddressStatement.setString(10, null);
			insertAddressStatement.setString(11, "1");
			insertAddressStatement.executeUpdate();
		}
		catch(SQLException exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());			
		}
	}
	public static void insertRegistration(Connection connection,String memberId,String email,String password) throws ApplicationException{
		try{
			String insertRegistrationQuery="insert into registration values(?,?,?,?)";
			PreparedStatement insertRegistrationStatement=connection.prepareStatement(insertRegistrationQuery);
			insertRegistrationStatement.setString(1,memberId);
			insertRegistrationStatement.setString(2,email);
			insertRegistrationStatement.setString(3,password);
			insertRegistrationStatement.setString(4,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
			insertRegistrationStatement.executeUpdate();
		}
		catch(SQLException exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}		
	}

}
