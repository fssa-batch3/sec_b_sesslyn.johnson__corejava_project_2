package in.fssa.minimal.model;

public abstract class UserEntity {

	private String name;
	private int id;
	private String email;
	private String password;
	private String image;
	private String gender;
	private String role;
	private String date_of_birth;
	private long phoneNumber;
	private boolean isActive = true;
	
	private String gst_number;
	private long  aadhar_number;
	private String  shop_address;
	private int experience;
	private String designer_description;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDateOfBirth() {
		return date_of_birth;
	}

	public void setDateOfBirth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGst_number() {
		return gst_number;
	}

	public void setGst_number(String gst_number) {
		this.gst_number = gst_number;
	}

	public long getAadhar_number() {
		return aadhar_number;
	}

	public void setAadhar_number(long aadhar_number) {
		this.aadhar_number = aadhar_number;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getDesigner_description() {
		return designer_description;
	}

	public void setDesigner_description(String designer_description) {
		this.designer_description = designer_description;
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + ", image="
				+ image + ", gender=" + gender + ", role=" + role + ", date_of_birth=" + date_of_birth
				+ ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + ", gst_number=" + gst_number
				+ ", aadhar_number=" + aadhar_number + ", shop_address=" + shop_address + ", experience=" + experience
				+ ", designer_description=" + designer_description + "]";
	}
}