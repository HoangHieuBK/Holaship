package vn.vmg.ptdv.hola.account.service.impl;

import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountExistedException;
import vn.vmg.ptdv.hola.account.factory.HolaAccount;
import vn.vmg.ptdv.hola.account.factory.RegisterInfo;
import vn.vmg.ptdv.hola.account.presentation.RegisterResponse;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.service.usecase.RegisterUseCase;

public class RegisterUseCaseImpl implements RegisterUseCase {
    private final AccountRepository accountRepository;
    private RegisterInfo iMediaAcc;
    private HolaAccount holaAccount;
    private RegisterResponse registerResponse;

    public RegisterUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public RegisterUseCase registerIMedia(AccountId accountId, Password password, String email) throws
            AccountDomainException {
        iMediaAcc = accountRepository.saveIMediaAcc(accountId.getPhoneNumber(), password.getClearText(), email);
        return this;
    }

    @Override
    public RegisterUseCase fail() throws AccountExistedException {
        if (iMediaAcc == null || iMediaAcc.getErrorCode() != -1) {

            throw new AccountExistedException(iMediaAcc.getErrorCode(), iMediaAcc.getErrorMsg());
        }
        return this;
    }

    @Override
    public RegisterUseCase success() {
        registerResponse = new RegisterResponse();
        return this;
    }

    @Override
    public RegisterUseCase createAccHola(AccountId accountId, UserService userService) throws AccountDomainException {
        Integer ship = userService.getServiceName() == HolaProvider.SHIP ? 1 : null;
        Integer shop = userService.getServiceName() == HolaProvider.SHOP ? 1 : null;

        if(userService.getServiceName() == HolaProvider.SHIP){
            ship = 0;
        }
        if(userService.getServiceName() == HolaProvider.SHOP){
            shop = 0;
        }
        int created = accountRepository.createHolaAcc(accountId, iMediaAcc, ship, shop);
        if (created == 0) {
            throw new AccountDomainException("Cannot create Hola Account");
        }
        holaAccount = new HolaAccount();
        holaAccount.setAccountId(accountId);
        holaAccount.setServiceFor(userService);
        holaAccount.setEmail(iMediaAcc.getEmail());
        return this;
    }

    @Override
    public RegisterUseCase updateAccountStatus(UserService userService, DeviceToken deviceToken) throws
            AccountDomainException {
//        Integer ship = userService.getServiceName() == HolaProvider.SHIP ? 1 : null;
//        Integer shop = userService.getServiceName() == HolaProvider.SHOP ? 1 : null;
//        int updated = accountRepository
//                .saveUserDevice(holaAccount.getAccountId(), deviceToken.getUniqueToken(), ship, shop);
//        if (updated == 0) {
//            throw new AccountDomainException("Cannot update Hola Account info");
//        }
        return this;
    }

    @Override
    public RegisterResponse end() {
        registerResponse.setEmail(holaAccount.getEmail());
        registerResponse.setPhoneNumber(holaAccount.getAccountId().getPhoneNumber());

        return registerResponse;
    }
}
