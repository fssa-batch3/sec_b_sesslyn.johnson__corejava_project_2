package in.fssa.minimal.dto;

import in.fssa.minimal.model.User;

public class AppointmentRespondDTO {

	private int id;
	private User fromUser;
	private User toUser;
	private String status;
	private String email;
	private long phoneNumber;

	private String date;
	private String time;
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "AppointmentRespondDTO [id=" + id + ", fromUser=" + fromUser + ", toUser=" + toUser + ", status="
				+ status + ", email=" + email + ", phoneNumber=" + phoneNumber + ", date=" + date + ", time=" + time
				+ ", address=" + address + "]";
	}

}
