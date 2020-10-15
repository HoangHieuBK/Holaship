package vn.vmg.ptdv.hola.servicepack.factory.search;

import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

public class SPPagingAndSort extends OrderPagingAndSort {
    public SPPagingAndSort() {
    }

    public SPPagingAndSort(Integer pageIndex, Integer pageSize, Integer totalCount) {
        super(pageIndex, pageSize, totalCount);
    }
}
