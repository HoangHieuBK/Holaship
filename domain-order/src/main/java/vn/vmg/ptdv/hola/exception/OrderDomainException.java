package vn.vmg.ptdv.hola.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class OrderDomainException extends Exception {
    private List<OrderError> errors;
    private int errorCode;

    public OrderDomainException(String msg) {
        super(msg);
    }

    public OrderDomainException() {
        super();
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDomainException(Throwable cause) {
        super(cause);
    }

    protected OrderDomainException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OrderDomainException(List<OrderError> errors) {
        this.errors = errors;
    }

    public OrderDomainException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
