package in.fssa.minimal.service;

import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.util.StringUtil;
import in.fssa.minimal.validator.DesignValidator;

public class StyleService {
	 /**
     * Creates a new style with the provided information.
     *
     * @param newStyle The Style object containing the information of the new style.
     * @throws ValidationException If the provided style object is null or has invalid data.
     * @throws PersistenceException If a database error occurs while creating the style.
     */
	public void create(Style newStyle) throws ValidationException, PersistenceException {
		if(newStyle == null) {
			throw new ValidationException("Style object can not be null");
		}
		StringUtil.rejectIfInvalidString(newStyle.getName(), "Style Name");
		StyleDAO.nameExists(newStyle.getName());
		StyleDAO styleDao = new StyleDAO();
		styleDao.create(newStyle);
	}
	/**
     * Updates an existing style with new information.
     *
     * @param id          The ID of the style to be updated.
     * @param updateStyle The Style object containing the updated information.
     * @throws ValidationException If the provided style ID is invalid, or the updated style object has invalid data.
     * @throws PersistenceException If a database error occurs while updating the style.
     */
	public void update(int id, Style updateStyle) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		StyleDAO.checkIdExists(id);
		DesignValidator.validateName(updateStyle.getName());
		StyleDAO.nameExists(updateStyle.getName());
		StyleDAO styleDao = new StyleDAO();
		styleDao.update(id, updateStyle);
	}
	
	
}
