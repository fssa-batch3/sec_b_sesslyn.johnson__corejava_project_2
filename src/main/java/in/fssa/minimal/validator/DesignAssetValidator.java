package in.fssa.minimal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.minimal.dao.AssetDAO;
import in.fssa.minimal.dao.DesignAssetDAO;
import in.fssa.minimal.dao.UserDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Asset;
import in.fssa.minimal.model.Design;
import in.fssa.minimal.model.DesignAsset;
import in.fssa.minimal.util.StringUtil;

public class DesignAssetValidator {

	/**
	 * Validates the combination of a Design object and an Asset object. Ensures
	 * that both Design and Asset objects are not null.
	 *
	 * @param newDesign The Design object to be validated.
	 * @param newAsset  The Asset object to be validated.
	 * @throws ValidationException If either the Design or Asset object is null.
	 */
	public static void validateDesignAsset(Design newDesign, Asset newAsset, int designerId)
			throws ValidationException, ServiceException {
		if (newDesign == null || newAsset == null) {
			throw new ValidationException("Design object and Asset Object cannot be null");
		}
		validateDesignerId(designerId);
	}

	/**
	 * Validates the combination of a Design object and an Asset object for updates.
	 * Ensures that at least one of Design or Asset objects is not null.
	 *
	 * @param newDesign The Design object to be validated.
	 * @param newAsset  The Asset object to be validated.
	 * @throws ValidationException If both the Design and Asset objects are null.
	 */
	public static void validateUpdateDesignAsset(Design newDesign, Asset newAsset)
			throws ValidationException, ServiceException {
		if (newDesign == null && newAsset == null) {
			throw new ValidationException("Design object and Asset Object cannot be null");
		}
	}

	/**
	 * Validates an ID value.
	 *
	 * @param name The name of the field (for error message).
	 * @param id   The ID to be validated.
	 * @throws ValidationException If the ID is not valid (less than or equal to
	 *                             zero).
	 */
	public static void validateId(String name, int id) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(name + " cannot be less than or equal to zero");
		}
	}

	/**
	 * Validates a Design Asset ID value.
	 *
	 * @param designAssetId The Design Asset ID value to be validated. 
	 * @throws ValidationException If the Design Asset ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             validation.
	 */
	public static void validateDesignAssetId(int designAssetId) throws ValidationException, ServiceException {
		try {
			validateId("Design Asset Id", designAssetId);
			DesignAssetDAO.checkIdExists(designAssetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during design asset id validation.", e);
		}
	}

	/**
	 * Validates an asset URL string.
	 *
	 * @param assetUrl The asset URL to be validated.
	 * @throws ValidationException If the asset URL is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             validation.
	 */
	public static String validateAssetUrl(String assetUrl) throws ValidationException, ServiceException {
	    String embeddedLink = null;

	    // Check if the URL is already in the embedded format
	    if (isEmbeddedLink(assetUrl)) {
	        embeddedLink = assetUrl;
	    } else {
	        StringUtil.rejectIfInvalidString(assetUrl, "Asset Url Name");
	        embeddedLink = convertToEmbeddedLink(assetUrl);
	        System.out.println(embeddedLink);
	    }

	    return embeddedLink;
	}

	public static boolean isEmbeddedLink(String assetUrl) {
	  
	    String embeddedPattern = "^https://www.youtube.com/embed/";
	    return assetUrl.matches(embeddedPattern);
	}

	public static String convertToEmbeddedLink(String assetUrl) {
	    String videoId = extractVideoId(assetUrl);
	    return "https://www.youtube.com/embed/" + videoId;
	}

	public static String extractVideoId(String youtubeUrl) {
	    String videoId = null;
	    String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed\n\\/|e\n\\/|v=|%2Fv%2F|e\\/)[^#\\&\\?\\n]*";
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(youtubeUrl);
	    if (matcher.find()) {
	        videoId = matcher.group();
	    }
	    return videoId;
	}

	/**
	 * Validates an Asset ID value.
	 *
	 * @param assetId The Asset ID value to be validated.
	 * @throws ValidationException If the Asset ID is invalid.
	 * @throws ServiceException    If a service-related error occurs during
	 *                             validation.
	 */
	public static void validateAssetId(int assetId) throws ValidationException, ServiceException {
		try {
			validateId("Asset Id", assetId);
			AssetDAO.checkIdExists(assetId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during asset id validation.", e);
		}
	}

	public static void validateDesignerId(int designerId) throws ValidationException, ServiceException {
		try {
			validateId("Designer Id", designerId);
			UserDAO.checkDesignerIdExists(designerId);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred during created by id validation.", e);
		}
	}

}
