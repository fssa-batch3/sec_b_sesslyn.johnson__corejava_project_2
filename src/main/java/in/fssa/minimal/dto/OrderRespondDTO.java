 package in.fssa.minimal.dto;

import java.sql.Date;

import in.fssa.minimal.model.Address;
import in.fssa.minimal.model.Product;
import in.fssa.minimal.model.User;

public class OrderRespondDTO {

	private int id;
	private int price;
	private int quantity;
	private String payment;
	private String status;
	private Date deliveryDate;
	private User userId;
	private User sellerId;
	private Address addressId;
	private ProductRespondDTO productId;
	private String createdDate;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

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

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public User getSellerId() {
		return sellerId;
	}

	public void setSellerId(User sellerId) {
		this.sellerId = sellerId;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public ProductRespondDTO getProductId() {
		return productId;
	}

	public void setProductId(ProductRespondDTO productObj) {
		this.productId = productObj;
	}

	@Override
	public String toString() {
		return "OrderRespondDTO [id=" + id + ", price=" + price + ", quantity=" + quantity + ", payment=" + payment
				+ ", status=" + status + ", deliveryDate=" + deliveryDate + ", userId=" + userId + ", sellerId="
				+ sellerId + ", addressId=" + addressId + ", productId=" + productId + ", createdDate=" + createdDate
				+ "]";
	}
}
