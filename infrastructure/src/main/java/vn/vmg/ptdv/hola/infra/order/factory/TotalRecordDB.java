package vn.vmg.ptdv.hola.infra.order.factory;

import lombok.Data;

@Data
public class TotalRecordDB {
    public static final String TOTAL = "TOTAL";
    private Integer totalRecords;
    public TotalRecordDB() {
        totalRecords = 0;
    }
}
