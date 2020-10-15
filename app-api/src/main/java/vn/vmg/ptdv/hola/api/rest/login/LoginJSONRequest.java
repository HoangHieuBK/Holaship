package vn.vmg.ptdv.hola.api.rest.login;

import lombok.Data;

@Data
public class LoginJSONRequest {
    private String phoneNumber;
    private String password;
    private String serviceName;
    private String deviceToken;
}
