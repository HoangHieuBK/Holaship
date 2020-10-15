package vn.vmg.ptdv.hola.leadtime.factory;

import lombok.Data;

import java.util.List;

@Data
public class LeadtimeLogList {

    private List<LeadtimeLog> leadtimeLogs;
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;

}
