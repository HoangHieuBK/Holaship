package vn.vmg.ptdv.hola.district.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class DistrictListResponse extends OrderPagingAndSort {

    List<District> districts;
    public DistrictListResponse() {
    }

    public DistrictListResponse(List<District> districts) {
        this.districts = districts;
    }

    public DistrictListResponse(Integer pageIndex, Integer pageSize, Integer totalCount,
            List<District> districts) {
        super(pageIndex, pageSize, totalCount);
        this.districts = districts;
    }
}
