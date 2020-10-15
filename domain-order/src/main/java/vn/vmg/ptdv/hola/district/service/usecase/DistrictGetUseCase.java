package vn.vmg.ptdv.hola.district.service.usecase;

import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;

public interface DistrictGetUseCase {

    DistrictGetUseCase getAll(DistrictSearch districtSearch);

    DistrictListResponse endGetAll();

}
