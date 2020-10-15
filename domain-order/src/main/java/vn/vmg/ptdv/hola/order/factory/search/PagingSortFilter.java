package vn.vmg.ptdv.hola.order.factory.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingSortFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public PagingSortFilter() {

    }

    public PagingSortFilter(Integer pageIndex, Integer pageSize, Boolean asc, List<String> fieldSort) {
        this.pageIndex = pageIndex == null ? 0 : pageIndex;
        this.pageSize = pageSize == null ? 20 : pageSize;
        this.asc = asc == null ? true : asc;
        this.fieldSort = fieldSort == null || fieldSort.isEmpty() ? new ArrayList<>() : fieldSort;
    }
}
