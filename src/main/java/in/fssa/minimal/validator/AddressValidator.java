package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AddressDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Address;
import in.fssa.minimal.util.StringUtil;

public class AddressValidator {
	private static final String CITY_PATTERN = "^[a-zA-Z\\s]+$";

	public static void validateAddress(Address address) throws ValidationException, ServiceException {
		if (address == null) {
			throw new ValidationException("Address object cannot be null");
		}
		UserValidator.validateName(address.getName());
		UserValidator.validateAllEmail(address.getEmail()); 
		UserValidator.validatePhoneNumber(address.getPhoneNumber());
		StringUtil.rejectIfInvalidString(address.getAddress(), "Address");
		validatePlaces(address.getCity(), "City"); 
		validatePlaces(address.getState(), "State");
		validatePlaces(address.getCountry(), "Country");
		validatePlaces(address.getTitle(), "Title");
		validatePincode(address.getPincode());
		UserValidator.validateUserId(address.getUserId());
	}
	
	public static void validatePlaces(String places,String value) throws ValidationException {
		StringUtil.rejectIfInvalidString(places, value);
	    if (!places.matches(CITY_PATTERN)) {
	        throw new ValidationException( value + " should only contain alphabets and spaces");
	    }

	}
	
	public static void validatePincode(int pincode) throws ValidationException {
	    String pincodeStr = String.valueOf(pincode);
	    if (pincodeStr.matches("6[0-9]{5}")) {
	    } else {
	        throw new ValidationException("Pincode should start with 6 and have 6 digits");
	    }
	}

	public static void validateAddressId(int addressId) throws ValidationException, ServiceException {
		UserValidator.validateId("Address Id", addressId);
		try {
			AddressDAO.checkAddressIdExists(addressId);
		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage()); 
		}
	}
	
	
}
