/**
 * 
 */
package personal.shubhanuj.hospital.utils;

import java.util.HashMap;

/**
 * @author Shubhanuj
 *
 */
public class ApplicationUtils {
	
	public static String generateTransactionId(){
		StringBuilder txnId=new StringBuilder();
		String transaction="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		for(int i=0;i<10;i++){
			int position=(int) (Math.floor(Math.ceil(Math.random()*1000))%transaction.length());
			txnId.append(transaction.charAt(position));
		}
		return txnId.toString();
	}
	public static HashMap<String, String> calculateBillAmount(String amount,String taxPerc){
		double tax=100+Double.parseDouble(taxPerc);
		double billAmount=(Math.round((Double.parseDouble(amount)/tax)*100)/100);
		double taxAmount=Double.parseDouble(amount)-billAmount;
		HashMap<String,String> transactionAmount=new HashMap<String,String>();
		transactionAmount.put("billAmount",((Double)billAmount).toString());
		System.out.println(((Double)billAmount).toString());
		transactionAmount.put("taxAmount",((Double)taxAmount).toString());
		return transactionAmount;
	}

}
