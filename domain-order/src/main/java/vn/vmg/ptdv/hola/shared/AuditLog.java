package vn.vmg.ptdv.hola.shared;

import lombok.Data;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Data
public class AuditLog {

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
    private Timestamp uTimestamp;
    private AccountAdminId createdBy;
    private AccountAdminId updatedBy;
    private AccountAdminId deletedBy;

    public AuditLog() {

    }

    public AuditLog withCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AuditLog withUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public AuditLog withDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public AuditLog withCreatedBy(AccountAdminId createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public AuditLog withUpdatedBy(AccountAdminId updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public AuditLog withDeletedBy(AccountAdminId deletedBy) {
        this.deletedBy = deletedBy;
        return this;
    }

    public AuditLog withUTimestamp(Timestamp uTimestamp) {
        this.uTimestamp = uTimestamp;
        return this;
    }

}
