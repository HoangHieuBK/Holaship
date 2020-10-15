package vn.vmg.ptdv.hola.leadtime.service.usecase;

import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogListResponse;

public interface LeadtimeLogUC {

    LeadtimeLogUC getAll(LeadtimeLogSearch leadtimeLogSearch, PagingSortFilter pagingSortFilter);

    LeadtimeLogListResponse endGetAll();

}
