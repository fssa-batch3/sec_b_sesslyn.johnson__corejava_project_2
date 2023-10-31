package in.fssa.minimal.model;

public abstract class AssetEntity {
	private int id;
	private String assetsUrl;

	public int getId() {
		return id;
	} 

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetsUrl() {
		return assetsUrl;
	}

	public void setAssetsUrl(String assetsUrl) {
		this.assetsUrl = assetsUrl;
	}

	@Override
	public String toString() {
		return "AssetEntity [id=" + id + ", assetsUrl=" + assetsUrl + "]";
	}
}
