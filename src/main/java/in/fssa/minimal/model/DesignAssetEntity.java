package in.fssa.minimal.model;

public class DesignAssetEntity {
	private int id;

	private int designerId;
	private int assetsId;
	private boolean isActive = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDesignerId() {
		return designerId;
	}

	public void setDesignerId(int designerId) {
		this.designerId = designerId;
	}

	public int getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(int assetsId) {
		this.assetsId = assetsId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DesignAssetEntity [id=" + id + ", designerId=" + designerId + ", assetsId=" + assetsId + ", isActive="
				+ isActive + "]";
	}
}
