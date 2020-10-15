package vn.vmg.ptdv.hola.cms.rest.leadtime;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.presentation.ProvinceResponse;

import java.util.List;

@Data
public class ProvinceListJSONResponse {

    List<ProvinceResponse> provinces;

}
