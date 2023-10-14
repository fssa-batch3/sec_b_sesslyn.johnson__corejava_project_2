package in.fssa.minimal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.minimal.exception.PersistenceException;
import in.fssa.minimal.model.Review;
import in.fssa.minimal.util.ConnectionUtil;
import in.fssa.minimal.util.Logger;

public class ReviewDAO {
	
	/**
	 * Creates a new review entity in the database.
	 *
	 * @param newReview The review to be created.
	 * @throws PersistenceException If a database error occurs during creation.
	 */
	public void create(Review newReview) throws PersistenceException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO appointment_reviews ( feedback, ratings, appointment_id, from_user, to_user) VALUES (?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, newReview.getFeedback());
			ps.setInt(2, newReview.getRatings());
			ps.setInt(3, newReview.getAppointment_id()); 
			ps.setInt(4, newReview.getFrom_user());
			ps.setInt(5, newReview.getTo_user());
			ps.executeUpdate();
			Logger.info("Review has been Added successfully"); 
		}  catch (SQLException e) {
			Logger.error(e);
			throw new PersistenceException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * Retrieves all reviews by the user with the given 'toUserId'.
	 *
	 * @param toUserId The ID of the user to retrieve reviews for.
	 * @return A set of Review objects representing reviews for the user.
	 * @throws PersistenceException If a database error occurs during retrieval.
	 */
	public Set<Review> getAllReviewByToUserId(int toUserId) throws PersistenceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<Review> reviewList = new HashSet<>(); 
	    try {
	        String query = "SELECT ratings FROM appointment_reviews WHERE to_user = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, toUserId);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Review review = new Review();
	            review.setRatings(rs.getInt("ratings")); 
	            reviewList.add(review);
	        }
	    } catch (SQLException e) {
	        Logger.error(e);
	        throw new PersistenceException(e);
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return reviewList;
	}
	
}
