package vn.vmg.ptdv.hola.cms.rest.servicepack;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServicePackCreateJSONRequest {
    private String name;
    private String code;
    private String note;
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
    private LocalDate effectiveAt;
    private Long otherCost;

    public ServicePackCreateJSONRequest() {
    }

    public ServicePackCreateJSONRequest(String name, String code, String note, Integer maxTime,
            Integer maxDistance, Integer maxPoint, Integer maxOrder, Integer baseDistance, Integer basePoint,
            Integer baseCost, Integer baseOrderDetail, Long surchargeDistance, Long surchargePoint,
            Long surchargeOrderDetail, Long porterFee, Long doorDeliveryFee, Long refundFee, Long priceDeclareFee,
            Integer type, Long createdBy, LocalDate effectiveAt, Long otherCost) {
        this.name = name;
        this.code = code;
        this.note = note;
        this.maxTime = maxTime;
        this.maxDistance = maxDistance;
        this.maxPoint = maxPoint;
        this.maxOrder = maxOrder;
        this.baseDistance = baseDistance;
        this.basePoint = basePoint;
        this.baseCost = baseCost;
        this.baseOrderDetail = baseOrderDetail;
        this.surchargeDistance = surchargeDistance;
        this.surchargePoint = surchargePoint;
        this.surchargeOrderDetail = surchargeOrderDetail;
        this.porterFee = porterFee;
        this.doorDeliveryFee = doorDeliveryFee;
        this.refundFee = refundFee;
        this.priceDeclareFee = priceDeclareFee;
        this.type = type;
        this.createdBy = createdBy;
        this.effectiveAt = effectiveAt;
        this.otherCost = otherCost;
    }
}
