package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
public class GroupManagerDetailJSONRequest {
    private String groundName;
    private Long priceList;
    private Integer status;
    private Long singleQuantity;// so luong dơn
    private Long freightFee;// cuoc phi
    private Long massProduct;// khoi luong hang hoa
    private Integer route;// tuyen giao %
    private Integer valueRoute; // chon tuyen
    private Integer groundSort;
    private String note;
    private Instant uTimestamp;

    public GroupManagerDetailJSONRequest() {
    }

    public GroupManagerDetailJSONRequest(String groundName, Long priceList, Integer status, Long singleQuantity,
            Long freightFee, Long massProduct, Integer route, Integer valueRoute, Integer groundSort, String note,
            @JsonInclude(JsonInclude.Include.NON_NULL) Instant uTimestamp) {
        this.groundName = groundName;
        this.priceList = priceList;
        this.status = status;
        this.singleQuantity = singleQuantity;
        this.freightFee = freightFee;
        this.massProduct = massProduct;
        this.route = route;
        this.valueRoute = valueRoute;
        this.groundSort = groundSort;
        this.note = note;
        this.uTimestamp = uTimestamp;
    }
}
