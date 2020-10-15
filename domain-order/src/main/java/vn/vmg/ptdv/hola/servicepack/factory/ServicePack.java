package vn.vmg.ptdv.hola.servicepack.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

@Data
public class ServicePack {

    private ServicePackID servicePackID;
    private String name;
    private Integer status;
    private String note;
    private Integer active;
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
    private Long otherCost;

    public ServicePack() {

    }

    //    private Integer deliveryTimeMax;
//    private Money priceBase;

//    private OrderDomainState state;
//    private Distance distance;
//    private Destination destination;
//    private OrderDetailSetting orderDetailSetting;
//    private Money porterage;
//    private Money d2dCost;
//    private Money refundCost;
//    private Money declarePriceCost;
//    private Money otherCost;

}
