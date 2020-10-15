package vn.vmg.ptdv.hola.order.core;

import lombok.Data;

@Data
public class OrderId {
    private long id;
    private long qrCode;
    private long shipId;
    public OrderId(){

    }
    public OrderId(long id, long qrCode){
        this.id = id;
        this.qrCode = qrCode;
    }
}
