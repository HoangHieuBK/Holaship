package vn.vmg.ptdv.hola.exception;

public class OrderExistedException extends OrderDomainException {
    public OrderExistedException(String msg) {
        super(msg);
    }

    public OrderExistedException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
