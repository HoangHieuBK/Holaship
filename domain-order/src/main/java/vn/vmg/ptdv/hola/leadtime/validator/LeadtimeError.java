package vn.vmg.ptdv.hola.leadtime.validator;

import lombok.Data;

@Data
public class LeadtimeError {

    private final String code;
    private final String field;
    private final String description;

    public LeadtimeError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }

}
