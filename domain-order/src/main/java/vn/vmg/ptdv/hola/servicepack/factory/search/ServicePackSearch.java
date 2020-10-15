package vn.vmg.ptdv.hola.servicepack.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

@Data
public class ServicePackSearch {

    private ServicePackID servicePackID;
    private String name;
    private Integer status;
    private OffsetDateTime effectiveAt;
    private AuditLog createdAtFrom;
    private AuditLog createdAtTo;
    private String globalSearch;
    private String searchSuggest;

    public ServicePackSearch withServicePackID(Long id, String code) {
        servicePackID = new ServicePackID(id, code);
        return this;
    }

}
