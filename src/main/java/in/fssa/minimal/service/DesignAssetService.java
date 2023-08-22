package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dto.DesignAssetRespondDto;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.validator.DesignAssetValidator;
import in.fssa.minimal.validator.DesignValidator;

public class DesignAssetService {
	/**
	 * Creates a new design asset.
	 *
	 * @param newDesignAsset The DesignAsset object to be created.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException    If an error occurs during the service operation.
	 */
	public void create(DesignAsset newDesignAsset) throws ValidationException, PersistenceException, ServiceException {
	    try {
	        DesignAssetValidator.validate(newDesignAsset);
	        DesignAssetDAO designAssetDao = new DesignAssetDAO();
	        designAssetDao.create(newDesignAsset);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during connecting design and thier assets.", e);
	    }
	}

	/**
	 * Updates an existing design asset.
	 *
	 * @param id                 The ID of the design asset to be updated.
	 * @param updatedDesignAsset The updated DesignAsset object.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException    If an error occurs during the service operation.
	 */
	public void update(int id, DesignAsset updatedDesignAsset) throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateDesignAssetId(id);

	        if (updatedDesignAsset.getDesignId() != 0) {
	            DesignValidator.validateDesignId(updatedDesignAsset.getDesignId());
	        }
	        if (updatedDesignAsset.getAssetsId() != 0) {
	            DesignAssetValidator.validateAssetId(updatedDesignAsset.getAssetsId());
	        }

	        DesignAssetDAO designAssetDao = new DesignAssetDAO();
	        designAssetDao.update(id, updatedDesignAsset);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during updating design and thier assets.", e);
	    }
	}

	/**
	 * Deletes a design asset by ID.
	 *
	 * @param id The ID of the design asset to be deleted.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws ServiceException    If an error occurs during the service operation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void delete(int id) throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateDesignAssetId(id);
	        DesignAssetDAO designAssetDao = new DesignAssetDAO();
	        designAssetDao.delete(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during deleting design and thier assets.", e);
	    }
	}

	/**
	 * Retrieves all active design assets.
	 *
	 * @return A set of DesignAssetRespondDto objects representing all active design assets.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException    If an error occurs during the service operation.
	 */
	public Set<DesignAssetRespondDto> getAllByDesignAsset() throws ValidationException, ServiceException {
	    try {
	        DesignAssetDAO designAssetDao = new DesignAssetDAO();
	        Set<DesignAssetRespondDto> assetList = designAssetDao.findAllDesignAsset();
	        for (DesignAssetRespondDto asset : assetList) {
	            System.out.println(asset);
	        }
	        return assetList;
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieveing all designs and thier assets.", e);
	    }
	}

	/**
	 * Retrieves a specific design asset by ID.
	 *
	 * @param id The ID of the design asset to be retrieved.
	 * @return The DesignAssetRespondDto object representing the design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException    If an error occurs during the service operation.
	 */
	public DesignAssetRespondDto findDesignAssetById(int id) throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateDesignAssetId(id);
	        DesignAssetDAO designAssetDao = new DesignAssetDAO();
	        return designAssetDao.findAllDesignAssetById(id);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving design and thier assets by id.", e);
	    }
	}

}
