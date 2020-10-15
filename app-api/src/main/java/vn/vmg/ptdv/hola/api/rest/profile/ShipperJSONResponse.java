package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vn.vmg.ptdv.hola.account.core.CardID;
import vn.vmg.ptdv.hola.account.core.DriverLicense;
import vn.vmg.ptdv.hola.account.core.Vehicle;
import vn.vmg.ptdv.hola.account.factory.ShipperProfile;
import vn.vmg.ptdv.hola.account.presentation.ShipperResponse;

import java.time.Instant;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipperJSONResponse {
    private String phoneNumber;
    private Instant uTimestamp;
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

    public ShipperJSONResponse(String phone, ShipperResponse response) {
        ShipperProfile p = response.getShipperProfile();
        this.phoneNumber = phone;
        this.uTimestamp = p.getAuditLog().getUTimestamp().toInstant();

        DriverLicense d = p.getDriverLicense();
        if (d != null) {
            this.driverLicense = d.getDriverLicense();
            this.speciesLicenseType = d.getSpeciesLicenseType();
            this.imgAfterDriverLicense = d.getImgAfterDriverLicense();
            this.imgBeforeDriverLicense = d.getImgBeforeDriverLicense();
        }

        CardID c = p.getCardID();
        if (c != null) {
            this.numberCardID = c.getNumberCardID();
            this.dateCardID = c.getDateCardID();
            this.placeCardID = c.getPlaceCardID();
            this.imgBeforeCardID = c.getImgBeforeCardID();
            this.imgAfterCardID = c.getImgAfterCardID();
        }

        Vehicle v = p.getVehicle();
        if (v != null) {
            this.vehicleType = v.getVehicleType();
            this.licensePlace = v.getLicensePlace();
            this.imgBeforeDriverRegister = v.getImgBeforeDriverRegister();
            this.imgAfterDriverRegister = v.getImgAfterDriverRegister();
            this.imgBeforeInsurance = v.getImgBeforeInsurance();
            this.imgAfterInsurance = v.getImgAfterInsurance();
        }
    }
}
