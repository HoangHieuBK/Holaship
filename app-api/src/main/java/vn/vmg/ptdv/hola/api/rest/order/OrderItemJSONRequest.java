package vn.vmg.ptdv.hola.api.rest.order;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class OrderItemJSONRequest {
    private String phoneNumber;
    private String code;
    private String provinceCode;
    private String districtCode;
    private String wardCode;
    private String address;
    private String phoneSender;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private String fieldSort;
    public OrderItemJSONRequest(
            @PathVariable("phoneNumber") String phoneNumber,
            @Nullable @RequestParam("code") String code,
            String provinceCode,
            String districtCode,
            String wardCode,
            String address,
            String phoneSender,
            Integer pageIndex,
            Integer pageSize,
            Boolean asc,
            String fieldSort
    ){
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.provinceCode = provinceCode;
        this.districtCode = districtCode;
        this.wardCode = wardCode;
        this.address = address;
        this.phoneSender = phoneSender;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }

}
