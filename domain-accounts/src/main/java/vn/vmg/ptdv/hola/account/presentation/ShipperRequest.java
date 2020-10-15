package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.service.impl.PartInfo;

@Data
public class ShipperRequest {
    private AccountId accountId;
    private PartInfo pathInfo;
    private CardID cardID;
    private Vehicle vehicle;
    private DriverLicense driverLicense;
    private AuditLog auditLog;
    private String part;

    public ShipperRequest(String phoneNumber) {
        this.accountId = new AccountId(phoneNumber);
    }

    public ShipperRequest(String phoneNumber, String part) {
        this.accountId = new AccountId(phoneNumber);
        this.part = part;
    }

    public ShipperRequest withCardID(CardID cardID) {
        this.cardID = cardID;
        return this;
    }

    public ShipperRequest withDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
        return this;
    }

    public ShipperRequest withVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public ShipperRequest withAuditLog(AuditLog auditLog) {
        this.auditLog = auditLog;
        return this;
    }

    public ShipperRequest build() {
//        pathInfo = PartInfo.BASIC;
//
//        if (cardID != null) {
//            pathInfo = PartInfo.CMND;
//        }
//        if (vehicle != null) {
//            pathInfo = PartInfo.VEHICLE;
//        }
//        if (driverLicense != null) {
//            pathInfo = PartInfo.LICENSE;
//        }
        return this;
    }

    public ShipperRequest withPartInfo(String part) {
        this.pathInfo = PartInfo.valueOf(part.toUpperCase());
        return this;
    }
}
