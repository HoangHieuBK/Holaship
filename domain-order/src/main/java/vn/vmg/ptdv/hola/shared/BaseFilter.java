package vn.vmg.ptdv.hola.shared;

import lombok.Data;

import java.util.List;

@Data
public class BaseFilter {
    private Integer pageIndex;
    private Integer pageSize;
    private boolean asc;
    private List<String> fieldSort;
}
