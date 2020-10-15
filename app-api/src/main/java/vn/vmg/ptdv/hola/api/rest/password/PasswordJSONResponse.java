package vn.vmg.ptdv.hola.api.rest.password;

import lombok.Data;

@Data
public class PasswordJSONResponse {
    private String phoneNumber;
    private Integer code;
}
