package vn.vmg.ptdv.hola.account.service.usecase;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.presentation.PasswordResponse;
import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

public interface PasswordUseCase {
    PasswordUseCase accept(String step);

    PasswordUseCase findAccountById(AccountId accountId, DeviceToken deviceToken);

    PasswordUseCase accountNotFound(AccountNotFoundException e) throws AccountNotFoundException;

    PasswordUseCase generatorOTP(AccountId accountId) throws OTPVerificationException;

    PasswordUseCase resetPassword(AccountId accountId, Password password, OTPVerification otp) throws
            AccountDomainException;

    PasswordResponse end();
}
