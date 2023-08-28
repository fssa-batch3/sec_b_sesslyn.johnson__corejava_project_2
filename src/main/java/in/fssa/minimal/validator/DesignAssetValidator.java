package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.util.StringUtil;

public class DesignAssetValidator {

	public static void validateDesignAsset(DesignAsset newDesignAsset) throws ValidationException, ServiceException {
		if (newDesignAsset == null) {
			throw new ValidationException("Design Asset Object cannot be null");
		}
		DesignValidator.validateDesignId(newDesignAsset.getDesignId());
		validateAssetId(newDesignAsset.getAssetsId());
	}

	/**
	 * Validates an ID value.
	 *
	 * @param id The ID to be validated.
	 * @throws ValidationException If the ID is not valid (less than or equal to
	 *                             zero).
	 */
	public static void validateId(String name,int id) throws ValidationException {
	    if (id <= 0) {
	        throw new ValidationException(name + " cannot be less than or equal to zero");
	    }
	}
	
	public static void validateDesignAssetId(int designAssetId) throws ValidationException, ServiceException {
		try {
			validateId("Design Asset Id",designAssetId);
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
	 * @throws ServiceException    If a service-related error occurs during
	 *                             validation.
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
	 * @param id The ID value to be validated.
	 * @throws ValidationException If the ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             validation.
	 */
	public static void validateAssetId(int assetId) throws ValidationException, ServiceException {
		try {
			validateId("Asset Id",assetId);
			AssetDAO.checkIdExists(assetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during asset id validation.", e);
		}
	}
}
