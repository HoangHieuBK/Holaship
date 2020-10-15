package vn.vmg.ptdv.hola.servicepack.presentation;


import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class ServicePackRequest {

    private ServicePackID servicePackID;
    private String name;
    private String note;
    private Integer active;
    private Integer status;
    private OffsetDateTime effectiveAt;
    private AuditLog auditLog;
    private Integer maxTime;
    private Integer maxDistance;
    private Integer maxPoint;
    private Integer maxOrder;
    private Integer baseDistance;
    private Integer basePoint;
    private Integer baseCost;
    private Integer baseOrderDetail;
    private Long surchargeDistance;
    private Long surchargePoint;
    private Long surchargeOrderDetail;
    private Long porterFee;
    private Long doorDeliveryFee;
    private Long refundFee;
    private Long priceDeclareFee;
    private Integer type;
    private Long settingId;
    private Long otherCost;

    public ServicePackRequest() {
    }

}
