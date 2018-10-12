package application;

public class Hospital {
	
	String name;
	String address;
	String faxNumber;
	String phoneNumber;
	String email;
	Integer rating;
	Integer Id;
	Hospital(){
		
	}
	Hospital(Integer id,String name,String address,String fax,String phone, String email,Integer rating){
		this.name=name;
		Id=id;
		this.address=address;
		this.faxNumber=fax;
		this.phoneNumber=phone;
		this.email=email;
		this.rating=rating;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
