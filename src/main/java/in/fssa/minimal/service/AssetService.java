package in.fssa.minimal.service;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.util.StringUtil;
import in.fssa.minimal.validator.DesignValidator;

public class AssetService {
	/**
	 * Creates a new asset with the provided URL.
	 *
	 * @param newAsset The Asset object containing the URL of the new asset.
	 * @throws ValidationException  If the Asset object is null or the URL is
	 *                              invalid.
	 * @throws PersistenceException If a database error occurs while creating the
	 *                              asset.
	 */
	public void create(Asset newAsset) throws ValidationException, PersistenceException {
		if (newAsset == null) {
			throw new ValidationException("Asset object can not be null");
		}
		StringUtil.rejectIfInvalidString(newAsset.getAssetsUrl(), "Asset Url Name");
		AssetDAO.checkAssetUrlExists(newAsset.getAssetsUrl());
		AssetDAO assetDao = new AssetDAO();
		assetDao.create(newAsset);
	}

	/**
	 * Updates the URL of an existing asset.
	 *
	 * @param id          The ID of the asset to update.
	 * @param updateAsset The Asset object containing the updated URL.
	 * @throws ValidationException  If the URL is invalid or the ID doesn't exist.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              asset.
	 */
	public void update(int id, Asset updateAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateName(updateAsset.getAssetsUrl());
		AssetDAO.checkAssetUrlExists(updateAsset.getAssetsUrl());
		DesignValidator.validateId(id);
		AssetDAO.checkIdExists(id);
		AssetDAO assetDao = new AssetDAO();
		assetDao.update(id, updateAsset);
	}

	/**
	 * Retrieves an asset by its ID.
	 *
	 * @param id The ID of the asset to retrieve.
	 * @return An Asset object representing the requested asset.
	 * @throws ValidationException  If the ID is invalid or doesn't exist.
	 * @throws PersistenceException If a database error occurs while retrieving the
	 *                              asset.
	 */
	public static Asset findByAssetId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		AssetDAO.checkIdExists(id);
		AssetDAO assetDao = new AssetDAO();
		return assetDao.findById(id);
	}

}
