package vn.vmg.ptdv.hola.account.validator;

public enum AccountValidationError {
    EMPTY_FIELD("EMPTY_FIELD", "Please enter this field"),
    INVALID_EMAIL("INVALID_EMAIL", "Invalid email format"),
    INVALID_PHONE("INVALID_PHONE", "Invalid phone format"),
    INVALID_OTP("INVALID_OTP", "Invalid otp format"),
    INVALID_PASSWORD("INVALID_PASSWORD", "Password does not match ConfirmPassword"),
    LOGIN_FAIL("LOGIN_FAIL", "Sai thông tin tài khoản hoặc mật khẩu"),
    INVALID_ROLE_TYPE("INVALID_ROLE_TYPE", "Invalid role type format"),

    PASSWORD_MUST_HAVE_CAPITAL_PASSWORD("CAPITAL_PASSWORD", "Password must have capital letters"),
    PASSWORD_MUST_HAVE_NORMAL_PASSWORD("NORMAL_PASSWORD", "Password must have normal letters"),
    PASSWORD_MUST_HAVE_NUMBER_PASSWORD("NUMBER_PASSWORD", "Password must have number letters"),
    PASSWORD_MUST_HAVE_SPECIAL_LETTER("NUMBER_PASSWORD", "Password must have special letters"),
    INVALID_MAX_LENGTH_PASSWORD("INVALID_MAX_LENGTH_PASSWORD", "Max length is 15"),
    INVALID_MIN_LENGTH_PASSWORD("INVALID_MIN_LENGTH_PASSWORD", "Min length is 8"),
    INVALID_CONFIRM_PASSWORD("INVALID_CONFIRM_PASSWORD", "New password and Confirm password are not the same!"),
    INVALID_MAX_LENGTH_EMAIL("INVALID_MAX_LENGTH_EMAIL", "Max length is 100");

    public final String code;
    public final String message;

    AccountValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
