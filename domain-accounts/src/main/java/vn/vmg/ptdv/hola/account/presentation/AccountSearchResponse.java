package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;

import java.time.Instant;

@Data
public class AccountSearchResponse {
    private String id;
    private String serviceName;
    private String phone;
    private String displayName;
    private String email;
    private String gender;
    private Integer status;
    private Instant createAt;
}
