package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class AuditLog {

    private OffsetDateTime created;
    private OffsetDateTime updated;
    private OffsetDateTime deleted;
    private Timestamp uTimestamp;
    private AccountId creator;
    private AccountId updater;
    private AccountId deleter;

    public AuditLog() {
    }

    public AuditLog(Timestamp uTimestamp) {
        this.uTimestamp = uTimestamp;
    }

}
