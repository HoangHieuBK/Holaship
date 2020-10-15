package vn.vmg.ptdv.hola.cms.rest.leadtimelog;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLog;

import java.util.List;

@Data
public class LeadtimeLogListJSONResponse {

    List<LeadtimeLog> leadtimeLogs;
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;

}
