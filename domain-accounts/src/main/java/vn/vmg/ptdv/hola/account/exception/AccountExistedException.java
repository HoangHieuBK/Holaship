package vn.vmg.ptdv.hola.account.exception;

public class AccountExistedException extends AccountDomainException {
    public AccountExistedException(String msg) {
        super(msg);
    }

    public AccountExistedException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
