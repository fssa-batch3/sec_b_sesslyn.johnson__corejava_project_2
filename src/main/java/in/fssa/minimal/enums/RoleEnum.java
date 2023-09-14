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

    public static String getRoleString(String input) throws ValidationException {
        for (RoleEnum roles : RoleEnum.values()) {
            if (roles.getValue().equals(input)) {
                return roles.name().toLowerCase();
            }
        }
        return "non";
    }
}

