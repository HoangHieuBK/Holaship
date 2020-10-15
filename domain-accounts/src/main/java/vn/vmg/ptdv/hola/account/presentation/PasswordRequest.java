package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

@Data
public class PasswordRequest {
    private AccountId accountId;
    private String step;
    private Password password;
    private OTPVerification otp;
}
