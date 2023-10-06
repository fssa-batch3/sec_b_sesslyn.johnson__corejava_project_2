package in.fssa.minimal.enums;

import in.fssa.minimal.exception.ValidationException;

public enum StatusEnum {
    waiting_list, approved, rejected,completed;
    
	/**
	 * Get the status string for the provided status value.
	 *
	 * @param input The status value (e.g., "waiting_list", "approved", "rejected").
	 * @return The corresponding status string (e.g., "waiting_list" for "waiting_list").
	 * @throws ValidationException If the input is not a valid status value.
	 */
    public static String getStatus(String input) throws ValidationException {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "waiting_list":
                return "waiting_list";
            case "approved":
                return "approved";
            case "rejected":
                return "rejected";
            case "completed":
                return "completed"; 
            default:
                return "non";
        }
    }
}
