package vn.vmg.ptdv.hola.leadtime.repository;

import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLog;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import java.util.List;

public interface LeadtimeLogRepository {

    List<LeadtimeLog> findAll(LeadtimeLogSearch leadtimeLogSearch, PagingSortFilter pagingSortFilter);

}
