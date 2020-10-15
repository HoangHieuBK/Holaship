package vn.vmg.ptdv.hola.account.service.usecase;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.core.UserService;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountExistedException;
import vn.vmg.ptdv.hola.account.presentation.RegisterResponse;

public interface RegisterUseCase {
    RegisterUseCase registerIMedia(AccountId username, Password password, String email) throws AccountDomainException;

    RegisterUseCase fail() throws AccountExistedException;

    RegisterUseCase success();

    RegisterUseCase createAccHola(AccountId username, UserService userService) throws AccountDomainException;

    RegisterUseCase updateAccountStatus(UserService userService, DeviceToken deviceToken) throws AccountDomainException;

    RegisterResponse end();
}
