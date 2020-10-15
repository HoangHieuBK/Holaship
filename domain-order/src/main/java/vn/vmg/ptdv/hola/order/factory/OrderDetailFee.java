package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

@Data
public class OrderDetailFee {
    private Long shipBaseFee;
    private Long surchargeFee;
    private Long refundFee;
    private Long porterFee;
    private Long doorDeliveryFee;
    private Long declareProductFee;
    private Long otherFee;
    private Long shipFee;
    private Long amount;
    private Long shipNet;
    private Long holaNet;
    private Long cod;
    private Long holdShop;
    private Long holdShip;
}
