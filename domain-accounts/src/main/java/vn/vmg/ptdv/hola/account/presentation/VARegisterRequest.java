package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.HolaService;
import vn.vmg.ptdv.hola.account.core.SessionKey;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.validator.AccountError;
import vn.vmg.ptdv.hola.account.validator.PhoneNumberValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class VARegisterRequest {
    private AccountId accountId;
    private String userName;
    private String otp;
    private SessionKey sessionKey;
    private String customerName;
    private HolaService serviceName;

    public VARegisterRequest(Long userId, String userName, String otp, String sessionKey, String customerName,
            String serviceName) {
        this.accountId = new AccountId(userId);
        this.userName = userName;
        this.otp = otp;
        this.sessionKey = new SessionKey(sessionKey);
        this.customerName = customerName;
        this.serviceName = new HolaService().withServiceName(serviceName);
    }

    public VARegisterRequest(String phoneNumber, String userName, String otp, String sessionKey, String customerName
    ) {
        this.accountId = new AccountId(phoneNumber);
        this.userName = userName;
        this.otp = otp;
        this.sessionKey = new SessionKey(sessionKey);
        this.customerName = customerName;
    }

    public static VARegisterRequest vaRegister(Long userId, String userName, String otp, String sessionKey,
            String customerName, String serviceCode) throws AccountDomainException {
        validate(userName);
        return new VARegisterRequest(userId, userName, otp, sessionKey, customerName, serviceCode);
    }

    public static void validate(String phone) throws AccountDomainException {
        List<AccountError> errors = new ArrayList<AccountError>();
        errors.addAll(PhoneNumberValidator.validate(phone)
                .stream().map(vr -> new AccountError(vr.code, "PHONE", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }
    }
}
