package vn.vmg.ptdv.hola.api.rest.login;

import lombok.Data;

import java.time.Instant;

@Data
public class LoginJSONResponse {
    private String userName;
    private String sessionKey;
    private Integer code;
    private Boolean isFirstLogin;
    private Instant uTimestamp;
}
