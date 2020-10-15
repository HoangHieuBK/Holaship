package vn.vmg.ptdv.hola.infra.leadtime.mapper;

import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeLogDB;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLog;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class LeadtimeLogDBMapper {

    private static LeadtimeLogDBMapper INSTANCE;

    private LeadtimeLogDBMapper() {

    }

    public static LeadtimeLogDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeLogDBMapper();
        }
        return INSTANCE;
    }

    public LeadtimeLog fromDB(LeadtimeLogDB objDB) {
        LeadtimeId leadtimeId = new LeadtimeId(objDB.getId(), null);
        AuditLog auditLog = new AuditLog()
                .withCreatedAt(OffsetDateTime.of(objDB.getCreatedAt(), LocalTime.NOON, ZoneOffset.UTC))
                .withCreatedBy(new AccountAdminId(objDB.getCreatedBy()));

        LeadtimeLog leadtimeLog = new LeadtimeLog();
        leadtimeLog.setLeadtimeId(leadtimeId);
        leadtimeLog.setDataUpdated(objDB.getDataUpdated());
        leadtimeLog.setAuditLog(auditLog);
        return leadtimeLog;
    }
    
}
