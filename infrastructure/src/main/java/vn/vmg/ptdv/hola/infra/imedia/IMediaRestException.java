package vn.vmg.ptdv.hola.infra.imedia;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class IMediaRestException extends Exception {
    private int code;
    private String message;

    public IMediaRestException() {
    }

    public IMediaRestException(String message) {
        super(message);
    }

    public IMediaRestException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public IMediaRestException(String message, Throwable cause) {
        super(message, cause);
    }

    public IMediaRestException(Throwable cause) {
        super(cause);
    }

    public IMediaRestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
