package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;

@Data
public class SPListRequest {
    private SPPagingAndSort pagingAndSort;
    private SPListSearch search;

    public SPListRequest() {
    }

    public SPListRequest(SPPagingAndSort pagingAndSort, SPListSearch search) {
        this.pagingAndSort = pagingAndSort;
        this.search = search;
    }
}
