/**
 * 
 */
package personal.shubhanuj.hospital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import personal.shubhanuj.hospital.model.PatientAppointment;
import personal.shubhanuj.hospital.model.PatientRegistration;
import personal.shubhanuj.hospital.registry.AllDepartmentsAndDoctors;
import personal.shubhanuj.hospital.registry.AppointmentSlots;
import personal.shubhanuj.hospital.registry.Departments;
import personal.shubhanuj.hospital.registry.Doctors;

/**
 * @author Shubhanuj
 *
 */

@Controller
public class IndexController implements MessageSourceAware{
	
	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value="/welcome")
	   public ModelAndView welcome(Model model,HttpServletRequest request) {
		WebApplicationContext context=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		ModelAndView modelAndView=new ModelAndView();
		PatientRegistration registrationBean=(PatientRegistration) context.getBean("registration");
		PatientAppointment appointmentBean=(PatientAppointment) context.getBean("appointment");
		AllDepartmentsAndDoctors deptDoc= (AllDepartmentsAndDoctors) context.getBean("departmentAndDoctors");
		List<Departments> departments=deptDoc.getAllDepartments();
		List<Doctors> doctors=deptDoc.getAllDoctors();	
		List<AppointmentSlots> slots=deptDoc.getAllAppointmentSlots();
		model.addAttribute("message", messageSource.getMessage("message", null, "Welcome!", null));
		model.addAttribute("consultationCharges", messageSource.getMessage("consultCharge", null, "free", null));
		modelAndView.setViewName("welcome");
		modelAndView.addObject("patientRegistration", registrationBean);
		modelAndView.addObject("patientAppointment", appointmentBean);
		modelAndView.addObject("departmentList",departments);
		modelAndView.addObject("doctorList",doctors);
		modelAndView.addObject("appointmentSlots",slots);
		return modelAndView;
	 }
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub
		this.messageSource=messageSource;
		//System.out.println("set messagesource");
		
	}

}
