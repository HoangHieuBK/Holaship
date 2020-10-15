package vn.vmg.ptdv.hola.servicepack.service.usecase;

import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.servicepack.core.Destination;
import vn.vmg.ptdv.hola.servicepack.core.Distance;
import vn.vmg.ptdv.hola.servicepack.core.OrderDetailSetting;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.presentation.SPUpdateResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Money;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.time.LocalDate;

public interface SPUpdateUseCase {
    SPUpdateUseCase applyIdentity(ServicePackID servicePackID, AuditLog auditLog) throws EntityNotFoundException;

    SPUpdateUseCase applyBasicInfo(String name, String description, Integer deliveryTimeMax, LocalDate effectiveDate,
            Money priceBase, OrderDomainState state);

    SPUpdateUseCase applyDistance(Distance distance);

    SPUpdateUseCase applyDestination(Destination destination);

    SPUpdateUseCase applyOrderDetail(OrderDetailSetting orderDetailSetting);

    SPUpdateUseCase applyOtherCost(Money porterage, Money d2dCost, Money refundCost, Money declarePriceCost,
            Money otherCost);

    SPUpdateUseCase update();

    SPUpdateUseCase createNewWhenEffective();

    SPUpdateUseCase fail() throws EntityUpdateException;

    SPUpdateResponse end();

    SPUpdateUseCase getByIdentity(ServicePackID servicePackID, AuditLog auditLog);

    SPUpdateUseCase notFound() throws EntityNotFoundException;

    SPUpdateResponse endGetInfo();

    SPUpdateUseCase activeOrDeactive(ServicePackID servicePackID, OrderDomainState state, AuditLog auditLog) throws OrderDomainException;
}
