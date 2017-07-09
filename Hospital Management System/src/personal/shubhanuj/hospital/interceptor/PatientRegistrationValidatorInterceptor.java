/**
 * 
 */
package personal.shubhanuj.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Shubhanuj
 *
 */
public class PatientRegistrationValidatorInterceptor extends HandlerInterceptorAdapter  {
	
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler){
		boolean processRequest=true;
		System.out.println("preHandle for patient registration");
		return processRequest;
				
	}
	@Override
	public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView){
		
		System.out.println("postHandle for patient registration");				
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after completion for patient Registration");
	}

}
