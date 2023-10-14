package in.fssa.minimal.service;

import java.util.Set;

import in.fssa.minimal.dao.ReviewDAO;
import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.exception.ServiceException;
import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Review;
import in.fssa.minimal.validator.ReviewValidator;

public class ReviewService {
	
	/**
	 * Creates a new review in the system.
	 *
	 * @param newReview The Review object containing the details of the new review to create.
	 * @throws ServiceException If a service-related error occurs during the operation.
	 * @throws ValidationException If the validation of the review fails.
	 */
	public void createReview(Review newReview) throws ServiceException, ValidationException {
		try {
			ReviewValidator.validate(newReview);
			ReviewDAO reviewDAO = new ReviewDAO();
			reviewDAO.create(newReview);
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while creating Review.", e);
		}
	}
	
	/**
	 * Retrieves a set of Review objects representing all reviews for a designer specified by their ID.
	 *
	 * @param designerId The ID of the designer for whom reviews are to be retrieved.
	 * @return A set of Review objects representing reviews for the specified designer.
	 * @throws ValidationException If the designer ID validation fails.
	 * @throws ServiceException If a service-related error occurs during the operation.
	 */
	public Set<Review> getAllReviewByDesignerId(int designerId) throws ValidationException, ServiceException {
		try {
			ReviewValidator.validateDesignerId(designerId);
			ReviewDAO reviewDAO = new ReviewDAO();
			Set<Review> reviewList = reviewDAO.getAllReviewByToUserId(designerId);
			return reviewList;
		} catch (PersistenceException e) {
			throw new ServiceException("Error occurred while retrieveing all designs and thier assets", e);
		}
	}
} 
 