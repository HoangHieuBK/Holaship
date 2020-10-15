package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VADB {
    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String SHIP_CODE = "SHIP_CODE";
    public static final String SHOP_CODE = "SHOP_CODE";
    public static final String UTIMESTAMP = "UTIMESTAMP";
    public static final String BANK_CODE = "BANK_CODE";
    public static final String BANK_NAME = "BANK_NAME";
    public static final String PHONE = "PHONE";

    private Long userId;
    private String userName;
    private String shipCode;
    private String shopCode;
    private String bankCode;
    private String bankName;
    private String phone;
    private Timestamp uTimestamp;
}
