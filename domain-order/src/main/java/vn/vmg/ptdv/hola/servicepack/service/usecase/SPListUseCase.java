package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;
import vn.vmg.ptdv.hola.servicepack.presentation.SPListResponse;

public interface SPListUseCase {
    SPListUseCase withSearch(SPListSearch search);

    SPListUseCase withPagingAndSort(SPPagingAndSort pagingAndSort);

    SPListUseCase search();

    SPListResponse end();
}
