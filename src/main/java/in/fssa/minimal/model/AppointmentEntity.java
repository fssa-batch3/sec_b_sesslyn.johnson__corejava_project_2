package in.fssa.minimal.model;

public abstract class AppointmentEntity {

	private int id;
	private int fromUser;
	private int toUser;
	private String email;
	private long phoneNumber;
	private String status;
	private String date; 
	private String time;
	private String address;
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	@Override
	public String toString() {
		return "AppointmentEntity [id=" + id + ", fromUser=" + fromUser + ", toUser=" + toUser + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", status=" + status + ", date=" + date + ", time=" + time
				+ ", address=" + address + "]";
	}

}