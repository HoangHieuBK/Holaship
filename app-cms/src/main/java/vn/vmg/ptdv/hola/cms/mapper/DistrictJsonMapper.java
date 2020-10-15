package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.leadtime.DistrictListJSONResponse;
import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.DistrictResponse;

import java.util.ArrayList;
import java.util.List;

public class DistrictJsonMapper {

    private static DistrictJsonMapper INSTANCE;

    private DistrictJsonMapper() {
    }

    public static DistrictJsonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DistrictJsonMapper();
        }
        return INSTANCE;
    }

    public DistrictListJSONResponse fromDistrictListResponse(DistrictListResponse districtListResponse) {
        DistrictListJSONResponse jsonResponse = new DistrictListJSONResponse();
        if (districtListResponse != null) {
            List<DistrictResponse> districtResponses = new ArrayList<>();
            for (District district : districtListResponse.getDistricts()) {
                DistrictResponse districtResponse = new DistrictResponse();
                districtResponse.setId(district.getDistrictId().getId());
                districtResponse.setCode(district.getDistrictId().getCode());
                districtResponse.setName(district.getName());
                districtResponse.setProvinceId(district.getProvinceId().getId());
                districtResponses.add(districtResponse);
            }
            jsonResponse.setDistricts(districtResponses);
        }
        return jsonResponse;
    }

}
