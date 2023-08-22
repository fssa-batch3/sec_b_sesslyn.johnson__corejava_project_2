package in.fssa.minimal.validator;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.util.StringUtil;

public class DesignAssetValidator {

	public static void validate(DesignAsset newDesignAsset) throws ValidationException, ServiceException {
		if (newDesignAsset == null) {
			throw new ValidationException("Design Asset Object cannot be null");
		}
		DesignValidator.validateDesignId(newDesignAsset.getDesignId());
		validateAssetId(newDesignAsset.getAssetsId());
	}

	public static void validateDesignAssetId(int id) throws ValidationException, ServiceException {
		try {
			if (id <= 0) {
				throw new ValidationException("ID cannot be less than or equal to zero");
			}
			DesignAssetDAO.checkIdExists(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation.", e);
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
			throw new ServiceException("Error occurred during validation.", e);
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
	public static void validateAssetId(int id) throws ValidationException, ServiceException {
		try {
			if (id <= 0) {
				throw new ValidationException("ID cannot be less than or equal to zero");
			}
			AssetDAO.checkIdExists(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during validation.", e);
		}
	}
}
