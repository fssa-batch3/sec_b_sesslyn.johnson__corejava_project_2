package in.fssa.minimal.model;


public abstract class UserEntity implements Comparable<UserEntity> {
	private String name;
	private int id;
	private String email;
	private String password;
	private long phoneNumber;
	private boolean isDesigner;
	private boolean isActive = true;

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
	public int compareTo(UserEntity other) {
		return Integer.compare(this.id, other.getId());
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", id=" + id + ", email=" + email + ", password=" + password
				+ ", phoneNumer=" + phoneNumber + ", isDesigner=" + isDesigner + ", isActive=" + isActive + "]";
	}
}