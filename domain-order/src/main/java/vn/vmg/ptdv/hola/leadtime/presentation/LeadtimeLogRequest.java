package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;


@Data
public class LeadtimeLogRequest {

    private LeadtimeLogSearch leadtimeLogSearch;
    private PagingSortFilter pagingSortFilter;

    public LeadtimeLogRequest() {

    }

}
