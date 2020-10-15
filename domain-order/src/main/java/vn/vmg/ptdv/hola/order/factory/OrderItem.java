package vn.vmg.ptdv.hola.order.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderItem {
    private Long id;
    private String shortId;
    private String shopName;
    private String shopPhone;
    private Integer totalDistance;
    private Timestamp createAt;
    private String shopAddress;
    private Integer totalOrderDetail;
    private Long totalShipFee;
    private Long totalCod;
    private Long totalProductValue;
    private List<OrderItemCheckPoint> orderItemCheckPoints;
    private List<OrderItemDetail> orderItemDetails;
}
