package vn.vmg.ptdv.hola.common.exception;

import lombok.Data;

@Data
public class OTPError {
    private final String code;
    private final String field;
    private final String description;

    public OTPError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
