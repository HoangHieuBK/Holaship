package vn.vmg.ptdv.hola.exception;

import java.util.List;

public class EntityInsertException extends OrderDomainException {

    public EntityInsertException(List<OrderError> errors) {
        super(errors);
    }

    public EntityInsertException(String errorMsg) {
        super(errorMsg);
    }

    public EntityInsertException(Throwable cause) {
        super(cause);
    }

    public EntityInsertException(int code, String errorMsg) {
        super(code, errorMsg);
    }

}
