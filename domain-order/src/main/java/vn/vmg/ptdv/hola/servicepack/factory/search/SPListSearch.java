package vn.vmg.ptdv.hola.servicepack.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.shared.OrderDomainState;
import vn.vmg.ptdv.hola.shared.OrderSearch;

import java.time.OffsetDateTime;

@Data
public class SPListSearch extends OrderSearch {
    private ServicePackID servicePackID;
    private String name;
    private OrderDomainState state;
    private OffsetDateTime effectiveDate;
    private OffsetDateTime createdFrom;
    private OffsetDateTime createdTo;
    private Integer status;
}
