package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.exception.OrderError;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.validator.LeadtimeValidator;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class LeadtimeRequest {

    private LeadtimeId leadTimeId;
    private String name;
    private Integer status;
    private String note;
    private AuditLog auditLog;
    private OffsetDateTime effectiveAt;

    public LeadtimeRequest() {

    }

    public LeadtimeRequest(String code, String name, Long createdBy, Integer status, String note, Instant effectiveAt) throws
            OrderDomainException {
        this.leadTimeId = new LeadtimeId(null, code);
        this.name = name;
        this.status = status;
        this.note = note;
        this.effectiveAt = OffsetDateTime.ofInstant(effectiveAt, ZoneId.systemDefault());
        this.auditLog = new AuditLog().withCreatedBy(new AccountAdminId(createdBy));
        validate();
    }

    private void validate() throws OrderDomainException {
        List<OrderError> errors = new ArrayList<>();
        errors.addAll(LeadtimeValidator.validateName(getName())
                .stream().map(error -> new OrderError(error.code, "NAME", error.message))
                .collect(Collectors.toList()));
//        errors.addAll(LeadtimeValidator.validateDatetime(getAuditLog())
//                .stream().map(error -> new OrderError(error.code, "NAME", error.message))
//                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new OrderDomainException(errors);
        }
// TODO validate date time
    }

}
