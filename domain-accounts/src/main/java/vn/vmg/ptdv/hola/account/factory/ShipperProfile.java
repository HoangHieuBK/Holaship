package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.*;

@Data
public class ShipperProfile {
    private AccountId accountId;
    private AuditLog auditLog;
    private CardID cardID;
    private DriverLicense driverLicense;
    private Vehicle vehicle;
}
