package vn.vmg.ptdv.hola.account.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.exception.UnauthorizedException;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.*;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.account.service.impl.*;
import vn.vmg.ptdv.hola.account.service.usecase.*;
import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;

public class AccountService {
    private final AccountRepository accountRepository;
    private final OTPVerifyRepository otpVerifyRepository;
    private final VARepository vaRepository;
    private final CashRepository cashRepository;

    public AccountService(AccountRepository accountRepository, OTPVerifyRepository otpVerifyRepository,
            VARepository vaRepository, CashRepository cashRepository) {
        this.accountRepository = accountRepository;
        this.otpVerifyRepository = otpVerifyRepository;
        this.vaRepository = vaRepository;
        this.cashRepository = cashRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LoginResponse login(LoginRequest loginRequest) throws AccountDomainException {
        LoginUseCase loginUseCase = new LoginUseCaseImpl(accountRepository);
        LoginResponse response = loginUseCase
                .loginIMedia(loginRequest.getUsername(), loginRequest.getPassword())
                .fail(new UnauthorizedException())
                .success()
                .getHolaAccount(loginRequest.getUsername(), loginRequest.getDeviceToken())
                .createAccHolaIfNone(loginRequest.getUsername(), loginRequest.getUserService())
                .activeUserService(loginRequest.getUserService())
                .updateLoginStatus(loginRequest.getUserService(), loginRequest.getDeviceToken())
                .findProfileById(loginRequest.getUsername(), loginRequest.getDeviceToken())
                .end();

        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RegisterResponse register(RegisterRequest registerReq) throws AccountDomainException {
        RegisterUseCase registerUseCase = new RegisterUseCaseImpl(accountRepository);
        RegisterResponse response = registerUseCase
                .registerIMedia(registerReq.getUsername(), registerReq.getPassword(), registerReq.getEmail())
                .fail()
                .success()
                .createAccHola(registerReq.getUsername(), registerReq.getUserService())
                .updateAccountStatus(registerReq.getUserService(), registerReq.getDeviceToken())
                .end();

        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PasswordResponse forgotPassword(PasswordRequest passwordRequest) throws AccountDomainException,
            OTPVerificationException {
        DeviceToken deviceToken = new DeviceToken();
        PasswordUseCase passwordUseCase = new PasswordUseCaseImpl(accountRepository, otpVerifyRepository);
        PasswordResponse response = passwordUseCase
                .accept(passwordRequest.getStep())
                .findAccountById(passwordRequest.getAccountId(), deviceToken)
                .accountNotFound(new AccountNotFoundException())
                .generatorOTP(passwordRequest.getAccountId())
                .resetPassword(passwordRequest.getAccountId(), passwordRequest.getPassword(), passwordRequest.getOtp())
                .end();

        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProfileResponse getProfileById(ProfileRequest profileRequest) throws AccountNotFoundException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        ProfileResponse response = profileUseCase
                .findProfileById(profileRequest.getAccountId(),
                        profileRequest.getDeviceToken())
                .profileNotFound()
                .end();
        return response;
    }

    public VAInfo getVAByUserId(VARequest request) throws AccountDomainException {
        VAUseCase vaUseCase = new VAUseCaseImpl(vaRepository, cashRepository, accountRepository);
        VAInfo response = vaUseCase.findVAByUserId(request.getUserId())
                .findVAByUserIdNotFound().endFindVAByUserId();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VAResponse registerVA(VARegisterRequest request) throws AccountDomainException {
        VAUseCase vaUseCase = new VAUseCaseImpl(vaRepository, cashRepository, accountRepository);
        VAResponse response = vaUseCase
                .getHolaAccount(request.getAccountId())
                .failGetAccount()
                .findVAByUserId(request.getAccountId())
                .findVAByUserIdIsExist()
                .createVAWalletIMedia(request)
                .createVAWallet()
                .failCreateVA()
                .endCreateVA();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ProfileResponse updateProfile(ProfileRequest profileRequest) throws AccountDomainException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        ProfileResponse response = profileUseCase
                .applyIdentity(profileRequest.getAccountId(), profileRequest.getAuditLog())
                .applyBasicInfo(profileRequest.getAvatar(), profileRequest.getDisplayName(),
                        profileRequest.getBirthday(), profileRequest.getGender())
                .update()
                .fail()
                .findProfileById(profileRequest.getAccountId(), profileRequest.getDeviceToken())
                .end();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ShipperResponse getShipperProfile(ShipperRequest shipperRequest) throws AccountDomainException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        ShipperResponse response = profileUseCase
                .path(shipperRequest.getPathInfo())
                .getShipperProfile(shipperRequest.getAccountId())
                .profileNotFound()
                .endShipper();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ShipperResponse updateShipperProfile(ShipperRequest shipperRequest) throws AccountDomainException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        ShipperResponse response = profileUseCase
                .path(shipperRequest.getPathInfo())
                .getShipperProfile(shipperRequest.getAccountId(), shipperRequest.getAuditLog())
                .profileNotFound()
                .applyCardID(shipperRequest.getCardID())
                .applyVehicle(shipperRequest.getVehicle())
                .applyDriverLicense(shipperRequest.getDriverLicense())
                .update()
                .fail()
                .getShipperProfile(shipperRequest.getAccountId())
                .endShipper();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public VerifyResponse identity(OTPVerifyRequest otpRequest) throws AccountDomainException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        VerifyResponse response = profileUseCase
                .getHolaAccount(otpRequest.getAccountId(), otpRequest.getDeviceToken())
                .getHolaAccountFirstLogin(otpRequest.getAccountId())
                .activeAccount(otpRequest.getAccountId(), otpRequest.getOtp(),
                        otpRequest.getAuditLog(), otpRequest.getDeviceToken(),
                        otpRequest.getServiceName())
                .fail()
                .endIdentity();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BankAccountResponse confirmBankInfo(BankAccountRequest request) throws AccountDomainException {
        VAUseCase vaUseCase = new VAUseCaseImpl(vaRepository, cashRepository, accountRepository);
        BankAccountResponse response = vaUseCase
                .confirmBankInfoIMedia(request.getAccountId(), request.getBankIMediaProvider(), request.getBankInfo(),
                        request.getOtp())
                .failIMediaResponse()
                .getHolaAccount(request.getAccountId())
                .failGetAccount()
                .findBankAccount(request.getAccountId(), request.getBankInfo())
                .createBankAccountIfNone(request.getAccountId())
                .failCreateBank()
                .endConfirmBankAccount();
        return response;
    }

    public BankAccountListResponse getBankAccount(BankAccountRequest request) throws AccountDomainException {
        ProfileUseCase profileUseCase = new ProfileUseCaseImpl(accountRepository);
        BankAccountListResponse bankAccountListResponse = profileUseCase
                .getBankAccount(request.getAccountId(), request.getServiceName())
                .endGetBankAccount();
        return bankAccountListResponse;
    }


}
