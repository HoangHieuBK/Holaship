package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.leadtime.factory.TotalRecordDB;
import vn.vmg.ptdv.hola.infra.leadtime.mapper.LeadtimeDBMapper;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeadtimeRepositoryImpl implements LeadtimeRepository {

    private final LeadtimeJDBC jdbc;

    public LeadtimeRepositoryImpl(LeadtimeJDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Leadtime> findAll(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {
        List<LeadtimeDB> leadtimeDBs = jdbc.findAll(leadtimeSearch, pagingSortFilter);
        List<Leadtime> result = new ArrayList<>();
        if (!leadtimeDBs.isEmpty()) {
            for (LeadtimeDB leadtimeDB : leadtimeDBs
            ) {
                result.add(LeadtimeDBMapper.getInstance().fromDB(leadtimeDB));
            }
        }
        return result;
    }

    @Override
    public List<Leadtime> findBySuggest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {

        List<LeadtimeDB> leadtimeDBs = jdbc.getDataSuggest(leadtimeSearch, pagingSortFilter);
        List<Leadtime> result = new ArrayList<>();
        if (!leadtimeDBs.isEmpty()) {
            for (LeadtimeDB leadtimeDB : leadtimeDBs
            ) {
                result.add(LeadtimeDBMapper.getInstance().fromDB(leadtimeDB));
            }
        }
        return result;
    }

    @Override
    public Leadtime findById(Long id) {
        LeadtimeDB dbRs = jdbc.findById(id);
        if (dbRs == null) {
            return null;
        }
        LeadtimeId leadtimeId = new LeadtimeId(dbRs.getId(), dbRs.getCode());
        AccountAdminId createdBy = new AccountAdminId(dbRs.getCreatedBy());
        AccountAdminId updatedBy = new AccountAdminId(dbRs.getUpdatedBy());
        AuditLog auditLog = new AuditLog()
                .withCreatedAt(dbRs.getCreatedAt())
                .withUpdatedAt(dbRs.getUpdatedAt())
                .withCreatedBy(createdBy)
                .withUpdatedBy(updatedBy)
                .withUTimestamp(dbRs.getUTimestamp());

        Leadtime rs = new Leadtime();
        rs.setLeadtimeId(leadtimeId);
        rs.setName(dbRs.getName());
        rs.setStatus(dbRs.getStatus());
        rs.setNote(dbRs.getNote());
        rs.setEffectiveAt(dbRs.getEffectiveAt());
        rs.setAuditLog(auditLog);

        return rs;
    }

    @Override
    public Integer countTotal(LeadtimeSearch leadtimeSearch) {
        TotalRecordDB totalRecordDB = jdbc.getTotal(leadtimeSearch);
        return totalRecordDB.getTotalRecords();
    }

    @Override
    public int create(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                      OffsetDateTime effectiveAt) {
        LeadtimeDB leadtimeDB = new LeadtimeDB();
        leadtimeDB.setCode(leadtimeId.getCode());
        leadtimeDB.setName(name);
        leadtimeDB.setStatus(status);
        leadtimeDB.setNote(note);
        leadtimeDB.setCreatedBy(auditLog.getCreatedBy() != null ? auditLog.getCreatedBy().getId() : null);
        leadtimeDB.setEffectiveAt(effectiveAt);

        int executedRecord = jdbc.create(leadtimeDB, leadtimeId);
        return executedRecord;
    }

    @Override
    public int update(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                      OffsetDateTime effectiveAt) {
        LeadtimeDB leadtimeDB = new LeadtimeDB();
        leadtimeDB.setId(leadtimeId.getId());
        leadtimeDB.setCode(leadtimeId.getCode());
        leadtimeDB.setName(name);
        leadtimeDB.setStatus(status);
        leadtimeDB.setNote(note);
        leadtimeDB.setEffectiveAt(effectiveAt);
        leadtimeDB.setUTimestamp(auditLog.getUTimestamp());
        int executedRecord = jdbc.update(leadtimeDB);
        return executedRecord;
    }

    @Override
    public int changeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog) {
        return jdbc.changeStatus(leadtimeId.getId(), status, auditLog.getUTimestamp());
    }

}
