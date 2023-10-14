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

    /**
     * Get the gender value for the provided input (case-insensitive).
     *
     * @param input The input string (e.g., "male", "female", "other").
     * @return The gender value (e.g., "m" for "male").
     * @throws ValidationException If the input is not a valid gender value.
     */
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

    /**
     * Get the string representation of a gender value based on its value.
     *
     * @param input The gender value (e.g., "m", "f", "o").
     * @return The string representation (e.g., "male", "female", "other").
     * @throws ValidationException If the input is not a valid gender value.
     */
    public static String getGenderString(String input) throws ValidationException {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.getValue().equals(input)) {
                return gender.name().toLowerCase();
            }
        }
        return "non";
    }

}
