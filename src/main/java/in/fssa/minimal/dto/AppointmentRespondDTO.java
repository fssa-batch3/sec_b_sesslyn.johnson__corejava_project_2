package in.fssa.minimal.dto;

import in.fssa.minimal.model.User;

public class AppointmentRespondDTO {

	private int id;
	private User fromUser;
	private User toUser;
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
		return "AppointmentRespondDto [fromUser=" + fromUser + ", toUser=" + toUser + ", status=" + status + ", date="
				+ date + ", time=" + time + ", address=" + address + ", id=" + id + "]";
	}


}
