package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackListResponse;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;

public interface ServicePackGetUC {

    ServicePackGetUC applySearch(ServicePackSearch servicePackSearch);

    ServicePackGetUC applyPagingSortFilter(PagingSortFilter pagingSortFilter);

    ServicePackGetUC applyServicePackID(ServicePackID servicePackID);

    ServicePackGetUC getAll();

    ServicePackGetUC getByCode();

    ServicePackGetUC notFound() throws EntityNotFoundException;

    ServicePackListResponse endGetAll();

    ServicePackResponse endGetByCode();

}
