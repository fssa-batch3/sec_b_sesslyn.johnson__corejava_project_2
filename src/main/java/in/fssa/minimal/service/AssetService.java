package in.fssa.minimal.service;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.validator.AssetExists;
import in.fssa.minimal.validator.DesignValidator;

public class AssetService {
	/**
	 * 
	 * @param newAsset
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void create(Asset newAsset) throws ValidationException, PersistenceException {
		if(newAsset == null) {
			throw new ValidationException("Asset object can not be null");
		}
		DesignValidator.validateName(newAsset.getAssetsUrl());
		AssetExists.checkAssetUrlExists(newAsset.getAssetsUrl());
		AssetDAO assetDao = new AssetDAO();
		assetDao.create(newAsset);
	}

	/**
	 * 
	 * @param id
	 * @param updateAsset
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void update(int id, Asset updateAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateName(updateAsset.getAssetsUrl());
		AssetExists.checkAssetUrlExists(updateAsset.getAssetsUrl());
		DesignValidator.validateId(id);
	    AssetExists.checkIdExists(id);
		AssetDAO assetDao = new AssetDAO();
		assetDao.update(id, updateAsset);
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public static Asset findByAssetId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
	    AssetExists.checkIdExists(id);
	    AssetDAO assetDao = new AssetDAO();
		return assetDao.findById(id);
	}

}
