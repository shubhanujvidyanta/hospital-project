/**
 * Spring Rest Demo controller, not used in project.
 */
package personal.shubhanuj.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import personal.shubhanuj.hospital.model.PatientRest;

/**
 * @author Shubhanuj
 *
 */
@RestController
public class PatientRestController {

	
	@RequestMapping(value="/patients", method = RequestMethod.GET)
	public List<PatientRest> getAllPatients(){
		System.out.println("getAllPatients;");
		List<PatientRest> myList=new ArrayList<PatientRest>();
		PatientRest patient1=new PatientRest(1,"a");
		PatientRest patient2=new PatientRest(2,"b");
		PatientRest patient3=new PatientRest(3,"c");
		PatientRest patient4=new PatientRest(4,"d");
		PatientRest patient5=new PatientRest(5,"e");
		myList.add(patient1);
		myList.add(patient2);
		myList.add(patient3);
		myList.add(patient4);
		myList.add(patient5);
		return myList;
		
	}

}
