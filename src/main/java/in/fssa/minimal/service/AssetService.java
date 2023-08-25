package in.fssa.minimal.service;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.validator.DesignAssetValidator;


public class AssetService {
	/**
	 * Creates a new asset with the provided URL.
	 *
	 * @param newAsset The Asset object containing the URL of the new asset.
	 * @throws ValidationException  If the Asset object is null or the URL is
	 *                              invalid.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 * @throws PersistenceException If a database error occurs while creating the
	 *                              asset.
	 */
	public void createAsset(Asset newAsset) throws ValidationException, ServiceException {
		try {
			if (newAsset == null) {
				throw new ValidationException("Asset object can not be null");
			}
			DesignAssetValidator.validateAssetUrl(newAsset.getAssetsUrl());

			AssetDAO assetDAO = new AssetDAO();
			assetDAO.create(newAsset);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while adding assets", e);
		}
	}

	/**
	 * Updates the URL of an existing asset.
	 *
	 * @param id          The ID of the asset to update.
	 * @param updateAsset The Asset object containing the updated URL.
	 * @throws ValidationException  If the URL is invalid or the ID doesn't exist.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              asset.
	 */
	public void updateAsset(int id, Asset updateAsset) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateAssetUrl(updateAsset.getAssetsUrl());
			DesignAssetValidator.validateAssetId(id);
			AssetDAO assetDAO = new AssetDAO();
			assetDAO.update(id, updateAsset);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while updating asset url", e);
		}
	}

	/**
	 * Retrieves an asset by its ID.
	 *
	 * @param id The ID of the asset to retrieve.
	 * @return An Asset object representing the requested asset.
	 * @throws ValidationException  If the ID is invalid or doesn't exist.
	 * @throws ServiceException     If a service-related error occurs during the
	 *                              operation.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              asset.
	 */
	public static Asset findByAssetId(int id) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateAssetId(id);
			AssetDAO assetDAO = new AssetDAO();
			return assetDAO.findById(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieves data using asset id", e);
		}
	}

}
