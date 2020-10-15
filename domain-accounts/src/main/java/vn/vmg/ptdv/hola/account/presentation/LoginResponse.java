package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.SessionKey;

@Data
public class LoginResponse {
    private AccountId username;
    private SessionKey sessionKey;
    private Boolean isFirstLogin;
    private AuditLog auditLog;
}
