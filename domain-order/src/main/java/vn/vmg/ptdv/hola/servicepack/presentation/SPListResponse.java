package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;

import java.util.List;

@Data
public class SPListResponse {
    private SPPagingAndSort pagingAndSort;
    private List<ServicePack> servicePackList;
}
