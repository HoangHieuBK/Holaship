package vn.vmg.ptdv.hola.api.rest.profile;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class ShipperJSONRequest {
    private String phoneNumber;
    private Instant utimestamp;
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

    public ShipperJSONRequest() {
    }

    public ShipperJSONRequest(@PathVariable String phoneNumber, Instant utimestamp, String numberCardID,
            LocalDate dateCardID,
            String placeCardID, String imgBeforeCardID, String imgAfterCardID) {
        this.phoneNumber = phoneNumber;
        this.utimestamp = utimestamp;
        this.numberCardID = numberCardID;
        this.dateCardID = dateCardID;
        this.placeCardID = placeCardID;
        this.imgBeforeCardID = imgBeforeCardID;
        this.imgAfterCardID = imgAfterCardID;
    }

    public ShipperJSONRequest(@PathVariable String phoneNumber, Instant utimestamp, Integer vehicleType,
            String licensePlace,
            String imgBeforeDriverRegister, String imgAfterDriverRegister, String imgBeforeInsurance,
            String imgAfterInsurance) {
        this.phoneNumber = phoneNumber;
        this.utimestamp = utimestamp;
        this.vehicleType = vehicleType;
        this.licensePlace = licensePlace;
        this.imgBeforeDriverRegister = imgBeforeDriverRegister;
        this.imgAfterDriverRegister = imgAfterDriverRegister;
        this.imgBeforeInsurance = imgBeforeInsurance;
        this.imgAfterInsurance = imgAfterInsurance;
    }

    public ShipperJSONRequest(@PathVariable String phoneNumber, Instant utimestamp, String driverLicense,
            Integer speciesLicenseType, String imgBeforeDriverLicense, String imgAfterDriverLicense) {
        this.phoneNumber = phoneNumber;
        this.utimestamp = utimestamp;
        this.driverLicense = driverLicense;
        this.speciesLicenseType = speciesLicenseType;
        this.imgBeforeDriverLicense = imgBeforeDriverLicense;
        this.imgAfterDriverLicense = imgAfterDriverLicense;
    }

    public ShipperJSONRequest(@PathVariable String phoneNumber, Instant utimestamp, String numberCardID, LocalDate dateCardID,
            String placeCardID, String imgBeforeCardID, String imgAfterCardID, Integer vehicleType,
            String licensePlace, String imgBeforeDriverRegister, String imgAfterDriverRegister,
            String imgBeforeInsurance, String imgAfterInsurance, String driverLicense, Integer speciesLicenseType,
            String imgBeforeDriverLicense, String imgAfterDriverLicense,@RequestParam String part) {
        this.phoneNumber = phoneNumber;
        this.utimestamp = utimestamp;
        this.numberCardID = numberCardID;
        this.dateCardID = dateCardID;
        this.placeCardID = placeCardID;
        this.imgBeforeCardID = imgBeforeCardID;
        this.imgAfterCardID = imgAfterCardID;
        this.vehicleType = vehicleType;
        this.licensePlace = licensePlace;
        this.imgBeforeDriverRegister = imgBeforeDriverRegister;
        this.imgAfterDriverRegister = imgAfterDriverRegister;
        this.imgBeforeInsurance = imgBeforeInsurance;
        this.imgAfterInsurance = imgAfterInsurance;
        this.driverLicense = driverLicense;
        this.speciesLicenseType = speciesLicenseType;
        this.imgBeforeDriverLicense = imgBeforeDriverLicense;
        this.imgAfterDriverLicense = imgAfterDriverLicense;
    }
}
