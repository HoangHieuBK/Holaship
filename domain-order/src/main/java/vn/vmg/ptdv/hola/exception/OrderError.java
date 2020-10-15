package vn.vmg.ptdv.hola.exception;

import lombok.Data;

@Data
public class OrderError {
    private final String code;
    private final String field;
    private final String description;

    public OrderError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
