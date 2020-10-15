package vn.vmg.ptdv.hola.leadtime.repository;

import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;
import java.util.List;

public interface LeadtimeRepository {

    List<Leadtime> findAll(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter);

    List<Leadtime> findBySuggest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter);

    Leadtime findById(Long id);

    Integer countTotal(LeadtimeSearch leadtimeSearch);

    int create(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
            OffsetDateTime effectiveAt);

    int update(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
               OffsetDateTime effectiveAt);

    int changeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog);

}
