package vn.vmg.ptdv.hola.province.service.usecase;

import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;

public interface ProvinceGetUseCase {

    ProvinceGetUseCase getAll(ProvinceSearch provinceSearch);

    ProvinceListResponse endGetAll();

}
