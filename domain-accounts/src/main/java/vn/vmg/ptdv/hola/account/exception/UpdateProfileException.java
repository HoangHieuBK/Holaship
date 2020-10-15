package vn.vmg.ptdv.hola.account.exception;

public class UpdateProfileException extends AccountDomainException {
    public UpdateProfileException(String msg) {
        super(msg);
    }

    public UpdateProfileException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public UpdateProfileException() {
        super();
    }
}
