package vn.vmg.ptdv.hola.common.exception;

public class SecurityDESException extends Exception {
    public SecurityDESException() {
    }

    public SecurityDESException(String message) {
        super(message);
    }

    public SecurityDESException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityDESException(Throwable cause) {
        super(cause);
    }

    public SecurityDESException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
