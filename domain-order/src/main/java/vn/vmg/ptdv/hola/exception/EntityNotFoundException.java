package vn.vmg.ptdv.hola.exception;


public class EntityNotFoundException extends OrderDomainException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }

    public EntityNotFoundException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public EntityNotFoundException() {
        super();
    }
}
