package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeCreateUC;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public class LeadtimeCreateUCImpl implements LeadtimeCreateUC {

    private final LeadtimeRepository leadtimeRepository;

    private int executedRecord;

    public LeadtimeCreateUCImpl(LeadtimeRepository leadtimeRepository) {
        this.leadtimeRepository = leadtimeRepository;
    }

    @Override
    public LeadtimeCreateUC create(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                                   OffsetDateTime effectiveAt) {
        executedRecord = leadtimeRepository.create(leadtimeId, name, status, note, auditLog, effectiveAt);
        return this;
    }

    @Override
    public LeadtimeCreateUC fail() throws EntityInsertException {
        if (executedRecord == 0) {
            throw new EntityInsertException("Thêm mới leadtime không thành công");
        }
        return this;
    }

    @Override
    public LeadtimeResponse end(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                                OffsetDateTime effectiveAt) {
        LeadtimeResponse response = new LeadtimeResponse();
        response.setId(leadtimeId.getId());
        response.setCode(leadtimeId.getCode());
        response.setName(name);
        response.setStatus(status);
        response.setNote(note);
        response.setEffectiveAt(effectiveAt.toInstant());
        response.setCreatedAt(auditLog.getCreatedAt() != null ? auditLog.getCreatedAt().toInstant() : null);
        response.setCreatedBy(auditLog.getCreatedBy() != null ? auditLog.getCreatedBy().getId() : null);
        response.setUpdatedAt(auditLog.getUpdatedAt() != null ? auditLog.getUpdatedAt().toInstant() : null);
        response.setUpdatedBy(auditLog.getUpdatedBy() != null ? auditLog.getUpdatedBy().getId() : null);
        response.setUTimestamp(auditLog.getUTimestamp() != null ? auditLog.getUTimestamp().toInstant() : null);
        return response;
    }

}
