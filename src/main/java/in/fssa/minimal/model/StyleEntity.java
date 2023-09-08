package in.fssa.minimal.model;

public abstract class StyleEntity {
	private int id;
	private String name;
	private boolean isActive = true;

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "StyleEntity [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}
}
