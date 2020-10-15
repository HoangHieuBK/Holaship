package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class ProfileRequest {
    private AccountId accountId;
    private UserService userService;
    private DeviceToken deviceToken;
    private AuditLog auditLog;
    private Avatar avatar;
    private LocalDate birthday;
    private String displayName;
    private Gender gender;

    public ProfileRequest(String phone, String deviceToken) {
        this.accountId = new AccountId(phone);
        this.deviceToken = new DeviceToken().withUniqueToken(deviceToken);
    }

    public ProfileRequest() {

    }

    public ProfileRequest withIdentity(String phone, Instant uTimestamp) {
        this.accountId = new AccountId(phone);
        this.auditLog = new AuditLog(Timestamp.from(uTimestamp));
        return this;
    }

    public ProfileRequest withBasicInfo(String avatar, LocalDate birthday, String displayName, String gender) {
        this.avatar = new Avatar().withPath(avatar);
        this.birthday = birthday;
        this.displayName = displayName;
        this.gender = new Gender().fromText(gender);

        return this;
    }

    public ProfileRequest build() {
        return this;
    }
}
