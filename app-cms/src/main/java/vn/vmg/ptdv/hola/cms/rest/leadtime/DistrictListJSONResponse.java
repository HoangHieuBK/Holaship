package vn.vmg.ptdv.hola.cms.rest.leadtime;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.presentation.DistrictResponse;

import java.util.List;

@Data
public class DistrictListJSONResponse {

    List<DistrictResponse> districts;

}
