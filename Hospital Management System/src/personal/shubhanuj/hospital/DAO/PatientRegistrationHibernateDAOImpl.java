package personal.shubhanuj.hospital.DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.model.Member;
import personal.shubhanuj.hospital.model.Patient;
import personal.shubhanuj.hospital.model.PatientDetails;
import personal.shubhanuj.hospital.model.PatientRegistration;
import personal.shubhanuj.hospital.utils.DatabaseTableUtils;

public class PatientRegistrationHibernateDAOImpl implements PatientRegistrationDao {
	
	private  SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	Connection connection=null;
	int memberId;
	int mbrroleId;
	int addressId;

	@Override
	public void insertMember() throws ApplicationException {
		Session session=factory.openSession();
		Transaction tx=null;
		try{
			connection=factory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
			memberId=Integer.parseInt(DatabaseTableUtils.getLastIdforTable(connection,"member"));
			mbrroleId=Integer.parseInt(DatabaseTableUtils.getMbrroleId(connection,"patient"));
			addressId=Integer.parseInt(DatabaseTableUtils.getLastIdforTable(connection,"address"));
			tx=session.beginTransaction();
			session.persist(new Member(memberId, mbrroleId));
			tx.commit();
		}
		catch(Exception exp){
			exp.printStackTrace();
			tx.rollback();
			throw new ApplicationException(exp.getMessage());
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.close();
		}
	}

	@Override
	public String insertPatient(PatientRegistration registrationBean) throws ApplicationException {
		Session session=factory.openSession();
		Transaction tx=null;
		try{
			tx=session.beginTransaction();
			session.save(new Patient(memberId,1,5,0,new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
			tx.commit();
			insertPatientDetails(registrationBean);
			
		}
		catch(Exception exp){
			exp.printStackTrace();
			tx.rollback();
			throw new ApplicationException(exp.getMessage());
		}
		finally{
			session.close();
		}
		return String.valueOf(memberId);
		
	}
	public void insertPatientDetails(PatientRegistration registrationBean) throws ApplicationException {
		Session session=factory.openSession();
		Transaction tx=null;
		try{
			insertAddress(registrationBean);
			tx=session.beginTransaction();
			session.save(new PatientDetails(memberId, registrationBean.getFirstName(), registrationBean.getLastName(), addressId, 
					registrationBean.getPhone(), null, registrationBean.getEmail(), "N/A"));
			insertRegistration(registrationBean);
			tx.commit();
		}
		catch(Exception exp){
			exp.printStackTrace();
			tx.rollback();
			throw new ApplicationException(exp.getMessage());
		}
		finally{
			session.close();
		}
	}
	
	public void insertAddress(PatientRegistration registrationBean) throws ApplicationException {
		
		try{
			connection=factory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
			DatabaseTableUtils.insertAddress(connection, String.valueOf(addressId));
			connection.commit();
		}
		catch(Exception exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void insertRegistration(PatientRegistration registrationBean) throws ApplicationException {
		try{
			connection=factory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
			DatabaseTableUtils.insertRegistration(connection,String.valueOf(memberId),registrationBean.getEmail(),registrationBean.getPassword());
			connection.commit();
			updateAssociatedTables(registrationBean);
		}
		catch(Exception exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public void updateAssociatedTables(PatientRegistration registrationBean) throws ApplicationException {

		try{
			connection.prepareStatement("update tablekeys set id_value="+memberId+" where table_name='member'").executeUpdate();
			connection.prepareStatement("update tablekeys set id_value="+addressId+" where table_name='address'").executeUpdate();
			connection.commit();
		}
		catch(Exception exp){
			exp.printStackTrace();
			throw new ApplicationException(exp.getMessage());
		}
		
	}
}
