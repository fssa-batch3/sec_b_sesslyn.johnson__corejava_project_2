package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AddressDAO;
import in.fssa.minimal.dao.OrderDAO;
import in.fssa.minimal.enums.OrderStatusEnum;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Order;
import in.fssa.minimal.util.StringUtil;

public class OrderValidator {
	
	public static void validateOrder(Order newOrder) throws ValidationException, ServiceException {
		if (newOrder == null) {
			throw new ValidationException("Order object cannot be null");
		}
		validatePrice(newOrder.getPrice());
		ProductValidator.validateQuantity(newOrder.getQuantity());
		validatePayment(newOrder.getPayment());
		validateStatus(newOrder.getStatus());
		UserValidator.validateUserId(newOrder.getUserId());
		UserValidator.validateSellerId(newOrder.getSellerId());
		validateUserAddressId(newOrder.getAddressId(), newOrder.getUserId());
		ProductValidator.validateProductId(newOrder.getProductId());
		
	}

	public static void validateOrderId(int orderId) throws ValidationException, ServiceException {
		try {
			UserValidator.validateId("Order Id", orderId);
			OrderDAO.checkIdExists(orderId); 
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation", e);
		}
	}

	public static void validateStatus(String status) throws ValidationException {
		StringUtil.rejectIfInvalidString(status, "Status");
		if (OrderStatusEnum.getStatus(status).equals("non")) {
			throw new ValidationException(
					"Invalid status value. The status can only be one of: waiting_list,rejected,delivered,cancelled");
		}
	}
	
	public static void validatePrice(int price) throws ValidationException {
		  if (price < 300 || price > 100000000) { 
		        throw new ValidationException("Price should be in the range between 300 and 10 crores (100 million)");
		    }
	}
	
	public static void validatePayment(String payment) throws ValidationException {
	    StringUtil.rejectIfInvalidString(payment, "Payment");

	    if (!("Net Banking".equals(payment) || "Cash On Delivery".equals(payment) || "Credit / Debit cards".equals(payment))) {
	        throw new ValidationException("Invalid payment method");
	    }
	}

	public static void validateUserAddressId(int addressId, int userId) throws ValidationException, ServiceException {
		UserValidator.validateId("Address Id", addressId);
		try {
			AddressDAO.checkUserAddressIdExists(addressId, userId);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage()); 
		}
	}
}
