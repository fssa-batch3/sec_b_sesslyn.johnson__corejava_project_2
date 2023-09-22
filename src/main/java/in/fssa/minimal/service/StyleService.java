package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.DesignDAO;
import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Design;
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
	public void createStyle(Style newStyle) throws ValidationException, PersistenceException, ServiceException {
	    try {
	        if (newStyle == null) {
	            throw new ValidationException("Style object cannot be null");
	        }
	        DesignValidator.validateStyleName(newStyle.getName()); 
	        StyleDAO styleDAO = new StyleDAO();
	        styleDAO.create(newStyle);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while creating style", e);
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
	public void updateStyle(int styleId, Style updateStyle) throws ValidationException, ServiceException {
	    try {
	        DesignValidator.validateStyleId(styleId);
	        DesignValidator.validateStyleName(updateStyle.getName());
	        StyleDAO styleDAO = new StyleDAO();
	        styleDAO.update(styleId, updateStyle);
	    } catch (PersistenceException e) {
	        throw new ServiceException("Error occurred while updating style", e);
	    }
	}

     public int findStyleId(String styleName) throws ValidationException, ServiceException {
    	 int styleId = 0;
    	 try {
 	        DesignValidator.validateName(styleName);
 	        StyleDAO styleDAO = new StyleDAO();
 	        styleId = styleDAO.getStyleId(styleName);
 	    } catch (PersistenceException e) {
 	        throw new ServiceException("Error occurred while updating style", e);
 	    }
		return styleId; 
     }
     
     public Set<Style> getAllStyle() throws ServiceException {
 	    try {
 	        StyleDAO styleDao = new StyleDAO();
 	        Set<Style> styleList = styleDao.findAllStyle();
 	        return styleList;
 	    } catch (PersistenceException e) {
 	        throw new ServiceException("Error occurred while retrieving designs", e);
 	    }
 	}
     
     public String findStyleName(int styleId) throws ValidationException, ServiceException {
        String styleName = null;
    	 try {
 	        DesignValidator.validateId("Style Id",styleId);
 	        StyleDAO styleDAO = new StyleDAO();
 	        styleName = styleDAO.getStyleName(styleId);
 	    } catch (PersistenceException e) {
 	        throw new ServiceException("Error occurred while updating style", e);
 	    }
		return styleName; 
     }
     
}
