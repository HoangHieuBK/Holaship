package vn.vmg.ptdv.hola.account.exception;

public class AccountNotFoundException extends AccountDomainException {
    public AccountNotFoundException(String msg) {
        super(msg);
    }

    public AccountNotFoundException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public AccountNotFoundException() {
        super();
    }
}
