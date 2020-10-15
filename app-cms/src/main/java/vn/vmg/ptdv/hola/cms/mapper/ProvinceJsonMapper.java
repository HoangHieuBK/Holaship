package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.leadtime.ProvinceListJSONResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.ProvinceResponse;
import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;

import java.util.ArrayList;
import java.util.List;

public class ProvinceJsonMapper {

    private static ProvinceJsonMapper INSTANCE;

    private ProvinceJsonMapper() {
    }

    public static ProvinceJsonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProvinceJsonMapper();
        }
        return INSTANCE;
    }

    public ProvinceListJSONResponse fromProvinceListResponse(ProvinceListResponse provinceListResponse) {
        ProvinceListJSONResponse jsonResponse = new ProvinceListJSONResponse();
        if (provinceListResponse != null) {
            List<ProvinceResponse> provinceResponses = new ArrayList<>();
            for (Province province : provinceListResponse.getProvinces()) {
                ProvinceResponse provinceResponse = new ProvinceResponse();
                provinceResponse.setId(province.getProvinceId().getId());
                provinceResponse.setCode(province.getProvinceId().getCode());
                provinceResponse.setName(province.getName());
                provinceResponses.add(provinceResponse);
            }
            jsonResponse.setProvinces(provinceResponses);
        }
        return jsonResponse;
    }

}
