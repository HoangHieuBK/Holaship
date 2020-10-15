package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.time.Instant;

@Data
public class ServicePackJSONResponse {

    private Long id;
    private String code;
    private String name;
    private Integer status;
    private String note;
    private Integer active;
    private Instant effectiveAt;
    private Instant createdAt;
    private Long createdBy;
    private Instant updatedAt;
    private Long updatedBy;
    private Instant uTimestamp;

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

}
