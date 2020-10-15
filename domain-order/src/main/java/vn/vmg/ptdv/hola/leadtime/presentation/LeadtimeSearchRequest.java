package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.exception.OrderError;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.validator.LeadtimeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class LeadtimeSearchRequest {

    private LeadtimeSearch leadtimeSearch;
    private PagingSortFilter pagingSortFilter;

    public LeadtimeSearchRequest() {

    }

    public LeadtimeSearchRequest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) throws OrderDomainException {
        this.leadtimeSearch = leadtimeSearch;
        this.pagingSortFilter = pagingSortFilter;
//        validate();
    }

    private void validate() throws OrderDomainException {
        List<OrderError> errors = new ArrayList<>();
        errors.addAll(LeadtimeValidator.validateName(leadtimeSearch.getName())
                .stream().map(vr -> new OrderError(vr.code, "NAME", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new OrderDomainException(errors);
        }

    }
}
