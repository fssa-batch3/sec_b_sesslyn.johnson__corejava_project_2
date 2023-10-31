package in.fssa.minimal.service;

import java.util.Set;
import in.fssa.minimal.dao.OrderDAO;
import in.fssa.minimal.dto.OrderRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Order;
import in.fssa.minimal.validator.OrderValidator;
import in.fssa.minimal.validator.UserValidator;

public class OrderService {

	public void createOrder(Order newOrder) throws ValidationException, ServiceException {
		try { 
			OrderValidator.validateOrder(newOrder);
			OrderDAO orderDAO = new OrderDAO();   
			orderDAO.create(newOrder); 
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while creating appointment", e);
		} 
	} 
	
	public void updateOrderRequestStatus(int orderId, String status)
			throws ValidationException, ServiceException { 
		try {
			OrderValidator.validateOrderId(orderId);
			OrderValidator.validateStatus(status);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.updateRequestStatus(orderId, status);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while updating appointment status", e);
		}
	}
	
	public Set<OrderRespondDTO> getAllOrder() throws ServiceException, ValidationException {
		try {
			OrderDAO orderDAO = new OrderDAO();
			Set<OrderRespondDTO> orderList = orderDAO.findAllOrders();
			return orderList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving all appointments", e);
		}
	}
	
	public Set<OrderRespondDTO> getAllOrderByUserId(int userId)
			throws ValidationException, ServiceException {
		try {
			UserValidator.validateUserId(userId);
			OrderDAO orderDAO = new OrderDAO();
			Set<OrderRespondDTO> orderList = orderDAO.findAllOrdersByUserId(userId);
			return orderList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	} 
	
	public Set<OrderRespondDTO> getAllOrderBySellerId(int sellerId)
			throws ValidationException, ServiceException {
		try {
			UserValidator.validateSellerId(sellerId);
			OrderDAO orderDAO = new OrderDAO();
			Set<OrderRespondDTO> orderList = orderDAO.findAllOrdersBySellerId(sellerId);
			return orderList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	}
	
	public OrderRespondDTO getOrderById(int orderId)
			throws ValidationException, ServiceException {
		try {
			OrderValidator.validateOrderId(orderId);
			OrderDAO orderDAO = new OrderDAO();
			OrderRespondDTO order = orderDAO.findOrderById(orderId);
			return order;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving appointments by their status", e);
		}
	}

}
