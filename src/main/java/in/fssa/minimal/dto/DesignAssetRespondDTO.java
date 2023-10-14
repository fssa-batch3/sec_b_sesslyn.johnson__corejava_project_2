package in.fssa.minimal.dto;

import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;

public class DesignAssetRespondDTO { 

	private int id;
	private Design designId;
	private Asset assetsId;
	private int designerId;
	private boolean isActive = true; 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Design getDesignId() {
		return designId;
	}
	public void setDesignId(Design designId) {
		this.designId = designId;
	}
	public Asset getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(Asset assetsId) {
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
		return "DesignAssetRespondDTO [id=" + id + ", designId=" + designId + ", assetsId=" + assetsId + ", designerId="
				+ designerId + ", isActive=" + isActive + "]";
	}
}
