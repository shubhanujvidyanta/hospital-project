/**
 * 
 */
package personal.shubhanuj.hospital.utils;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shubhanuj
 *
 */
public class ClientUtils {
	
	public static String getClientIpAddress(HttpServletRequest request) {
	    String xForwardedForHeader = request.getHeader("X-Forwarded-For");
	    String ipAddress=null;
	    if (xForwardedForHeader == null) {
	    	ipAddress=request.getRemoteAddr();
	        System.out.println("Client IP: " + ipAddress);
	    } else {
	        // The general format of the field is: X-Forwarded-For: client, proxy1, proxy2 ...
	        // we only want the client
	    	ipAddress=new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
	        System.out.println("Client IP: " + ipAddress);
	    }
		return ipAddress;
	}

}
