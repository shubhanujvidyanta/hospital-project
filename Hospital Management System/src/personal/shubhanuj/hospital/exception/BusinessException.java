/**
 * 
 */
package personal.shubhanuj.hospital.exception;

/**
 * @author Shubhanuj
 *
 */
public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2837183018578746354L;
	public BusinessException(String message){
		super(message);
		System.out.println(message);;
	}
	public BusinessException()
	{
		super("Input value exception.");
		System.out.println("Input value exception.");
	}

}
