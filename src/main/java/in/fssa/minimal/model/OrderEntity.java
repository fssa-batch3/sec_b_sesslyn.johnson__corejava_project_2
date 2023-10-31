package in.fssa.minimal.model;

import java.sql.Date;

public abstract class OrderEntity {

	private int id;
	private int price;
	private int quantity;
	private String payment;
	private String status;
	private Date deliveryDate;
	private int userId;
	private int sellerId;
	private int addressId;
	private int productId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", price=" + price + ", quantity=" + quantity + ", payment=" + payment
				+ ", status=" + status + ", deliveryDate=" + deliveryDate + ", userId=" + userId + ", sellerId="
				+ sellerId + ", addressId=" + addressId + ", productId=" + productId + "]";
	}
}
