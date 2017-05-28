/**
 * 
 */
package personal.shubhanuj.hospital.exception;

/**
 * @author Shubhanuj
 *
 */
public class ApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2837183018578746354L;
	public ApplicationException(String message){
		super(message);
		System.out.println(message);;
	}
	public ApplicationException()
	{
		super("Fatal system exception.");
		System.out.println("Fatal system exception.");
	}

}
