package vn.vmg.ptdv.hola.leadtime.validator;

public enum LeadtimeValidationError {

    EMPTY_FIELD("EMPTY_FIELD", "Please enter this field"),
    INVALID_MAX_LENGTH_NAME("INVALID_MAX_LENGTH_NAME", "Max length is 50"),
    INVALID_NUMBER("INVALID_NUMBER", "Invalid number format"),
    INVALID_MAX_LENGTH_5("INVALID_MAX_LENGTH_5", "Max length is 5"),
    INVALID_MAX_LENGTH_15("INVALID_MAX_LENGTH_15", "Max length is 15"),
    INVALID_MAX_LENGTH_50("INVALID_MAX_LENGTH_50", "Max length is 50"),
    INVALID_MAX_LENGTH_200("INVALID_MAX_LENGTH_200", "Max length is 200"),
    INVALID_DATETIME("INVALID_DATETIME", "Invalid date time format"),
    INVALID_INSTANT("INVALID_INSTANT", "Invalid instant format");
    public final String code;
    public final String message;

    LeadtimeValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
