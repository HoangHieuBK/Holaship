package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DetailOrderDetail extends OrderDetail {
    private String productCategoryName;
    private ZonedDateTime expectPickDate;
    private ZonedDateTime expectDeliverDate;
    private Integer isFree;
    private Integer isPartDelivery;
    private Integer isRefund;
    private Integer requiredNote;
    private String shipImgConfirm;
}
