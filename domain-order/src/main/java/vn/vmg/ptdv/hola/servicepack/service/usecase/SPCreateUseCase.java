package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.presentation.SPUpdateResponse;

public interface SPCreateUseCase {

    SPCreateUseCase create(ServicePackInfo servicePackInfo, ServicePackID servicePackId) throws OrderDomainException;

    SPUpdateResponse end();

}
