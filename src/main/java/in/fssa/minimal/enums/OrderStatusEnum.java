package in.fssa.minimal.enums;

import in.fssa.minimal.exception.ValidationException;

public enum OrderStatusEnum {
	Waiting_list, Delivered, Rejected, Cancelled;

	public static String getStatus(String input) throws ValidationException {
		switch (input) {
		case "Waiting_list":
			return "Waiting_list";
		case "Delivered":
			return "Delivered";
		case "Rejected":
			return "Rejected";  
		case "Cancelled":
			return "Cancelled";
		default:
			return "non";
		}
	}
}
