package vn.vmg.ptdv.hola.account.service.usecase;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.core.UserService;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.UnauthorizedException;
import vn.vmg.ptdv.hola.account.presentation.LoginResponse;

public interface LoginUseCase {
    LoginUseCase loginIMedia(AccountId username, Password password);

    LoginUseCase fail(UnauthorizedException clazz) throws UnauthorizedException;

    LoginUseCase success();

    LoginUseCase getHolaAccount(AccountId username, DeviceToken deviceToken);

    LoginUseCase createAccHolaIfNone(AccountId username, UserService userService) throws AccountDomainException;

    LoginUseCase activeUserService(UserService userService) throws AccountDomainException;

    LoginUseCase updateLoginStatus(UserService userService, DeviceToken deviceToken) throws AccountDomainException;

    LoginUseCase findProfileById(AccountId accountId, DeviceToken deviceToken);

    LoginResponse end();
}
