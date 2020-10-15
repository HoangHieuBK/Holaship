package vn.vmg.ptdv.hola.exception;

import java.util.List;

public class EntityUpdateException extends OrderDomainException {
    public EntityUpdateException(List<OrderError> errors) {
        super(errors);
    }

    public EntityUpdateException(String errorMsg) {
        super(errorMsg);
    }

    public EntityUpdateException(Throwable cause) {
        super(cause);
    }

    public EntityUpdateException(int code, String errorMsg) {
        super(code, errorMsg);
    }
}
