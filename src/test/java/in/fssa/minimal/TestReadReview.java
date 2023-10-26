package in.fssa.minimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.minimal.exception.ValidationException;
import in.fssa.minimal.model.Review;
import in.fssa.minimal.service.ReviewService;

public class TestReadReview {
	@Test
    @Order(1)
    void testGetAllReviewByDesignerId() {
        assertDoesNotThrow(() -> {
            ReviewService reviewService = new  ReviewService();
            Set<Review> arr = reviewService.getAllReviewByDesignerId(2);
            System.out.println(arr);
            });
    } 
	
	@Test
	@Order(2)
	void testWithDesignerIdNotExists() {
		 ReviewService reviewService = new  ReviewService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			Set<Review> arr = reviewService.getAllReviewByDesignerId(1);
		});
		String expectedMessage = "Designer Id doesn't exist";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
}
