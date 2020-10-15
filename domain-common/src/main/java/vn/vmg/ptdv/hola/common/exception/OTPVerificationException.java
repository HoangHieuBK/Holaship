package vn.vmg.ptdv.hola.common.exception;

public class OTPVerificationException extends Exception {
    public OTPVerificationException() {
    }

    public OTPVerificationException(String message) {
        super(message);
    }

    public OTPVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OTPVerificationException(Throwable cause) {
        super(cause);
    }

    public OTPVerificationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}