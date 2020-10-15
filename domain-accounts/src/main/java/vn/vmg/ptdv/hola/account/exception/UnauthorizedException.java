package vn.vmg.ptdv.hola.account.exception;

public class UnauthorizedException extends AccountDomainException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super(100, "Sai user hoặc password!");
    }
}
