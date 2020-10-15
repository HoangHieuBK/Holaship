package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import org.springframework.boot.web.servlet.server.Session;
import vn.vmg.ptdv.hola.account.core.*;

import java.time.LocalDate;

@Data
public class HolaProfile {
    private AccountId accountId;
    private Email email;
    private HolaService holaService;
    private Session sessionKey;
    private Long accountEpurseId;
    private DeviceToken deviceToken;
    private AuditLog auditLog;
    private String displayName;
    private Gender gender;
    private Avatar avatar;
    private LocalDate birthDay;
}
