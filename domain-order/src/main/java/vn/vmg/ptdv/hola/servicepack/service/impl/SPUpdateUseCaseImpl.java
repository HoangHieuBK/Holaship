package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.servicepack.core.Destination;
import vn.vmg.ptdv.hola.servicepack.core.Distance;
import vn.vmg.ptdv.hola.servicepack.core.OrderDetailSetting;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.presentation.SPUpdateResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.SPUpdateUseCase;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Money;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.time.LocalDate;

public class SPUpdateUseCaseImpl implements SPUpdateUseCase {
    private final ServicePackRepository servicePackRepository;
    private ServicePack servicePack;
    private ServicePack currentServicePack;
    private AuditLog auditLog;
    private boolean isEffective = false;
    private ServicePackID servicePackID;
    private int executedRecord;

    public SPUpdateUseCaseImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
        this.servicePack = new ServicePack();
    }

    @Override
    public SPUpdateUseCase applyIdentity(ServicePackID servicePackID, AuditLog auditLog) throws
            EntityNotFoundException {
        currentServicePack = servicePackRepository.findByID(servicePackID, auditLog);
        if (currentServicePack == null) {
            throw new EntityNotFoundException(
                    "Cannot found entity Service pack with ID " + servicePackID.getId() + " & uTimestamp: " +
                            auditLog.getUTimestamp().toInstant().toString());
        }
        servicePack.setServicePackID(servicePackID);
        servicePack.setAuditLog(auditLog);

//        isEffective = currentServicePack.getState() == OrderDomainState.ACTIVE;
        return this;
    }

    @Override
    public SPUpdateUseCase applyBasicInfo(String name, String description, Integer deliveryTimeMax,
            LocalDate effectiveDate, Money priceBase, OrderDomainState state) {
        servicePack.setName(name);
//        servicePack.setDescription(description);
//        servicePack.setDeliveryTimeMax(deliveryTimeMax);
//        servicePack.setEffectiveDate(effectiveDate);
//        servicePack.setPriceBase(priceBase);
//        servicePack.setState(state);
        return this;
    }

    @Override
    public SPUpdateUseCase applyDistance(Distance distance) {
//        servicePack.setDistance(distance);
        return this;
    }

    @Override
    public SPUpdateUseCase applyDestination(Destination destination) {
//        servicePack.setDestination(destination);
        return this;
    }

    @Override
    public SPUpdateUseCase applyOrderDetail(OrderDetailSetting orderDetailSetting) {
//        servicePack.setOrderDetailSetting(orderDetailSetting);
        return this;
    }

    @Override
    public SPUpdateUseCase applyOtherCost(Money porterage, Money d2dCost, Money refundCost, Money declarePriceCost,
            Money otherCost) {
//        servicePack.setPorterage(porterage);
//        servicePack.setD2dCost(d2dCost);
//        servicePack.setRefundCost(refundCost);
//        servicePack.setDeclarePriceCost(declarePriceCost);
//        servicePack.setOtherCost(otherCost);
        return this;
    }

    @Override
    public SPUpdateUseCase  update() {
        if (isEffective) {
//            currentServicePack.setState(OrderDomainState.DEACTIVATE);
//            auditLog = servicePackRepository.update(currentServicePack);
        } else {
//            auditLog = servicePackRepository.update(servicePack);
        }

        return this;
    }

    @Override
    public SPUpdateUseCase createNewWhenEffective() {
        if (isEffective) {
            servicePack.setServicePackID(new ServicePackID());
            servicePack.setAuditLog(new AuditLog());
            auditLog = servicePackRepository.save(servicePack);
        }
        return this;
    }

    @Override
    public SPUpdateUseCase fail() throws EntityUpdateException {
        if (auditLog.getUTimestamp().compareTo(servicePack.getAuditLog().getUTimestamp()) == 0) {
            throw new EntityUpdateException("Update fail");
        }
        return this;
    }

    @Override
    public SPUpdateResponse end() {
        SPUpdateResponse response = new SPUpdateResponse();
        response.setAuditLog(auditLog);
        response.setSuccess(true);
        return response;
    }

    @Override
    public SPUpdateUseCase getByIdentity(ServicePackID servicePackID, AuditLog auditLog) {
        this.servicePackID = servicePackID;
        this.auditLog = auditLog;
        currentServicePack = servicePackRepository.findByID(servicePackID, auditLog);
        return this;
    }

    @Override
    public SPUpdateUseCase notFound() throws EntityNotFoundException {
        if (currentServicePack == null) {
            throw new EntityNotFoundException(400,
                    "Cannot found entity with code: " + servicePackID.getCode() + " & timestamp: " +
                            auditLog.getUTimestamp().toInstant().toString());
        }
        return this;
    }

    @Override
    public SPUpdateResponse endGetInfo() {
        SPUpdateResponse response = new SPUpdateResponse();
        response.setServicePack(currentServicePack);
        response.setAuditLog(currentServicePack.getAuditLog());
        response.setSuccess(true);
        return response;
    }

    @Override
    public SPUpdateUseCase activeOrDeactive(ServicePackID servicePackID, OrderDomainState state, AuditLog auditLog) throws OrderDomainException {
        executedRecord = servicePackRepository.activeOrDeactive(servicePackID, state, auditLog);
        return this;
    }

}
