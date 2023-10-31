package in.fssa.minimal.dto;

import in.fssa.minimal.model.Category;
import in.fssa.minimal.model.User;

public class ProductRespondDTO {

	private int id;
	private String name;
	private String imageUrl;
	private String description;
	private User sellerId;
	private Category categoryId;
	private int price;
	private int discount;
	private int quantity;
	private String warranty;
	private boolean isActive;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getSellerId() {
		return sellerId;
	}

	public void setSellerId(User sellerId) {
		this.sellerId = sellerId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ProductRespondDTO [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", description="
				+ description + ", sellerId=" + sellerId + ", categoryId=" + categoryId + ", price=" + price
				+ ", discount=" + discount + ", quantity=" + quantity + ", warranty=" + warranty + ", isActive="
				+ isActive + "]";
	}
}
