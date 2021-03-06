package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.core.UserService;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.validator.AccountError;
import vn.vmg.ptdv.hola.account.validator.EmailValidator;
import vn.vmg.ptdv.hola.account.validator.PasswordValidator;
import vn.vmg.ptdv.hola.account.validator.PhoneNumberValidator;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegisterRequest {
    private AccountId username;
    private String email;
    private Password password;
    private UserService userService;
    private DeviceToken deviceToken;

    public RegisterRequest(String phoneNumber, String email, String password, String confirmPassword,
            String serviceName, String deviceToken) throws AccountDomainException, SecurityDESException {
        this.username = new AccountId(phoneNumber);
        this.email = email;
        this.password = new Password().withEncrypted(password).withConfirm(confirmPassword);
        this.userService = new UserService(serviceName);
        this.deviceToken = new DeviceToken().withUniqueToken(deviceToken);
    }

    private void validate() throws AccountDomainException {
        List<AccountError> errors = new ArrayList<>();
        errors.addAll(PhoneNumberValidator.validate(username.getPhoneNumber())
                .stream().map(vr -> new AccountError(vr.code, "PHONE", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(EmailValidator.validate(email)
                .stream().map(vr -> new AccountError(vr.code, "EMAIL", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(PasswordValidator.validate(password.getClearText())
                .stream().map(vr -> new AccountError(vr.code, "PASSWORD", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }
    }
}
