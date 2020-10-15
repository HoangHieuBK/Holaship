package vn.vmg.ptdv.hola.cms.rest.leadtime;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class LeadtimeListJSONResponse extends OrderPagingAndSort {

    List<LeadtimeResponse> leadtimes;

}
