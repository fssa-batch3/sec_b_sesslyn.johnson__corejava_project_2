 package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.validator.DesignExists;
import in.fssa.minimal.validator.DesignValidator;
import in.fssa.minimal.validator.StyleExists;
import in.fssa.minimal.validator.UserExists;


public class DesignService {
	/**
	 * 
	 * @param newDesign
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void create(Design newDesign) throws ValidationException, PersistenceException {
		DesignValidator.Validate(newDesign);
		StyleExists.checkIdExists(newDesign.getStyleId());
		UserExists.checkDesignerIdExists(newDesign.getCreatedBy());
		DesignDAO designDao = new DesignDAO();
		designDao.create(newDesign);
	}
	/**
	 * 
	 * @param id
	 * @param updatedDesign
	 * @throws ValidationException
	 * @throws PersistenceException
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
        	StyleExists.checkIdExists(updatedDesign.getStyleId());
        }
        if (updatedDesign.getCreatedBy() != 0) {
        	DesignValidator.validateId(updatedDesign.getCreatedBy());
        	UserExists.checkDesignerIdExists(updatedDesign.getCreatedBy());
        }
        
        DesignExists.checkIdExists(id);
        DesignDAO designDao = new DesignDAO();
		designDao.update(id, updatedDesign);
	}
	/**
	 * 
	 * @return
	 * @throws PersistenceException
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
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public static Design findByDesignId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
	    DesignExists.checkIdExists(id);
		DesignDAO designDao = new DesignDAO();
		return designDao.findByDesignId(id);
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public static Set<Design> findAllDesignsByDesignerId(int id) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		UserExists.checkDesignerIdExists(id);
		DesignExists.checkCreatedByExists(id);
		DesignDAO designDao = new DesignDAO();
		Set<Design>  designList = designDao.findAllDesignsByDesignerId(id);
		for (Design design : designList) {
			System.out.println(design);
		}
		return designList;
	}
}
