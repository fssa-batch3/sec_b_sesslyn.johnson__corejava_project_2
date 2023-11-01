package in.fssa.minimal.service;

import java.util.List;
import java.util.Set; 

import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dto.DesignAssetRespondDTO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.validator.DesignAssetValidator;
 
public class DesignAssetService {
	/** 
	 * Creates a new design asset.
	 *
	 * @param newDesign The Design object to be associated with the new design asset.
	 * @param newAsset  The Asset object to be associated with the new design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException     If an error occurs during the service operation.
	 */
	public void createDesignAsset(Design newDesign, Asset newAsset,int designerId)
	        throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateDesignAsset(newDesign, newAsset,designerId);
	        DesignService designService = new DesignService();
	        AssetService assetService = new AssetService();
	        DesignAssetDAO designAssetDAO = new DesignAssetDAO();
  
	        int designId = designService.createDesign(newDesign);
	        int assetId = assetService.createAsset(newAsset);
	        DesignAsset newDesignAsset = new DesignAsset();
	        newDesignAsset.setDesignId(designId);
	        newDesignAsset.setAssetsId(assetId);
	        newDesignAsset.setDesignerId(designerId);

	        
	        designAssetDAO.create(newDesignAsset);
	    }catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during updating design and their assets", e);
	    }
	}
	
	
 
	/**
	 * Updates an existing design asset.
	 *
	 * @param designAssetId      The ID of the design asset to be updated.
	 * @param updateDesign       The updated Design object.
	 * @param updateAsset        The updated Asset object.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws ServiceException     If an error occurs during the service operation.
	 */
	public void updateDesignAsset(int designAssetId, Design updateDesign, Asset updateAsset)
	        throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateUpdateDesignAsset(updateDesign, updateAsset);
	        DesignAssetValidator.validateDesignAssetId(designAssetId);
	        DesignAssetService designAssetService = new DesignAssetService();
	        DesignAsset designAsset = designAssetService.findDesignIdByDesignAssetId(designAssetId);
	        int designId = designAsset.getDesignId();
	        System.out.println("DesignId" +designId);
	        int assetId = designAsset.getAssetsId();
	        System.out.println("AssetId" +designId);
	        if (updateDesign != null) {
	            DesignService designService = new DesignService();
	            designService.updateDesign(designId, updateDesign);
	        }

	        if (updateAsset != null) {
	            AssetService assetService = new AssetService();
	            assetService.updateAsset(assetId, updateAsset);
	        }

	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred during updating design and their assets", e);
	    }
	}

	/**
	 * Deletes a design asset by ID.
	 *
	 * @param id The ID of the design asset to be deleted.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws ServiceException     If an error occurs during the service operation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 */
	public void deleteDesignAsset(int designAssetId) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateDesignAssetId(designAssetId);
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			designAssetDAO.delete(designAssetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during deleting design and thier assets", e);
		}
	}
	
	public void activateDesignAsset(int designAssetId) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateDesignAssetId(designAssetId);
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			designAssetDAO.activate(designAssetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during deleting design and thier assets", e);
		}
	}
	
	/**
	 * Retrieves all active design assets.
	 *
	 * @return A set of DesignAssetRespondDto objects representing all active design
	 *         assets.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException     If an error occurs during the service operation.
	 */
	public List<DesignAssetRespondDTO> getAllDesignAsset() throws ValidationException, ServiceException {
		try {
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			List<DesignAssetRespondDTO> assetList = designAssetDAO.findAllDesignAsset();
			return assetList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieveing all designs and thier assets", e);
		}
	}
	
	/**
	 * Retrieves a set of DesignAssetRespondDTO objects representing all design assets
	 * associated with a designer specified by their ID.
	 *
	 * @param designerId The ID of the designer for whom design assets are to be retrieved.
	 * @return A set of DesignAssetRespondDTO objects representing design assets.
	 * @throws ValidationException If the designer ID validation fails.
	 * @throws ServiceException If a service-related error occurs during the operation.
	 */
	public List<DesignAssetRespondDTO> getAllDesignAssetByDesignerId(int designerId) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateDesignerId(designerId);
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			List<DesignAssetRespondDTO> assetList = designAssetDAO.findAllDesignAssetByDesignerId(designerId);
			return assetList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieveing all designs and thier assets", e);
		}
	}

	/**
	 * Retrieves a set of DesignAssetRespondDTO objects representing all active design assets
	 * associated with a designer specified by their ID.
	 *
	 * @param designerId The ID of the designer for whom active design assets are to be retrieved.
	 * @return A set of DesignAssetRespondDTO objects representing active design assets.
	 * @throws ValidationException If the designer ID validation fails.
	 * @throws ServiceException If a service-related error occurs during the operation.
	 */
	public List<DesignAssetRespondDTO> getAllActiveDesignAssetByDesignerId(int designerId) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateDesignerId(designerId);
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			List<DesignAssetRespondDTO> assetList = designAssetDAO.findAllActiveDesignAssetByDesignerId(designerId);
			return assetList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieveing all designs and thier assets", e);
		}
	}

	/**
	 * Retrieves a specific design asset by ID.
	 *
	 * @param id The ID of the design asset to be retrieved.
	 * @return The DesignAssetRespondDto object representing the design asset.
	 * @throws ValidationException  If there's an issue with data validation.
	 * @throws PersistenceException If there's an issue with database interaction.
	 * @throws ServiceException     If an error occurs during the service operation.
	 */
	public DesignAssetRespondDTO findDesignAssetById(int designAssetId) throws ValidationException, ServiceException {
		try {
			DesignAssetValidator.validateDesignAssetId(designAssetId);
			DesignAssetDAO designAssetDAO = new DesignAssetDAO();
			return designAssetDAO.findAllDesignAssetById(designAssetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieving design and thier assets by id", e);
		}
	}

	/**
	 * Retrieves the associated Design ID from a given Design Asset ID.
	 *
	 * @param designAssetId The Design Asset ID for which to retrieve the associated Design ID.
	 * @return The associated DesignAsset object containing the Design ID.
	 * @throws ValidationException If the provided Design Asset ID is invalid.
	 * @throws ServiceException    If an error occurs while retrieving the Design ID by the given Design Asset ID.
	 */
	public DesignAsset findDesignIdByDesignAssetId(int designAssetId) throws ValidationException, ServiceException {
	    try {
	        DesignAssetValidator.validateDesignAssetId(designAssetId);
	        DesignAssetDAO designAssetDAO = new DesignAssetDAO();
	        return designAssetDAO.findDesignAssetById(designAssetId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving design id and assets id by designAsset id", e);
	    }
	}


}