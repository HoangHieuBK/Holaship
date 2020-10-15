package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;

@Data
public class LoginInfo {
    private String phone;
    private String sessionKey;
    private Long accountEpurseId;
    private String email;
    private String displayName;
}
