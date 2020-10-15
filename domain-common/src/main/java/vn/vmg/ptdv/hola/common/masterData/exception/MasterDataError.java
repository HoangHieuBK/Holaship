package vn.vmg.ptdv.hola.common.masterData.exception;

import lombok.Data;

@Data
public class MasterDataError {
    private final String code;
    private final String field;
    private final String description;

    public MasterDataError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
