package vn.vmg.ptdv.hola.common.masterData.exception;

public class MasterDataException extends Exception {
    public MasterDataException() {
    }

    public MasterDataException(String message) {
        super(message);
    }

    public MasterDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MasterDataException(Throwable cause) {
        super(cause);
    }

    public MasterDataException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
