package vn.vmg.ptdv.hola.leadtime.mapper;

import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public class LeadtimeMapper {

    private static LeadtimeMapper INSTANCE;

    private LeadtimeMapper() {
    }

    public static LeadtimeMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeMapper();
        }

        return INSTANCE;
    }

    public LeadtimeResponse fromLeadtime(Leadtime leadtime) {
        LeadtimeResponse response = new LeadtimeResponse();
        if (leadtime != null) {
            LeadtimeId leadtimeId = leadtime.getLeadtimeId();
            OffsetDateTime effectiveAt = leadtime.getEffectiveAt();
            AuditLog auditLog = leadtime.getAuditLog();

            response.setId(leadtimeId != null ? leadtimeId.getId() : null);
            response.setCode(leadtimeId != null ? leadtimeId.getCode() : null);
            response.setName(leadtime.getName());
            response.setStatus(leadtime.getStatus());
            response.setNote(leadtime.getNote());
            response.setEffectiveAt(effectiveAt != null ? effectiveAt.toInstant() : null);
            if (auditLog != null) {
                OffsetDateTime createdAt = auditLog.getCreatedAt();
                AccountAdminId createdBy = auditLog.getCreatedBy();
                OffsetDateTime updatedAt = auditLog.getUpdatedAt();
                AccountAdminId updatedBy = auditLog.getUpdatedBy();
                Timestamp uTimestamp = auditLog.getUTimestamp();

                response.setCreatedAt(createdAt != null ? createdAt.toInstant() : null);
                response.setCreatedBy(createdBy != null ? createdBy.getId() : null);
                response.setUpdatedAt(updatedAt != null ? updatedAt.toInstant() : null);
                response.setUpdatedBy(updatedBy != null ? updatedBy.getId() : null);
                response.setUTimestamp(uTimestamp != null ? uTimestamp.toInstant() : null);
            }
        }
        return response;
    }

}
