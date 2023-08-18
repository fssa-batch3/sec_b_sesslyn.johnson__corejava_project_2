package in.fssa.minimal.service;

import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.validator.DesignValidator;
import in.fssa.minimal.validator.StyleExists;
import in.fssa.minimal.validator.UserValidator;

public class StyleService {
	/**
	 * 
	 * @param newStyle
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void create(Style newStyle) throws ValidationException, PersistenceException {
		if(newStyle == null) {
			throw new ValidationException("Style object can not be null");
		}
		DesignValidator.validateName(newStyle.getName());
		StyleExists.nameExists(newStyle.getName());
		StyleDAO styleDao = new StyleDAO();
		styleDao.create(newStyle);
	}
	/**
	 * 
	 * @param id
	 * @param updateStyle
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void update(int id, Style updateStyle) throws ValidationException, PersistenceException {
		DesignValidator.validateId(id);
		StyleExists.checkIdExists(id);
		DesignValidator.validateName(updateStyle.getName());
		StyleExists.nameExists(updateStyle.getName());
		StyleDAO styleDao = new StyleDAO();
		styleDao.update(id, updateStyle);
	}
	
	
}
