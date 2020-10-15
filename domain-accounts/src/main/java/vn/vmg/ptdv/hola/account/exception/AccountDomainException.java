package vn.vmg.ptdv.hola.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.vmg.ptdv.hola.account.validator.AccountError;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class AccountDomainException extends Exception {
    private List<AccountError> errors;
    private int errorCode;

    public AccountDomainException(String msg) {
        super(msg);
    }

    public AccountDomainException() {
        super();
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDomainException(Throwable cause) {
        super(cause);
    }

    protected AccountDomainException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccountDomainException(List<AccountError> errors) {
        this.errors = errors;
    }

    public AccountDomainException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
