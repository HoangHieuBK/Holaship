package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;

public interface LeadtimeGetUC {

    LeadtimeGetUC getAll(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter);

    LeadtimeGetUC getProvinces(ProvinceSearch provinceSearch);

    LeadtimeGetUC getDistricts(DistrictSearch districtSearch);

    LeadtimeGetUC getBySuggest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter);

    LeadtimeGetUC getById(LeadtimeId leadtimeId);

    LeadtimeGetUC leadtimeNotFound() throws EntityNotFoundException;

    LeadtimeListResponse endGetAll();

    ProvinceListResponse endGetProvinces();

    DistrictListResponse endGetDistricts();

    LeadtimeListResponse endGetBySuggest();

    LeadtimeResponse endGetById();

}
