package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class ServicePackDetailJSONResponse {
    private Long id;
    private Long servicePackSettingId;
    private String name;
    private String code;
    private Long settingId;
    private String note;
    private Integer active;
    private Integer status;
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
    private Long createdBy;
    private LocalDate createdAt;
    private Long updatedBy;
    private LocalDate updatedAt;
    private LocalDate effectiveAt;
    private Long servicePackId;
    private Long otherCost;
    private Instant uTimestamp;
}
