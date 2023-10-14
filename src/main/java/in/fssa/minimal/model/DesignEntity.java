package in.fssa.minimal.model;

public class DesignEntity {

	private int id;
	private String name; 
	private String description;
	private String location;
	private int styleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}



	@Override
	public String toString() {
		return "DesignEntity [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", styleId=" + styleId + "]";
	}
}
