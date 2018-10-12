package application;

public class JobSeeker {

	private String firstName;
	private String surnameName;
	
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the surnameName
	 */
	public String getSurname() {
		return surnameName;
	}



	/**
	 * @param surnameName the surnameName to set
	 */
	public void setSurname(String surnameName) {
		this.surnameName = surnameName;
	}


	public String getInfo() {
		return "JobSeeker [firstName=" + firstName + ",\nsurnameName=" + surnameName + "]";
	}





}
