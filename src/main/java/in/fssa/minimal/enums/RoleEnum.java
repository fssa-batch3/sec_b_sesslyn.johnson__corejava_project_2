package in.fssa.minimal.enums;

import in.fssa.minimal.exception.ValidationException;

public enum RoleEnum {
    USER("u"), DESIGNER("d"), SELLER("s"), ADMIN("a");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Get the role value for the provided input (case-insensitive).
     *
     * @param input The input string (e.g., "user", "designer", "seller", "admin").
     * @return The role value (e.g., "u" for "user").
     * @throws ValidationException If the input is not a valid role value.
     */
    public static String getRole(String input) throws ValidationException {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "user":
                return USER.value;
            case "designer": 
                return DESIGNER.value;
            case "seller":
                return SELLER.value;
            case "admin":
                return ADMIN.value;
            default:
                return "non";
        }
    }

    /**
     * Get the role string for the provided role value.
     *
     * @param input The role value (e.g., "u" for "user", "d" for "designer").
     * @return The role string (e.g., "user" for "u").
     * @throws ValidationException If the input is not a valid role value.
     */
    public static String getRoleString(String input) throws ValidationException {
        for (RoleEnum roles : RoleEnum.values()) {
            if (roles.getValue().equals(input)) {
                return roles.name().toLowerCase();
            }
        }
        return "non";
    }
}

