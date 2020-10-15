package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackHistoryId;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistoryServicePackRequest {

    private ServicePackHistoryId servicePackHistoryId;
    private LocalDate effectiveAt;
    private PagingSortFilter pagingSortFilter;

    public HistoryServicePackRequest(Long id, LocalDate effectiveAt, Integer pageIndex, Integer pageSize, Boolean asc,
            List<String> fieldSort) throws OrderDomainException {
        this.servicePackHistoryId = new ServicePackHistoryId(id);
        this.effectiveAt = effectiveAt;
        this.pagingSortFilter = new PagingSortFilter(pageIndex, pageSize, asc, fieldSort);
    }

}
