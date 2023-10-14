package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Review;
import in.fssa.minimal.service.ReviewService;

public class TestCreateReview {
	@Test 
	@Order(1) 
	 void testCreateReviewWithValidInput() {
	    ReviewService reviewService = new ReviewService(); 
	    Review newReview = new Review();
	  newReview.setAppointment_id(2);
	  newReview.setFrom_user(1);
	  newReview.setTo_user(2);
	   newReview.setRatings(4);
	   newReview.setFeedback("Wonderful plan and exceuted perfectly");

	    assertDoesNotThrow(() -> {
	        reviewService.createReview(newReview); 
	    }); 
	}

	@Test
	@Order(2)
	 void testCreateReviewWithInvalidInput() {
		 ReviewService reviewService = new ReviewService(); 
		Exception exception = assertThrows(ValidationException.class, () -> {
			 reviewService.createReview(null); 
		});
		String expectedMessage = "Review Object cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	@Order(3)
	 void testCreateReviewWithIdZero() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(-2);
		  newReview.setFrom_user(1);
		  newReview.setTo_user(2);
		   newReview.setRatings(4);
		   newReview.setFeedback("Wonderful plan and exceuted perfectly");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "Appointment Id cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(4)
	 void testCreateReviewWithAppointmentIdNotExists() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(100);
		  newReview.setFrom_user(1);
		  newReview.setTo_user(2);
		   newReview.setRatings(4);
		   newReview.setFeedback("Wonderful plan and exceuted perfectly");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "Appointment Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(5)
	 void testCreateReviewWithUserIdNotExists() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(2);
		  newReview.setFrom_user(1000);
		  newReview.setTo_user(2);
		   newReview.setRatings(4);
		   newReview.setFeedback("Wonderful plan and exceuted perfectly");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "User Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(6)
	 void testCreateReviewWithDesignerIdNotExists() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(2);
		  newReview.setFrom_user(1);
		  newReview.setTo_user(1);
		   newReview.setRatings(4);
		   newReview.setFeedback("Wonderful plan and exceuted perfectly");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(7)
	 void testCreateReviewWithRatingsZero() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(2);
		  newReview.setFrom_user(1);
		  newReview.setTo_user(2);
		   newReview.setRatings(0);
		   newReview.setFeedback("Wonderful plan and exceuted perfectly");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "Ratings cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	@Order(7)
	 void testCreateReviewWithFeedBackEmpty() {
		 ReviewService reviewService = new ReviewService(); 
		 Review newReview = new Review();
		  newReview.setAppointment_id(2);
		  newReview.setFrom_user(1);
		  newReview.setTo_user(2);
		   newReview.setRatings(2);
		   newReview.setFeedback("");

		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(newReview); 
		});
		String expectedMessage = "FeedBack cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
}
