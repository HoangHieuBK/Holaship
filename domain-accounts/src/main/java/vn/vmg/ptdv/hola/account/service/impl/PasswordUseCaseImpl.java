package vn.vmg.ptdv.hola.account.service.impl;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.factory.HolaAccount;
import vn.vmg.ptdv.hola.account.presentation.PasswordResponse;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.service.usecase.PasswordUseCase;
import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;

public class PasswordUseCaseImpl implements PasswordUseCase {
    private final AccountRepository accountRepository;
    private final OTPVerifyRepository otpVerifyRepository;
    private ForgotPasswordStep step;
    private HolaAccount holaAccount;

    public PasswordUseCaseImpl(AccountRepository accountRepository,
            OTPVerifyRepository otpVerifyRepository) {
        this.accountRepository = accountRepository;
        this.otpVerifyRepository = otpVerifyRepository;
    }

    @Override
    public PasswordUseCase accept(String step) {
        this.step = ForgotPasswordStep.valueOf(step);
        return this;
    }

    @Override
    public PasswordUseCase findAccountById(AccountId accountId, DeviceToken deviceToken) {
        if (step == ForgotPasswordStep.FIND_ACCOUNT || step == ForgotPasswordStep.RESET_PASSWORD) {
            holaAccount = accountRepository.findByAccountId(accountId, null);
        }
        return this;
    }

    @Override
    public PasswordUseCase accountNotFound(AccountNotFoundException e) throws AccountNotFoundException {
        if (step == ForgotPasswordStep.FIND_ACCOUNT && holaAccount == null) {
            throw new AccountNotFoundException(400, "Tài khoản không tồn tại!");
        }
        return this;
    }

    @Override
    public PasswordUseCase generatorOTP(AccountId accountId) throws OTPVerificationException {
        if (step == ForgotPasswordStep.GENERATE_OTP) {
            OTPVerification otp = new OTPVerification();
            otp.setPhone(accountId.getPhoneNumber());
            otpVerifyRepository.generatorOTP(otp);
        }
        return this;
    }

    @Override
    public PasswordUseCase resetPassword(AccountId accountId, Password password, OTPVerification otp) throws
            AccountDomainException {
        if (step == ForgotPasswordStep.RESET_PASSWORD) {
            accountRepository.resetPasswordIMedia(accountId.getPhoneNumber(), password.getClearText(), otp.getOtp());
        }
        return this;
    }


    @Override
    public PasswordResponse end() {
        PasswordResponse passwordResponse = new PasswordResponse();
        passwordResponse.setStatus(true);
        if (step == ForgotPasswordStep.FIND_ACCOUNT || step == ForgotPasswordStep.RESET_PASSWORD) {
            passwordResponse.setPhoneNumber(holaAccount.getAccountId().getPhoneNumber());
        }
        return passwordResponse;
    }

    enum ForgotPasswordStep {
        FIND_ACCOUNT,
        GENERATE_OTP,
        RESET_PASSWORD
    }
}
