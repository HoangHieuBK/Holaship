package vn.vmg.ptdv.hola.cms.rest.servicepack;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.presentation.SPUpdateResponse;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class SPUpdateJSON {
    private Long id;
    private String spCode;
    private Instant uTimestamp;
    //basic info
    private String spName;
    private Integer deliveryTimeMax;
    private Double priceBase;
    private LocalDate effectiveDate;
    private Boolean effectiveStatus;
    private String description;

    //distance
    private Integer distanceMax;
    private Integer distanceBase;
    private Double distanceCost;

    //destination
    private Integer destinationMax;
    private Integer destinationBase;
    private Double destinationCost;

    //order detail
    private Integer detailOrderMax;
    private Integer detailOrderBase;
    private Double detailOrderCost;

    //other
    private Double porterage;
    private Double d2dCost;
    private Double refundCost;
    private Double declarePriceCost;
    private Double otherCost;

    public SPUpdateJSON(SPUpdateResponse response) {
        ServicePack sp = response.getServicePack();
        if(sp != null){
            this.id = sp.getServicePackID().getId();
            this.spCode = sp.getServicePackID().getCode();
            this.spName = sp.getName();
//            this.deliveryTimeMax = sp.getDeliveryTimeMax();
//            this.priceBase = sp.getPriceBase().getValue();
//            this.effectiveDate = sp.getEffectiveDate();
//            this.effectiveStatus = sp.getState().toBoolean();
//            this.description = sp.getDescription();
//            this.distanceMax = sp.getDistance().getMaxValue();
//            this.distanceBase = sp.getDistance().getBaseValue();
//            this.distanceCost = sp.getDistance().getCost().getValue();
//            this.destinationBase = sp.getDestination().getBaseValue();
//            this.destinationCost = sp.getDestination().getCost().getValue();
//            this.destinationMax = sp.getDestination().getMaxValue();
//            this.detailOrderBase = sp.getOrderDetailSetting().getBaseValue();
//            this.detailOrderCost = sp.getOrderDetailSetting().getCost().getValue();
//            this.detailOrderMax = sp.getOrderDetailSetting().getMaxValue();
//            this.porterage = sp.getPorterage().getValue();
//            this.d2dCost = sp.getD2dCost().getValue();
//            this.refundCost = sp.getRefundCost().getValue();
//            this.declarePriceCost = sp.getDeclarePriceCost().getValue();
//            this.otherCost = sp.getOtherCost().getValue();
            this.uTimestamp = sp.getAuditLog().getUTimestamp().toInstant();
        }

    }
}
