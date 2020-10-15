package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.mapper.LeadtimeMapper;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeUpdateUC;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public class LeadtimeUpdateUCImpl implements LeadtimeUpdateUC {

    private final LeadtimeRepository leadtimeRepository;

    private Leadtime leadtime;
    private int executedRecord;

    public LeadtimeUpdateUCImpl(LeadtimeRepository leadtimeRepository) {
        this.leadtimeRepository = leadtimeRepository;
    }

    @Override
    public LeadtimeUpdateUC update(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog,
                                   OffsetDateTime effectiveAt) {
        executedRecord = leadtimeRepository.update(leadtimeId, name, status, note, auditLog, effectiveAt);
        return this;
    }

    @Override
    public LeadtimeUpdateUC changeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog) {
        executedRecord = leadtimeRepository.changeStatus(leadtimeId, status, auditLog);
        leadtime = new Leadtime();
        leadtime.setLeadtimeId(leadtimeId);
        leadtime.setStatus(status);
        leadtime.setAuditLog(auditLog);
        return this;
    }

    @Override
    public LeadtimeUpdateUC fail() throws EntityUpdateException {
        if (executedRecord == 0) {
            throw new EntityUpdateException("Cập nhật leadtime không thành công");
        }
        return this;
    }

    @Override
    public LeadtimeResponse endUpdate(LeadtimeId leadtimeId, String name, Integer status, String note, AuditLog auditLog, OffsetDateTime effectiveAt) {
        LeadtimeResponse response = LeadtimeMapper.getInstance().fromLeadtime(leadtime);
        return response;
    }

    @Override
    public LeadtimeResponse endChangeStatus(LeadtimeId leadtimeId, Integer status, AuditLog auditLog) {
        LeadtimeResponse response = LeadtimeMapper.getInstance().fromLeadtime(leadtime);
        return response;
    }

}
