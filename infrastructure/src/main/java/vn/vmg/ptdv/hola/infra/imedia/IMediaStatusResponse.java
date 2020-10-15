package vn.vmg.ptdv.hola.infra.imedia;

public enum IMediaStatusResponse {
    SUCCESS(200, "thanh cong"),
    ACCOUNT_ALREADY_EXISTS(132, "tai khoan da ton tai"),
    SYSTEM_BUSY(106, "He thong dang ban thu lai sau");

    public final int code;
    public final String message;

    IMediaStatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
