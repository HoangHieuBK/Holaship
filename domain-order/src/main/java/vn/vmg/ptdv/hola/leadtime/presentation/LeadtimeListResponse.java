package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class LeadtimeListResponse extends OrderPagingAndSort {

    List<Leadtime> leadtimes;

    public LeadtimeListResponse() {
    }

}
