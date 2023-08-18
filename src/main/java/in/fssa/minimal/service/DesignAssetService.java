package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.validator.AssetExists;
import in.fssa.minimal.validator.DesignAssetExists;
import in.fssa.minimal.validator.DesignExists;
import in.fssa.minimal.validator.DesignValidator;


public class DesignAssetService {
	/**
	 * 
	 * @param newDesignAsset
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void create(DesignAsset newDesignAsset) throws ValidationException, PersistenceException {
		if(newDesignAsset == null) {
			throw new ValidationException("Design Asset Object can't be null");
		}
		DesignValidator.validateId(newDesignAsset.getDesignId());
		DesignValidator.validateId(newDesignAsset.getAssetsId());
		DesignExists.checkIdExists(newDesignAsset.getDesignId());
		AssetExists.checkIdExists(newDesignAsset.getAssetsId());
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.create(newDesignAsset);
	}
/**
 * 
 * @param id
 * @param updatedDesignAsset
 * @throws ValidationException
 * @throws PersistenceException
 */
	public void update(int id, DesignAsset updatedDesignAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		
		if (updatedDesignAsset.getDesignId() != 0) {
			DesignValidator.validateId(updatedDesignAsset.getDesignId());
			DesignExists.checkIdExists(updatedDesignAsset.getDesignId());
		}
		if (updatedDesignAsset.getAssetsId() != 0) {
			DesignValidator.validateId(updatedDesignAsset.getAssetsId());
			AssetExists.checkIdExists(updatedDesignAsset.getAssetsId());
		}

		DesignAssetExists.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.update(id, updatedDesignAsset);
	}
/**
 * 
 * @param id
 * @throws ValidationException
 * @throws PersistenceException
 */
	public void delete(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		DesignAssetExists.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.delete(id);
	}
/**
 * 
 * @return
 * @throws ValidationException
 * @throws PersistenceException
 */
	public Set<DesignAssetRespondDto> getAllByDesignAsset() throws ValidationException, PersistenceException {
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		Set<DesignAssetRespondDto> appList = designAssetDao.findAllDesignAsset();
		for (DesignAssetRespondDto app : appList) {
			System.out.println(app);
		}
		return appList;
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public DesignAssetRespondDto findAllDesignAssetById(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		DesignAssetExists.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		return designAssetDao.findAllDesignAssetById(id);
	}
}
