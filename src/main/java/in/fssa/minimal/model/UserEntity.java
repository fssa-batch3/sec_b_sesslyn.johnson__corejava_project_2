package in.fssa.minimal.model;


public abstract class UserEntity {
	
	private String name; 
	private int id;
	private String email;
	private String password;
	private String image;
	private long phoneNumber;
	private boolean isDesigner;
	private boolean isActive = true;
	
//	public UserEntity(String name, String email, String password, String image, long phoneNumber, boolean isDesigner) {
//		super();
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.image = image;
//		this.phoneNumber = phoneNumber;
//		this.isDesigner = isDesigner;
//	}

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

	public void setPassword(String passsword) {
		this.password = passsword;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isDesigner() {
		return isDesigner;
	}

	public void setDesigner(boolean isDesigner) {
		this.isDesigner = isDesigner;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password + ", image="
				+ image + ", phoneNumber=" + phoneNumber + ", isDesigner=" + isDesigner + ", isActive=" + isActive
				+ "]";
	}
	
}