package vn.vmg.ptdv.hola.exception;

import java.util.List;

public class EntityDeleteException extends OrderDomainException {
    public EntityDeleteException(List<OrderError> errors) {
        super(errors);
    }

    public EntityDeleteException(String errorMsg) {
        super(errorMsg);
    }

    public EntityDeleteException(Throwable cause) {
        super(cause);
    }

    public EntityDeleteException(int code, String errorMsg) {
        super(code, errorMsg);
    }
}
