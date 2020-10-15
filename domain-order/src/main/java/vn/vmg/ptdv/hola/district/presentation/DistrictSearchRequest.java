package vn.vmg.ptdv.hola.district.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;


@Data
public class DistrictSearchRequest {

    private DistrictSearch districtSearch;

    public DistrictSearchRequest() {

    }

    public DistrictSearchRequest(DistrictSearch districtSearch) {
        this.districtSearch = districtSearch;
    }

}
