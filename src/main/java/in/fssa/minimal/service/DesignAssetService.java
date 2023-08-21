package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.validator.DesignValidator;

public class DesignAssetService {
	/**
	 * Creates a new design asset.
	 *
	 * @param newDesignAsset The DesignAsset object to be created.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void create(DesignAsset newDesignAsset) throws ValidationException, PersistenceException {
		if (newDesignAsset == null) {
			throw new ValidationException("Design Asset Object can't be null");
		}
		DesignValidator.validateId(newDesignAsset.getDesignId());
		DesignValidator.validateId(newDesignAsset.getAssetsId());
		DesignDAO.checkIdExists(newDesignAsset.getDesignId());
		AssetDAO.checkIdExists(newDesignAsset.getAssetsId());
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.create(newDesignAsset);
	}

	/**
	 * Updates an existing design asset.
	 *
	 * @param id                 The ID of the design asset to be updated.
	 * @param updatedDesignAsset The updated DesignAsset object.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void update(int id, DesignAsset updatedDesignAsset) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);

		if (updatedDesignAsset.getDesignId() != 0) {
			DesignValidator.validateId(updatedDesignAsset.getDesignId());
			DesignDAO.checkIdExists(updatedDesignAsset.getDesignId());
		}
		if (updatedDesignAsset.getAssetsId() != 0) {
			DesignValidator.validateId(updatedDesignAsset.getAssetsId());
			AssetDAO.checkIdExists(updatedDesignAsset.getAssetsId());
		}

		DesignAssetDAO.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.update(id, updatedDesignAsset);
	}

	/**
	 * Deletes a design asset by ID.
	 *
	 * @param id The ID of the design asset to be deleted.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void delete(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		DesignAssetDAO.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		designAssetDao.delete(id);
	}

	/**
	 * Retrieves all active design assets.
	 *
	 * @return A set of DesignAssetRespondDto objects representing all active design
	 *         assets.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
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
	 * Retrieves a specific design asset by ID.
	 *
	 * @param id The ID of the design asset to be retrieved.
	 * @return The DesignAssetRespondDto object representing the design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public DesignAssetRespondDto findAllDesignAssetById(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		DesignAssetDAO.checkIdExists(id);
		DesignAssetDAO designAssetDao = new DesignAssetDAO();
		return designAssetDao.findAllDesignAssetById(id);
	}
}
