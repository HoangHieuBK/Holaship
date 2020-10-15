package vn.vmg.ptdv.hola.cms.rest.servicepack;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class ServicePackListJSONResponse extends OrderPagingAndSort {

    List<ServicePackResponse> servicePacks;

}
