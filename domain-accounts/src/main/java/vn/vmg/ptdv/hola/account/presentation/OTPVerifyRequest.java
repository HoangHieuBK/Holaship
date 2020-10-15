package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.HolaProvider;

import java.sql.Timestamp;
import java.time.Instant;


@Data
public class OTPVerifyRequest {
    private AccountId accountId;
    private String otp;
    private AuditLog auditLog;
    private DeviceToken deviceToken;
    private HolaProvider serviceName;
    public OTPVerifyRequest() {
    }

    public OTPVerifyRequest(String phone, String otpCode, Instant utimestamp,String deviceToken,String serviceName) {
        this.accountId = new AccountId(phone);
        this.otp = otpCode;
        this.auditLog = new AuditLog(Timestamp.from(utimestamp));
        this.deviceToken = new DeviceToken(deviceToken);
        this.serviceName = HolaProvider.valueOf(serviceName);
    }

}
