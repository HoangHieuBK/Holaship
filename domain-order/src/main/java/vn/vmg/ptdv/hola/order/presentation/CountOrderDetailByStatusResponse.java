package vn.vmg.ptdv.hola.order.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.order.factory.CountOrderDetailByStatus;

import java.util.List;

@Data
public class CountOrderDetailByStatusResponse {
    List<CountOrderDetailByStatus> countOrderDetailByStatusList;
}
