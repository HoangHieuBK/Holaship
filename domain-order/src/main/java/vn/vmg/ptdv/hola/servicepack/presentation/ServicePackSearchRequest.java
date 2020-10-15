package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;


@Data
public class ServicePackSearchRequest {

    private ServicePackSearch servicePackSearch;
    private PagingSortFilter pagingSortFilter;

    public ServicePackSearchRequest() {

    }

    public ServicePackSearchRequest(ServicePackSearch servicePackSearch, PagingSortFilter pagingSortFilter) {
        this.servicePackSearch = servicePackSearch;
        this.pagingSortFilter = pagingSortFilter;
//        validate();
    }

//    private void validate() throws OrderDomainException {
//        List<OrderError> errors = new ArrayList<>();
//        errors.addAll(LeadtimeValidator.validateName(servicePackSearch.getName())
//                .stream().map(vr -> new OrderError(vr.code, "NAME", vr.message))
//                .collect(Collectors.toList()));
//        if (!errors.isEmpty()) {
//            throw new OrderDomainException(errors);
//        }
//
//    }

}
