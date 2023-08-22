 package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.validator.DesignValidator;


public class DesignService {
	 /**
     * Creates a new design entity in the system.
     *
     * @param newDesign The design to be created.
     * @throws ValidationException If the input design is invalid.
     * @throws PersistenceException If a database error occurs during creation.
     */
	public void create(Design newDesign) throws ValidationException, PersistenceException {
		DesignValidator.validate(newDesign);
		StyleDAO.checkIdExists(newDesign.getStyleId());
		UserDAO.checkDesignerIdExists(newDesign.getCreatedBy());
		DesignDAO designDao = new DesignDAO();
		designDao.create(newDesign);
	}
	/**
     * Updates an existing design entity in the system.
     *
     * @param id            The ID of the design to be updated.
     * @param updatedDesign The updated design data.
     * @throws ValidationException If the input data is invalid.
     * @throws PersistenceException If a database error occurs during update.
     */
	public void update(int id, Design updatedDesign) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
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
        	DesignValidator.validateId(updatedDesign.getStyleId());
        	StyleDAO.checkIdExists(updatedDesign.getStyleId());
        }
        if (updatedDesign.getCreatedBy() != 0) {
        	DesignValidator.validateId(updatedDesign.getCreatedBy());
        	UserDAO.checkDesignerIdExists(updatedDesign.getCreatedBy());
        }
        
        DesignDAO.checkIdExists(id);
        DesignDAO designDao = new DesignDAO();
		designDao.update(id, updatedDesign);
	}
	 /**
     * Retrieves all design entities in the system.
     *
     * @return A set of all designs.
     * @throws PersistenceException If a database error occurs during retrieval.
     */
	public Set<Design> getAllDesign() throws PersistenceException {
		DesignDAO designDao = new DesignDAO();
		Set<Design>  designList = designDao.findAllDesign();
		for (Design design : designList) {
			System.out.println(design);
		}
		return designList;
	}
	 /**
     * Retrieves a design entity by its ID.
     *
     * @param id The ID of the design to retrieve.
     * @return The retrieved design entity.
     * @throws ValidationException If the input ID is invalid.
     * @throws PersistenceException If a database error occurs during retrieval.
     */
	public static Design findByDesignId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
	    DesignDAO.checkIdExists(id);
		DesignDAO designDao = new DesignDAO();
		return designDao.findByDesignId(id);
	}

    /**
     * Retrieves all design entities created by a specific designer.
     *
     * @param id The ID of the designer.
     * @return A set of designs created by the specified designer.
     * @throws ValidationException If the input ID is invalid.
     * @throws PersistenceException If a database error occurs during retrieval.
     */
	public static Set<Design> findAllDesignsByDesignerId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		UserDAO.checkDesignerIdExists(id);
		DesignDAO.checkCreatedByExists(id);
		DesignDAO designDao = new DesignDAO();
		Set<Design>  designList = designDao.findAllDesignsByDesignerId(id);
		for (Design design : designList) {
			System.out.println(design);
		}
		return designList;
	}
}
