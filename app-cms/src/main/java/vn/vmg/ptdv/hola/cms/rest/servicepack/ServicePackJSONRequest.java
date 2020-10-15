package vn.vmg.ptdv.hola.cms.rest.servicepack;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class ServicePackJSONRequest {

    private Long id;
    private String name;
    private String code;
    private Instant effectiveAt;
    private Instant createAt;
    private Long createdBy;
//    private Instant createdAtFrom;
//    private Instant createdAtTo;
    private Boolean state;
    private Integer status;
    private String note;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant uTimestamp;


//    private Long id;
//    private String name;
//    private String code;
//    private String note;
//    private Integer active;
//    private Integer status;
//    private Integer maxTime;
//    private Integer maxDistance;
//    private Integer maxPoint;
//    private Integer maxOrder;
//    private Integer baseDistance;
//    private Integer basePoint;
//    private Integer baseCost;
//    private Integer baseOrderDetail;
//    private Long surchargeDistance;
//    private Long surchargePoint;
//    private Long surchargeOrderDetail;
//    private Long porterFee;
//    private Long doorDeliveryFee;
//    private Long refundFee;
//    private Long priceDeclareFee;
//    private Integer type;
//    private Long servicePackId;
//    private Long otherCost;
//
//    public ServicePackJSONRequest() {
//    }
//
//    public ServicePackJSONRequest(@Nullable @RequestParam String name,
//            String code,
//            String note,
//            Integer active,
//            Integer status,
//            Integer maxTime,
//            Integer maxDistance,
//            Integer maxPoint,
//            Integer maxOrder,
//            Integer baseDistance,
//            Integer basePoint,
//            Integer baseCost,
//            Integer baseOrderDetail,
//            Long surchargeDistance,
//            Long surchargePoint,
//            Long surchargeOrderDetail,
//            Long porterFee,
//            Long doorDeliveryFee,
//            Long refundFee,
//            Long priceDeclareFee,
//            Integer type,
//            Long createdBy,
//            LocalDate createdAt,
//            Instant uTimestamp,
//            @Nullable @RequestParam LocalDate effectiveAt) {
//        this.id = id;
//        this.name = name;
//        this.code = code;
//        this.note = note;
//        this.active = active;
//        this.status = status;
//        this.maxTime = maxTime;
//        this.maxDistance = maxDistance;
//        this.maxPoint = maxPoint;
//        this.maxOrder = maxOrder;
//        this.baseDistance = baseDistance;
//        this.basePoint = basePoint;
//        this.baseCost = baseCost;
//        this.baseOrderDetail = baseOrderDetail;
//        this.surchargeDistance = surchargeDistance;
//        this.surchargePoint = surchargePoint;
//        this.surchargeOrderDetail = surchargeOrderDetail;
//        this.porterFee = porterFee;
//        this.doorDeliveryFee = doorDeliveryFee;
//        this.refundFee = refundFee;
//        this.priceDeclareFee = priceDeclareFee;
//        this.type = type;
//        this.createdBy = createdBy;
//        this.createdAt = createdAt;
//        this.servicePackId = servicePackId;
//        this.otherCost = otherCost;
//        this.effectiveAt = effectiveAt;
//        this.uTimestamp = uTimestamp;
//    }
}
