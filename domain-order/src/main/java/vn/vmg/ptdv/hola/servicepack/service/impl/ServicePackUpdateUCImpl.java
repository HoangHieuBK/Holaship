package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.mapper.ServicePackMapper;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.ServicePackUpdateUC;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public class ServicePackUpdateUCImpl implements ServicePackUpdateUC {

    private final ServicePackRepository servicePackRepository;

    private ServicePack servicePack;
    private int executedRecord;

    public ServicePackUpdateUCImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
    }

    @Override
    public ServicePackUpdateUC applyUpdateInfo(ServicePackID servicePackID, String name, Integer status, AuditLog auditLog) {
        servicePack = new ServicePack();
        servicePack.setServicePackID(servicePackID);
        servicePack.setName(name);
        servicePack.setStatus(status);
        servicePack.setAuditLog(auditLog);
        return this;
    }

    @Override
    public ServicePackUpdateUC applyChangeStatusInfo(ServicePackID servicePackID, Integer status, AuditLog auditLog) {
        servicePack = new ServicePack();
        servicePack.setServicePackID(servicePackID);
        servicePack.setStatus(status);
        servicePack.setAuditLog(auditLog);
        return this;
    }

    @Override
    public ServicePackUpdateUC update() {
        executedRecord = servicePackRepository.update(servicePack);
        return this;
    }

    @Override
    public ServicePackUpdateUC changeStatus() {
        executedRecord = servicePackRepository.changeStatus(servicePack);
        return this;
    }

    @Override
    public ServicePackUpdateUC fail() throws EntityUpdateException {
        if (executedRecord == 0) {
            throw new EntityUpdateException("Cập nhật gói cước giao nhanh không thành công");
        }
        return this;
    }

    @Override
    public ServicePackResponse endUpdate() {
        ServicePackResponse response = ServicePackMapper.getInstance().fromServicePack(servicePack);
        return response;
    }

    @Override
    public ServicePackResponse endChangeStatus() {
        ServicePackResponse response = ServicePackMapper.getInstance().fromServicePack(servicePack);
        return response;
    }

}
