package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class ShipperInfoDB {
    private Long id;
    private Timestamp utimestamp;
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

}
