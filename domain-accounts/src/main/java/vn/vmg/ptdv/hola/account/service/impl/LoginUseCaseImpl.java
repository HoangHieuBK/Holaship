package vn.vmg.ptdv.hola.account.service.impl;

import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.UnauthorizedException;
import vn.vmg.ptdv.hola.account.factory.HolaAccount;
import vn.vmg.ptdv.hola.account.factory.HolaProfile;
import vn.vmg.ptdv.hola.account.factory.LoginInfo;
import vn.vmg.ptdv.hola.account.presentation.LoginResponse;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.service.usecase.LoginUseCase;

public class LoginUseCaseImpl implements LoginUseCase {
    private final AccountRepository accountRepository;
    private LoginInfo iMediaAcc;
    private LoginResponse loginResponse;
    private Boolean isFirstLogin = false;
    private HolaAccount holaAccount;
    private HolaProfile profile;
    public LoginUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public LoginUseCase loginIMedia(AccountId accountId, Password password) {
        iMediaAcc = accountRepository.getIMediaAcc(accountId.getPhoneNumber(), password.getMd5Encrypted());
        return this;
    }

    @Override
    public LoginUseCase fail(UnauthorizedException exception) throws UnauthorizedException {
        if (iMediaAcc == null) {
            throw new UnauthorizedException("Sai user hoặc password");
        }
        return this;
    }

    @Override
    public LoginUseCase success() {
        loginResponse = new LoginResponse();
        return this;
    }

    @Override
    public LoginUseCase getHolaAccount(AccountId accountId, DeviceToken deviceToken) {
        holaAccount = accountRepository.findByAccountId(accountId, deviceToken);
        if (holaAccount == null) {
            isFirstLogin = true;
        }
        holaAccount = accountRepository.findByAccountId(accountId, null);
        return this;
    }



    @Override
    public LoginUseCase createAccHolaIfNone(AccountId accountId, UserService userService) throws
            AccountDomainException {
        if (holaAccount != null) {
            return this;
        }
        Integer ship = userService.getServiceName() == HolaProvider.SHIP ? 1 : null;
        Integer shop = userService.getServiceName() == HolaProvider.SHOP ? 1 : null;
        int created = accountRepository.createHolaAcc(accountId, iMediaAcc, 0, shop);
        if (created == 0) {
            throw new AccountDomainException("Cannot create Hola Account");
        }
        holaAccount = new HolaAccount();
        holaAccount.setAccountId(new AccountId(accountId.getId(), accountId.getPhoneNumber()));
        holaAccount.setEmail(iMediaAcc.getEmail());
        holaAccount.setServiceFor(userService);

        return this;
    }

    @Override
    public LoginUseCase activeUserService(UserService userService) throws AccountDomainException {
        if (holaAccount == null || holaAccount.enabled(userService)) {
            return this;
        }
        Integer ship = userService.getServiceName() == HolaProvider.SHIP ? 1 : null;
        Integer shop = userService.getServiceName() == HolaProvider.SHOP ? 1 : null;
        int update = accountRepository.activeAccount(holaAccount.getAccountId(), ship, shop);
        if (update == 0) {
            throw new AccountDomainException("Cannot active Hola Account");
        }

        return this;
    }

    @Override
    public LoginUseCase updateLoginStatus(UserService userService, DeviceToken deviceToken) throws
            AccountDomainException {
//        Integer shop = userService.getServiceName() == HolaProvider.SHOP ? 1 : null;
//        Integer ship = userService.getServiceName() == HolaProvider.SHIP ? 1 : null;
//        int updated = accountRepository
//                .saveUserDevice(holaAccount.getAccountId(), deviceToken.getUniqueToken(), ship, shop);
//        if (updated == 0) {
//            throw new AccountDomainException("Cannot update Hola Account info");
//        }
        return this;
    }

    @Override
    public LoginUseCase findProfileById(AccountId accountId, DeviceToken deviceToken) {
        profile = accountRepository.findProfileById(accountId);
        HolaProvider shipActive = profile.getHolaService().getShipService();
        if(shipActive == HolaProvider.SHIP){
            profile.setDeviceToken(new DeviceToken(false));
        } else{
            profile.setDeviceToken(new DeviceToken(true));
        }
        return this;
    }
    @Override
    public LoginResponse end() {
        loginResponse.setSessionKey(new SessionKey(iMediaAcc.getSessionKey()));
        loginResponse.setUsername(holaAccount.getAccountId());
        loginResponse.setIsFirstLogin(isFirstLogin);
        loginResponse.setAuditLog(profile.getAuditLog());
        return loginResponse;
    }


}
