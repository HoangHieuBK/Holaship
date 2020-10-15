package vn.vmg.ptdv.hola.infra.imedia;

public class IMediaAPIConfig {
    public class SSORequestCode {
        public static final int PR_LOGIN = 1100;// Đăng nhập sử dụng user&password
        public static final int PR_LOGIN_SOCIAL = 1200;// Đăng nhập từ FB
        public static final int PR_LOGIN_GMAIL = 1300;// Đăng nhập từ Google
        public static final int PR_REGISTER = 1400;// Đăng ký
        public static final int PR_ACTIVE_PHONE = 1500;// Active số điện thoại
        public static final int PR_ACTIVE_EMAIL = 1600;// Active Email
        public static final int PR_UPDATE_USER_INFO = 1700;
        // Cập nhật thông tin user(tên hiển thị, ngày sinh nhật, gới tính)
        public static final int PR_GET_USER_INF = 1800;// Lấy thông tin user
        public static final int PR_INNIT_PAYMENT = 1900;
        public static final int PR_GET_OTP = 2000;
        // Lấy OTP cho một nghiệp vụ xác định(reset pass, cập nhật thông tin, thanh toán,...)
        public static final int PR_SUBMIT_PAYMENT = 2100;
        public static final int PR_CHANGE_PASS = 2200;// Đổi mật khẩu sau khi đã login success
        public static final int PR_GET_BILL_INFO = 2300;
        public static final int PR_GET_HISTORY = 2400;
        public static final int PR_GET_HISTORY_DETAIL = 2500;
        public static final int PR_LOGOUT = 2600;// Đăng xuất
        public static final int PR_ADD_HOLDING_BALANCE = 2700;// Tăng số dư tạm giữ
        public static final int PR_SUBTRACT_HOLDING_BALANCE = 2800;// Trừ số dư tạm giữ
        public static final int PR_CREDIT_BALANCE = 2900;// Cộng tiền xuất phát từ client
        public static final int PR_DEBIT_BALANCE = 3000;// Trừ tiên xuất phát từ client
        public static final int PR_RESET_PASS = 3100;// Reset password(yêu cầu OTP)
        public static final int PR_REGISTER_VA_ACCOUNT = 3200;// Active tài khoản VA
        public static final int PR_DEBIT_SERVER_SERVER = 3300;// Trừ tiền tài khoản
        public static final int PR_CREDIT_SERVER_SERVER = 3400;// Cộng tiền vào tài khoản
        public static final int PR_VERIFICATION_TOP = 3500;// verification sdt
    }

    //public static final String CLIENT_IDENTITY = "8d19bea4-ed3e-47dd-a7e3-d5941585c7d5";
//    public static final String KEY3DES = "1f2f8c540779987923165751";
    public static final String SERVICE_CODE = "f90b20cd58124b2ab992fab896ba888e";
    public static final String SERVICE_CODE_SHIP = "f90b20cd58124b2ab992fab896ba888e";
    public static final String SERVICE_CODE_SHOP = "";
    public static final String CLIENT_IDENTITY = "8d19bea4-ed3e-47dd-a7e3-d5941585c7d5";
    //
    public static final String KEY3DES1 = "e13acaa1e57e88a3d2be29ac";
    public static final String SERVICE_CODE1 = "bfda375d84345565acc35996d12040e7";
    //
    public static final int CLIENT_TYPE = 3;
    public static final int PR_CHANGEPASS = 2200;
    public static final int PR_GETUSER_INF = 1800;
    public static final String SSO_URL = "http://14.232.245.102:8086/SSO/API";
    public static final String CLIENT_VERSION = "1.0.0";
}
