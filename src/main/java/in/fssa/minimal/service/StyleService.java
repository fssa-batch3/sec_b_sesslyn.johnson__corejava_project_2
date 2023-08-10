package in.fssa.minimal.service;

import in.fssa.minimal.dao.StyleDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Style;
import in.fssa.minimal.validator.StyleExists;
import in.fssa.minimal.validator.UserValidator;

public class StyleService {
	public void create(Style newStyle) throws ValidationException, PersistenceException {
		UserValidator.validateName(newStyle.getName());
		StyleExists.nameExists(newStyle.getName());
		StyleDAO styleDao = new StyleDAO();
		styleDao.create(newStyle);
	}
	
	public void update(int id, Style updateStyle) throws ValidationException, PersistenceException {
		UserValidator.validateName(updateStyle.getName());
		StyleExists.checkIdExists(id);
		StyleDAO styleDao = new StyleDAO();
		styleDao.update(id, updateStyle);
	}
	
	
}
