package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.core.Destination;
import vn.vmg.ptdv.hola.servicepack.core.Distance;
import vn.vmg.ptdv.hola.servicepack.core.OrderDetailSetting;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Money;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class SPUpdateRequest {
    private ServicePackID servicePackID;
    private String name;
    private String description;
    private Integer deliveryTimeMax;
    private Money priceBase;
    private LocalDate effectiveDate;
    private OrderDomainState state;
    private Distance distance;
    private Destination destination;
    private OrderDetailSetting orderDetailSetting;
    private Money porterage;
    private Money d2dCost;
    private Money refundCost;
    private Money declarePriceCost;
    private Money otherCost;
    private AuditLog auditLog;

    public SPUpdateRequest() {
    }

    public SPUpdateRequest(String servicePackCode, Instant uTimestamp) {
//        this.servicePackID = new ServicePackID(servicePackCode);
        this.auditLog = new AuditLog().withUTimestamp(Timestamp.from(uTimestamp));
    }

    public SPUpdateRequest(String code, Boolean state, Instant uTimestamp) {
//        this.servicePackID =  new ServicePackID(code);
        this.state = OrderDomainState.fromBoolean(state);
        this.auditLog = new AuditLog().withUTimestamp(Timestamp.from(uTimestamp));
    }
}
