package vn.vmg.ptdv.hola.shared;

import lombok.Data;

@Data
public class OrderPagingAndSort {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalCount;

    public OrderPagingAndSort() {
    }

    public OrderPagingAndSort(Integer pageIndex, Integer pageSize, Integer totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
}
