package in.fssa.minimal.model;

public class DesignAssetEntity {
	 
	private int id;
	private int designId;
	private int assetsId;
	private boolean isActive = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDesignId() {
		return designId;
	}

	public void setDesignId(int designId) {
		this.designId = designId;
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
		return "DesignAssetEntity [id=" + id + ", designId=" + designId + ", assetsId=" + assetsId + ", isActive="
				+ isActive + "]";
	}
}
