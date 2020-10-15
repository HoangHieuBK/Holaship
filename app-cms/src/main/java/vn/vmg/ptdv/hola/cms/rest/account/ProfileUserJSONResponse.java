package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class ProfileUserJSONResponse {
    private String id;
    private String displayName;
    private String phone;
    private String avatar;
    private LocalDate birthDay;
    private String gender;
    private String address;
    private String email;

    private String serviceName;

    private String numberCardID;
    private LocalDate dateCardID;
    private String placeCardID;
    private String imgBeforeCardID;
    private String imgAfterCardID;

    private Integer vehicleType;
    private String licensePlace;
    private String imgBeforeDriverRegister;
    private String imgAfterDriverRegister;
    private String imgBeforeInsurance;
    private String imgAfterInsurance;

    private String driverLicense;
    private Integer speciesLicenseType;
    private String imgBeforeDriverLicense;
    private String imgAfterDriverLicense;

    private String bank;
    private String bankAccount;
    private String bankAccountName;

    private Instant uTimestamp;
}
