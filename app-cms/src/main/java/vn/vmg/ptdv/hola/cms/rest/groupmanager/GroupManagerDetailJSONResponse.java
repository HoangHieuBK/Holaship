package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import lombok.Data;

import java.time.Instant;

@Data
public class GroupManagerDetailJSONResponse {
    private String groundCode;
    private String groundName;
    private Integer priceList;
    private Integer status;
    private Long singleQuantity;// so luong dơn
    private Long freightFee;// cuoc phi
    private Long massProduct;// khoi luong hang hoa
    private Long valueRoute;
    private Long route;// tuyen duong
    private Integer groundSort;
    private String note;
    private Instant uTimestamp;
}
