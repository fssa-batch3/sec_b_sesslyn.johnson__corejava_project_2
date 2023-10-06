package in.fssa.minimal.model;

public abstract class ReviewEntity {

	private int id;
	private String feedback;
	private int ratings;
	private int appointment_id;
	private int from_user;
	private int to_user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback; 
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
 
	public int getFrom_user() {
		return from_user;
	}

	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}

	public int getTo_user() {
		return to_user;
	}

	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}

	@Override
	public String toString() {
		return "ReviewEntity [id=" + id + ", feedback=" + feedback + ", ratings=" + ratings + ", appointment_id="
				+ appointment_id + ", from_user=" + from_user + ", to_user=" + to_user + "]";
	}
}
