package vn.vmg.ptdv.hola.servicepack.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.shared.OrderPagingAndSort;

import java.util.List;

@Data
public class ServicePackListResponse extends OrderPagingAndSort {

    List<ServicePack> servicePacks;

    public ServicePackListResponse() {
    }

}
