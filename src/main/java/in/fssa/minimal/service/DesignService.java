package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.validator.DesignValidator;

public class DesignService {
	/**
	 * Creates a new design entity in the system.
	 *
	 * @param newDesign The design to be created.
	 * @throws ValidationException  If the input design is invalid.
	 * @throws PersistenceException If a database error occurs during creation.
	 * @throws ServiceException    If an error occurs during validation or database operation.
	 */
	public void createDesign(Design newDesign) throws ValidationException, PersistenceException, ServiceException {
	    try {
	        DesignValidator.validateDesign(newDesign);
	        DesignDAO designDAO = new DesignDAO();
	        designDAO.create(newDesign);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating design.", e);
	    }
	}

	/**
	 * Updates an existing design with new information.
	 *
	 * @param id            The ID of the design to be updated.
	 * @param updatedDesign The Design object containing the updated information.
	 * @throws ValidationException  If the provided design ID is invalid, or the
	 *                              updated design object has invalid data.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              design.
	 * @throws ServiceException    If an error occurs during validation or database operation.
	 */
	public void updateDesign(int designId, Design updatedDesign)
	        throws ValidationException, PersistenceException, ServiceException {
	    try {
	        DesignValidator.validateDesignId(designId);

	        if (updatedDesign.getName() != null) {
	            DesignValidator.validateName(updatedDesign.getName());
	        }
	        if (updatedDesign.getDescription() != null) {
	            DesignValidator.validateDescription(updatedDesign.getDescription());
	        }
	        if (updatedDesign.getLocation() != null) {
	            DesignValidator.validateLocation(updatedDesign.getLocation());
	        }
	        if (updatedDesign.getStyleId() != 0) {
	            DesignValidator.validateStyleId(updatedDesign.getStyleId());
	        }
	        if (updatedDesign.getCreatedBy() != 0) {
	            DesignValidator.validateCreatedById(updatedDesign.getCreatedBy());
	        }

	        DesignDAO designDao = new DesignDAO();
	        designDao.update(designId, updatedDesign);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating design.", e);
	    }
	}

	/**
	 * Retrieves all design entities in the system.
	 *
	 * @return A set of all designs.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 * @throws ServiceException    If an error occurs during database operation.
	 */
	public Set<Design> getAllDesign() throws PersistenceException, ServiceException {
	    try {
	        DesignDAO designDao = new DesignDAO();
	        Set<Design> designList = designDao.findAllDesign();
	        for (Design design : designList) {
	            System.out.println(design);
	        }
	        return designList;
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designs.", e);
	    }
	}

	/**
	 * Retrieves a design entity by its ID.
	 *
	 * @param id The ID of the design to retrieve.
	 * @return The retrieved design entity.
	 * @throws ValidationException  If the input ID is invalid.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 * @throws ServiceException    If an error occurs during database operation.
	 */
	public static Design findByDesignId(int designId) throws ValidationException, ServiceException {
	    try {
	        DesignValidator.validateDesignId(designId);
	        DesignDAO designDAO = new DesignDAO();
	        return designDAO.findByDesignId(designId);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving design.", e);
	    }
	}

	/**
	 * Retrieves all design entities created by a specific designer.
	 *
	 * @param id The ID of the designer.
	 * @return A set of designs created by the specified designer.
	 * @throws ValidationException  If the input ID is invalid.
	 * @throws ServiceException    If an error occurs during database operation.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public static Set<Design> findAllDesignsByDesignerId(int designId) throws ValidationException, ServiceException {
	    try {
	        DesignValidator.validateDesignerId(designId);
	        DesignDAO designDAO = new DesignDAO();
	        Set<Design> designList = designDAO.findAllDesignsByDesignerId(designId);
	        for (Design design : designList) {
	            System.out.println(design);
	        }
	        return designList;
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while retrieving designs.", e);
	    }
	}

}