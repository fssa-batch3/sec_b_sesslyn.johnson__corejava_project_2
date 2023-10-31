package in.fssa.minimal.model;

public abstract class DesignAssetEntity {
	private int id;
	private int designId;
	private int assetsId;
	private int designerId;
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
	public int getDesignerId() {
		return designerId;
	}
	public void setDesignerId(int designerId) {
		this.designerId = designerId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "DesignAssetEntity [id=" + id + ", designId=" + designId + ", assetsId=" + assetsId + ", designerId="
				+ designerId + ", isActive=" + isActive + "]";
	}

}
