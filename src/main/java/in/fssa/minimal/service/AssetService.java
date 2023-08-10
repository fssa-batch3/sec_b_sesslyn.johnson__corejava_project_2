package in.fssa.minimal.service;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.validator.AssetExists;
import in.fssa.minimal.validator.DesignValidator;

public class AssetService {
	public void create(Asset newAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateName(newAsset.getAssetsUrl());
		AssetExists.chechAssetUrlExists(newAsset.getAssetsUrl());
		AssetDAO assetDao = new AssetDAO();
		assetDao.create(newAsset);
	}

	public void update(int id, Asset updateAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateName(updateAsset.getAssetsUrl());
		AssetExists.chechAssetUrlExists(updateAsset.getAssetsUrl());
		AssetDAO assetDao = new AssetDAO();
		assetDao.update(id, updateAsset);
	}

}
