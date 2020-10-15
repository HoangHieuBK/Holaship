package vn.vmg.ptdv.hola.groupmanager.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.exception.OrderError;
import vn.vmg.ptdv.hola.groupmanager.factory.search.GroupManagerSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupManagerRequest {
    private GroupManagerSearch groupManagerSearch;
    private PagingSortFilter pagingSortFilter;

    public GroupManagerRequest() {
    }

    public GroupManagerRequest(GroupManagerSearch groupManagerSearch,
            PagingSortFilter pagingSortFilter) throws OrderDomainException {
        this.groupManagerSearch = groupManagerSearch;
        this.pagingSortFilter = pagingSortFilter;
        validate();
    }

    private void validate() throws OrderDomainException {
        List<OrderError> errors = new ArrayList<>();
        if (!errors.isEmpty()) {
            throw new OrderDomainException(errors);
        }
        // TODO validate date time
    }
}
