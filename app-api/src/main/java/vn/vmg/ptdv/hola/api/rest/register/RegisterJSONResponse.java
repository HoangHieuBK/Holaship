package vn.vmg.ptdv.hola.api.rest.register;

import lombok.Data;

@Data
public class RegisterJSONResponse {
    private String phoneNumber;
    private String email;
    private String serviceName;
    private Integer code;
}
