package vn.vmg.ptdv.hola.common.otp.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OTPVerification {

    private Long id;
    private String phone;
    private String otp;
    private int roleType;
    private Timestamp uTimestamp;
    private String device;

    public OTPVerification() {
    }

    public OTPVerification(String otp) {
        this.otp = otp;
    }

    public OTPVerification(String phone, String otp) {
        this.phone = phone;
        this.otp = otp;
    }

    public OTPVerification(Long id, String phone, String otp, int roleType, Timestamp uTimestamp, String device) {
        this.id = id;
        this.phone = phone;
        this.otp = otp;
        this.roleType = roleType;
        this.uTimestamp = uTimestamp;
        this.device = device;
    }


}
