package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class VAInfo {
    private Long userId;
    private String userName;
    private String shipCode;
    private String shopCode;
    private String otp;
    private String bankCode;
    private String bankName;
    private Timestamp uTimestamp;
}
