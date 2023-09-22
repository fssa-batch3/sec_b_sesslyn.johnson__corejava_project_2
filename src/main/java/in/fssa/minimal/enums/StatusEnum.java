package in.fssa.minimal.enums;

import in.fssa.minimal.exception.ValidationException;

public enum StatusEnum {
    waiting_list, approved, rejected,completed;
    
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
