package vn.vmg.ptdv.hola.infra.leadtime.mapper;

import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

public class LeadtimeDBMapper {

    private static LeadtimeDBMapper INSTANCE;

    private LeadtimeDBMapper() {

    }

    public static LeadtimeDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeDBMapper();
        }
        return INSTANCE;
    }

    public Leadtime fromDB(LeadtimeDB leadtimeDB) {
        Leadtime leadtime = new Leadtime();
        LeadtimeId leadtimeId = new LeadtimeId(leadtimeDB.getId(), leadtimeDB.getCode());
        AuditLog auditLog = new AuditLog()
                .withCreatedAt(leadtimeDB.getCreatedAt())
                .withCreatedBy(new AccountAdminId(leadtimeDB.getCreatedBy()))
                .withUTimestamp(leadtimeDB.getUTimestamp());
        leadtime.setLeadtimeId(leadtimeId);
        leadtime.setName(leadtimeDB.getName());
        leadtime.setStatus(leadtimeDB.getStatus());
        leadtime.setEffectiveAt(leadtimeDB.getEffectiveAt());
        leadtime.setAuditLog(auditLog);
        return leadtime;
    }
    
}
