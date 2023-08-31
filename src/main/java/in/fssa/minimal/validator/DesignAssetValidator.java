package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.util.StringUtil;

public class DesignAssetValidator {

	/**
	 * Validates the combination of a Design object and an Asset object.
	 * Ensures that both Design and Asset objects are not null.
	 *
	 * @param newDesign The Design object to be validated.
	 * @param newAsset  The Asset object to be validated.
	 * @throws ValidationException If either the Design or Asset object is null.
	 */
	public static void validateDesignAsset(Design newDesign, Asset newAsset) throws ValidationException, ServiceException {
	    if (newDesign == null || newAsset == null) {
	        throw new ValidationException("Design object and Asset Object cannot be null");
	    }
	}

	/**
	 * Validates the combination of a Design object and an Asset object for updates.
	 * Ensures that at least one of Design or Asset objects is not null.
	 *
	 * @param newDesign The Design object to be validated.
	 * @param newAsset  The Asset object to be validated.
	 * @throws ValidationException If both the Design and Asset objects are null.
	 */
	public static void validateUpdateDesignAsset(Design newDesign, Asset newAsset) throws ValidationException, ServiceException {
	    if (newDesign == null && newAsset == null) {
	        throw new ValidationException("Design object and Asset Object cannot be null");
	    }
	}

	/**
	 * Validates an ID value.
	 *
	 * @param name The name of the field (for error message).
	 * @param id   The ID to be validated.
	 * @throws ValidationException If the ID is not valid (less than or equal to zero).
	 */
	public static void validateId(String name, int id) throws ValidationException {
	    if (id <= 0) {
	        throw new ValidationException(name + " cannot be less than or equal to zero");
	    }
	}

	/**
	 * Validates a Design Asset ID value.
	 *
	 * @param designAssetId The Design Asset ID value to be validated.
	 * @throws ValidationException If the Design Asset ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during validation.
	 */
	public static void validateDesignAssetId(int designAssetId) throws ValidationException, ServiceException {
	    try {
	        validateId("Design Asset Id", designAssetId);
	        DesignAssetDAO.checkIdExists(designAssetId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during design asset id validation.", e);
	    }
	}

	/**
	 * Validates an asset URL string.
	 *
	 * @param assetUrl The asset URL to be validated.
	 * @throws ValidationException If the asset URL is invalid.
	 * @throws ServiceException    If a service-related error occurs during validation.
	 */
	public static void validateAssetUrl(String assetUrl) throws ValidationException, ServiceException {
	    try {
	        StringUtil.rejectIfInvalidString(assetUrl, "Asset Url Name");
	        AssetDAO.checkAssetUrlExists(assetUrl);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during asset url validation.", e);
	    }
	}

	/**
	 * Validates an Asset ID value.
	 *
	 * @param assetId The Asset ID value to be validated.
	 * @throws ValidationException If the Asset ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during validation.
	 */
	public static void validateAssetId(int assetId) throws ValidationException, ServiceException {
	    try {
	        validateId("Asset Id", assetId);
	        AssetDAO.checkIdExists(assetId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during asset id validation.", e);
	    }
	}

}
