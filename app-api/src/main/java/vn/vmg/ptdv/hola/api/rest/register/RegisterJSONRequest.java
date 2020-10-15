package vn.vmg.ptdv.hola.api.rest.register;

import lombok.Data;

@Data
public class RegisterJSONRequest {
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private String serviceName;
    private String deviceToken;
}
