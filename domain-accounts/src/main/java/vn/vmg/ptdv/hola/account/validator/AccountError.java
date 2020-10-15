package vn.vmg.ptdv.hola.account.validator;

import lombok.Data;

@Data
public class AccountError {
    private final String code;
    private final String field;
    private final String description;

    public AccountError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
