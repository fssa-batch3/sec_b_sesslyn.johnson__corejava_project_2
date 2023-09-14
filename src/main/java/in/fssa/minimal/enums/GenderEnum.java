package in.fssa.minimal.enums;

import in.fssa.minimal.exception.ValidationException;

public enum GenderEnum {
	MALE("m"), FEMALE("f"), OTHER("o");

	private final String value;

	GenderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getGender(String input) throws ValidationException {
	    String lowerCaseInput = input.toLowerCase();
	    switch (lowerCaseInput) {
	        case "male":
	            return MALE.value;
	        case "female":
	            return FEMALE.value;
	        case "other":
	            return OTHER.value;
	        default:
	            return "non";
	    }
	}

	public static String getGenderString(String input) throws ValidationException {
		for (GenderEnum gender : GenderEnum.values()) {
		    if (gender.getValue().equals(input)) {
		        return gender.name().toLowerCase();
		    }
		}
		return "non";
	}
}
