package vn.vmg.ptdv.hola.province.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class ProvinceListResponse extends OrderPagingAndSort {

    List<Province> provinces;
    public ProvinceListResponse() {
    }

    public ProvinceListResponse(List<Province> provinces) {
        this.provinces = provinces;
    }

    public ProvinceListResponse(Integer pageIndex, Integer pageSize, Integer totalCount,
            List<Province> provinces) {
        super(pageIndex, pageSize, totalCount);
        this.provinces = provinces;
    }
}
