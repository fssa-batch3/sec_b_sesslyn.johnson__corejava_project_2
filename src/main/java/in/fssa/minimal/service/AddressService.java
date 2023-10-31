 package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.AddressDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.util.StringUtil;
import in.fssa.minimal.validator.AddressValidator;
import in.fssa.minimal.validator.UserValidator;

public class AddressService {
	public void createAddress(Address newAddress) throws ValidationException, ServiceException {
		try {
			AddressValidator.validateAddress(newAddress);
			AddressDAO addressDAO = new AddressDAO();
			addressDAO.create(newAddress);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while creating user.", e);
		}
	}

	public void updateAddress(int addressId, Address updatedAddress)
			throws ValidationException, ServiceException { 
		try {
			AddressValidator.validateAddressId(addressId);
			if(updatedAddress == null) {
				throw new ValidationException("Address object cannot be null");
			}
			if (updatedAddress.getName() != null) {
				UserValidator.validateName(updatedAddress.getName());
			}
			if (updatedAddress.getEmail() != null) {
				UserValidator.validateAllEmail(updatedAddress.getEmail()); 
			}
			if (updatedAddress.getPhoneNumber() != 0) {
				UserValidator.validatePhoneNumber(updatedAddress.getPhoneNumber());
			}
			if (updatedAddress.getAddress() != null) {
				StringUtil.rejectIfInvalidString(updatedAddress.getAddress(), "Address"); 
			}
			if (updatedAddress.getCity() != null) {
				AddressValidator.validatePlaces(updatedAddress.getCity(), "City");
			}
			if (updatedAddress.getState() != null) {
				AddressValidator.validatePlaces(updatedAddress.getState(), "State");
			}
			if (updatedAddress.getCountry() != null) {
				AddressValidator.validatePlaces(updatedAddress.getCountry(), "Country");
			}
			if (updatedAddress.getPincode() > 0) {
				AddressValidator.validatePlaces(updatedAddress.getTitle(), "Title");
			}
			if (updatedAddress.getTitle() != null) {
				AddressValidator.validatePincode(updatedAddress.getPincode());
			}
			if (updatedAddress.getUserId() > 0) {
				UserValidator.validateUserId(updatedAddress.getUserId());
			}
			AddressDAO addressDAO = new AddressDAO();
			addressDAO.update(addressId, updatedAddress);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while updating design", e);
		}
	}

	public void deleteAddress(int addressId) throws ValidationException, ServiceException {
		try {
			AddressValidator.validateAddressId(addressId);
			AddressDAO addressDAO = new AddressDAO();
			addressDAO.delete(addressId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while deleting user.", e);
		}
	}

	public Set<Address> getAllAddress() throws ServiceException, ValidationException {
	    try {
	    	AddressDAO addressDAO = new AddressDAO();
	        return addressDAO.findAllAddress(); 
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}    
	
	public Set<Address> getAllAddressByUserId(int userId) throws ServiceException, ValidationException {
	    try {
	    	UserValidator.validateUserId(userId);
	    	AddressDAO addressDAO = new AddressDAO();
	        return addressDAO.findAllAddressByUserId(userId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}    
	
	public static Address getAddressById(int addressId) throws ServiceException, ValidationException {
	    try {
	    	AddressValidator.validateAddressId(addressId);
	    	AddressDAO addressDAO = new AddressDAO();
	        return addressDAO.findAddressById(addressId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}   
	
	public void updateDefaultStatus(int userId, int addressId) throws ServiceException, ValidationException {
	    try {
	    	AddressDAO addressDAO = new AddressDAO();
	         addressDAO.updateDefaultStatus(userId, addressId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving users.", e);
	    }
	}   
}
