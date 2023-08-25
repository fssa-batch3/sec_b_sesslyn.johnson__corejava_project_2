package in.fssa.minimal.service;

import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.validator.DesignValidator;

public class StyleService {
	/**
	 * Creates a new style with the provided information.
	 *
	 * @param newStyle The Style object containing the information of the new style.
	 * @throws ValidationException  If the provided style object is null or has
	 *                              invalid data.
	 * @throws PersistenceException If a database error occurs while creating the
	 *                              style.
	 * @throws ServiceException    If a service-related error occurs while creating the
	 *                              style.
	 */
	public void create(Style newStyle) throws ValidationException, PersistenceException, ServiceException {
	    try {
	        if (newStyle == null) {
	            throw new ValidationException("Style object cannot be null");
	        }
	        DesignValidator.validateStyleName(newStyle.getName());
	        StyleDAO styleDao = new StyleDAO();
	        styleDao.create(newStyle);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating style.", e);
	    }
	}

	/**
	 * Updates an existing style with new information.
	 *
	 * @param id          The ID of the style to be updated.
	 * @param updateStyle The Style object containing the updated information.
	 * @throws ValidationException  If the provided style ID is invalid, or the
	 *                              updated style object has invalid data.
	 * @throws PersistenceException If a database error occurs while updating the
	 *                              style.
	 * @throws ServiceException    If a service-related error occurs while updating the
	 *                              style.
	 */
	public void update(int id, Style updateStyle) throws ValidationException, ServiceException {
	    try {
	        DesignValidator.validateStyleId(id);
	        DesignValidator.validateStyleName(updateStyle.getName());
	        StyleDAO styleDao = new StyleDAO();
	        styleDao.update(id, updateStyle);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating style.", e);
	    }
	}


}
