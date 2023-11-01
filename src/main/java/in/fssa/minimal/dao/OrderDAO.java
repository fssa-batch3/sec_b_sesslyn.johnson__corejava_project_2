package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import in.fssa.minimal.dto.OrderRespondDTO;
import in.fssa.minimal.dto.ProductRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.model.Order;
import in.fssa.minimal.model.User;
import in.fssa.minimal.service.AddressService;
import in.fssa.minimal.service.ProductService;
import in.fssa.minimal.service.UserService;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class OrderDAO {

	public void create(Order newOrder) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO orders (price, quantity, payment, status, user_id, seller_id, address_id, product_id) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, newOrder.getPrice());
			ps.setInt(2, newOrder.getQuantity());
			ps.setString(3, newOrder.getPayment());
			ps.setString(4, newOrder.getStatus());
			ps.setInt(5, newOrder.getUserId()); 
			ps.setInt(6, newOrder.getSellerId());
			ps.setInt(7, newOrder.getAddressId());
			ps.setInt(8, newOrder.getProductId());
			ps.executeUpdate();
			Logger.info("Order has been created successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public void updateRequestStatus(int orderId, String status) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE orders SET status = ? , delivery_date = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, status);
			Date currentDate = Date.valueOf(java.time.LocalDate.now());
			ps.setDate(2, currentDate);
			ps.setInt(3, orderId);
			ps.executeUpdate();
			Logger.info("Order Status has been updated successfully");
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public List<OrderRespondDTO> findAllOrders() throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderRespondDTO> orderList = new ArrayList<>();
		try {
			String query = "SELECT id, price, quantity, payment, status, delivery_date, user_id, seller_id, address_id, product_id,created_date"
					+ " FROM orders";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			User userObj = null;
			User sellerObj = null;
			Address addressObj = null;
			ProductRespondDTO productObj = null;
			OrderRespondDTO order = null;
			while (rs.next()) {

				int userId = rs.getInt("user_id");
				int sellerId = rs.getInt("seller_id");
				int addressId = rs.getInt("address_id");
				int productId = rs.getInt("product_id");

				userObj = UserService.findByUserId(userId);
				sellerObj = UserService.findBySellerId(sellerId);
				addressObj = AddressService.getAddressById(addressId);
				productObj = ProductService.findByProductId(productId);

				order = new OrderRespondDTO();
				order.setId(rs.getInt("id"));
				order.setPrice(rs.getInt("price"));
				order.setPayment(rs.getString("payment"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getString("status"));
				Date deliveryDate = rs.getDate("delivery_date");
				if (!rs.wasNull()) {
					order.setDeliveryDate(deliveryDate);
				} else {
					order.setDeliveryDate(null);
				}
				order.setUserId(userObj);
				order.setSellerId(sellerObj);
				order.setAddressId(addressObj);
				order.setProductId(productObj);
				Date date = rs.getDate("created_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = dateFormat.format(date);
				order.setCreatedDate(dateString);
				orderList.add(order);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return orderList;
	}

	public List<OrderRespondDTO> findAllOrdersByUserId(int userId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderRespondDTO> orderList = new ArrayList<>();
		try {
			String query = "SELECT id, price, quantity, payment, status, delivery_date, user_id, seller_id, address_id, product_id,created_date"
					+ " FROM orders WHERE user_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			User userObj = null;
			User sellerObj = null;
			Address addressObj = null;
			ProductRespondDTO productObj = null;
			OrderRespondDTO order = null;
			while (rs.next()) {

				int userIdOut = rs.getInt("user_id");
				int sellerId = rs.getInt("seller_id");
				int addressId = rs.getInt("address_id");
				int productId = rs.getInt("product_id");

				userObj = UserService.findByUserId(userIdOut);
				sellerObj = UserService.findBySellerId(sellerId);
				addressObj = AddressService.getAddressById(addressId);
				productObj = ProductService.findByProductId(productId);

				order = new OrderRespondDTO();
				order.setId(rs.getInt("id"));
				order.setPrice(rs.getInt("price"));
				order.setPayment(rs.getString("payment"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getString("status"));
				Date deliveryDate = rs.getDate("delivery_date");
				if (!rs.wasNull()) {
					order.setDeliveryDate(deliveryDate);
				} else {
					order.setDeliveryDate(null);
				}
				order.setUserId(userObj);
				order.setSellerId(sellerObj);
				order.setAddressId(addressObj);
				order.setProductId(productObj);
				Date date = rs.getDate("created_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = dateFormat.format(date);
				order.setCreatedDate(dateString);
				orderList.add(order);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return orderList;
	}

	public List<OrderRespondDTO> findAllOrdersBySellerId(int sellerId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderRespondDTO> orderList = new ArrayList<>();
		try {
			String query = "SELECT id, price, quantity, payment, status, delivery_date, user_id, seller_id, address_id, product_id,created_date"
					+ " FROM orders WHERE seller_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, sellerId);
			rs = ps.executeQuery();
			User userObj = null;
			User sellerObj = null;
			Address addressObj = null;
			ProductRespondDTO productObj = null;
			OrderRespondDTO order = null;
			while (rs.next()) {

				int userId = rs.getInt("user_id");
				int sellerIdOut = rs.getInt("seller_id");
				int addressId = rs.getInt("address_id");
				int productId = rs.getInt("product_id");

				userObj = UserService.findByUserId(userId);
				sellerObj = UserService.findBySellerId(sellerIdOut);
				addressObj = AddressService.getAddressById(addressId);
				productObj = ProductService.findByProductId(productId);

				order = new OrderRespondDTO();
				order.setId(rs.getInt("id"));
				order.setPrice(rs.getInt("price"));
				order.setPayment(rs.getString("payment"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getString("status"));
				Date deliveryDate = rs.getDate("delivery_date");
				if (!rs.wasNull()) {
					order.setDeliveryDate(deliveryDate);
				} else {
					order.setDeliveryDate(null);
				}
				order.setUserId(userObj);
				order.setSellerId(sellerObj);
				order.setAddressId(addressObj);
				order.setProductId(productObj);
				Date date = rs.getDate("created_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = dateFormat.format(date);
				order.setCreatedDate(dateString);
				orderList.add(order);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return orderList;
	}

	public OrderRespondDTO findOrderById(int orderId)
			throws ValidationException, PersistenceException, ServiceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderRespondDTO order = null;
		try {
			String query = "SELECT id, price, quantity, payment, status, delivery_date, user_id, seller_id, address_id, product_id,created_date"
					+ " FROM orders WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			User userObj = null;
			User sellerObj = null;
			Address addressObj = null;
			ProductRespondDTO productObj = null;
			if (rs.next()) {

				int userId = rs.getInt("user_id");
				int sellerIdOut = rs.getInt("seller_id");
				int addressId = rs.getInt("address_id");
				int productId = rs.getInt("product_id");

				userObj = UserService.findByUserId(userId);
				sellerObj = UserService.findBySellerId(sellerIdOut);
				addressObj = AddressService.getAddressById(addressId);
				productObj = ProductService.findByProductId(productId);

				order = new OrderRespondDTO();
				order.setId(rs.getInt("id"));
				order.setPrice(rs.getInt("price"));
				order.setPayment(rs.getString("payment"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getString("status"));
				Date deliveryDate = rs.getDate("delivery_date");
				if (!rs.wasNull()) {
					order.setDeliveryDate(deliveryDate);
				} else {
					order.setDeliveryDate(null);
				}
				order.setUserId(userObj);
				order.setSellerId(sellerObj);
				order.setAddressId(addressObj);
				order.setProductId(productObj);
				Date date = rs.getDate("created_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = dateFormat.format(date);
				order.setCreatedDate(dateString);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return order;
	}

	public static void checkIdExists(int orderId) throws ValidationException, PersistenceException {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		try {
			String query = "Select id From orders Where id = ?";
			conn = ConnectionUtil.getConnection();
			pre = conn.prepareStatement(query);
			pre.setInt(1, orderId);
			rs = pre.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Order Id doesn't exist");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(conn, pre, rs);
		}
	}
}
