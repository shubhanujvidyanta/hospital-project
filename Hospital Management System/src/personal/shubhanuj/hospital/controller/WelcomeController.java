/**
 * 
 */
package personal.shubhanuj.hospital.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import personal.shubhanuj.hospital.DAO.PatientAppointmentJDBCTemplate;
import personal.shubhanuj.hospital.DAO.PatientRegistrationDao;
import personal.shubhanuj.hospital.DAO.PatientRegistrationDaoImpl;
import personal.shubhanuj.hospital.DAO.PatientRegistrationHibernateDAOImpl;
import personal.shubhanuj.hospital.exception.ApplicationException;
import personal.shubhanuj.hospital.exception.BusinessException;
import personal.shubhanuj.hospital.model.PatientAppointment;
import personal.shubhanuj.hospital.model.PatientRegistration;
import personal.shubhanuj.hospital.validation.AppointmentValidation;

/**
 * @author Shubhanuj
 *
 */
@Controller

public class WelcomeController {
	@Autowired
	private MessageSource messageSource;
	PatientRegistration registrationBean=null;
	PatientAppointment appointmentBean=null;
	WebApplicationContext context=null;
	ModelAndView modelAndView=null;
	PatientRegistrationDao registrationDAO=null;
	HashMap<String,String> appointmentDetails=null;
	
	@RequestMapping(value="/appointment", method = RequestMethod.POST)
   public ModelAndView welcome(@ModelAttribute("patientAppointment") PatientAppointment appointment,BindingResult result,Model model,HttpServletRequest request) {
		context=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		modelAndView=new ModelAndView();
		registrationBean=(PatientRegistration) context.getBean("registration");
		appointmentBean=(PatientAppointment) context.getBean("appointment");
		try{			
			if (result.hasErrors()) {
				modelAndView.setViewName("error");
			}
			AppointmentValidation appValidationObj=(AppointmentValidation) context.getBean("appointmentValidation");
			appointmentDetails=appValidationObj.validateAppointment(appointment);
			if(!appointmentDetails.isEmpty()){
				model.addAttribute("appointmentDetails", appointmentDetails);
			}
			else{
				model.addAttribute("appointmentDetails", null);
			}
			modelAndView.setViewName("appointmentSuccess");
		}
		catch(ApplicationException exp){
			modelAndView.setViewName("error");
		}
		catch(BusinessException exp){
			System.out.println("exception:"+exp);
			modelAndView.setViewName("forward:/welcome");
			model.addAttribute("errorMsg",exp.getMessage());
			model.addAttribute("message", messageSource.getMessage("message", null, "Welcome!", null));
			model.addAttribute("consultationCharges", messageSource.getMessage("consultCharge", null, "free", null));
			modelAndView.addObject("patientRegistration", registrationBean);
			modelAndView.addObject("patientAppointment", appointmentBean);
		}
		
		return modelAndView;
	   }
	
	@RequestMapping(value="/registration")
	public ModelAndView registration(@ModelAttribute("patientRegistration") PatientRegistration registration,BindingResult result,Model model,HttpServletRequest request){
		context=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		modelAndView=new ModelAndView();
		registrationBean=(PatientRegistration) context.getBean("registration");
		appointmentBean=(PatientAppointment) context.getBean("appointment");
		try{			
			if (result.hasErrors()) {
				modelAndView.setViewName("error");
			}
			if (!registration.getPassword().equals(registration.getConfPassword())){			
				model.addAttribute("error",messageSource.getMessage("passwordMismatch", null, "Password Mismatch", null));
				throw new BusinessException(messageSource.getMessage("passwordMismatch", null, "Password Mismatch", null));
			}
			PatientRegistrationDao patientRegObj=(PatientRegistrationHibernateDAOImpl) context.getBean("patientRegistration");
			patientRegObj.insertMember();
			String patientId=patientRegObj.insertPatient(registration);
			model.addAttribute("registrationSuccess",messageSource.getMessage("registrationSuccess", null, "!", null));
			model.addAttribute("Id",patientId);
			model.addAttribute("Name",registration.getFirstName()+" "+registration.getLastName());
			model.addAttribute("Email",registration.getEmail());
			model.addAttribute("Password",registration.getPassword());
			modelAndView.setViewName("registrationSuccess");
		}
		catch(ApplicationException exp){
			modelAndView.setViewName("error");
		}
		catch(BusinessException exp){
			modelAndView.setViewName("forward:/welcome");
			model.addAttribute("message", messageSource.getMessage("message", null, "Welcome!", null));
			model.addAttribute("consultationCharges", messageSource.getMessage("consultCharge", null, "free", null));
			modelAndView.addObject("patientRegistration", registrationBean);
			modelAndView.addObject("patientAppointment", appointmentBean);
		} 
		return modelAndView;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub
		this.messageSource=messageSource;
	}
   
}
