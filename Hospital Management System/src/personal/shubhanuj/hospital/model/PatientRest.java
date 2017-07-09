package personal.shubhanuj.hospital.model;
/*
 * Demo class for Spring rest service, not used in project.
 * */
 

public class PatientRest {
	private int id;
	private String name;
	
	public PatientRest(int id,String name){
		this.id=id;
		this.name=name;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
