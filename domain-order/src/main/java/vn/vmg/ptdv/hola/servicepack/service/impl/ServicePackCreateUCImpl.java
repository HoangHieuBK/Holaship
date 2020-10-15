package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeCreateUC;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.mapper.ServicePackMapper;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.ServicePackCreateUC;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;

public class ServicePackCreateUCImpl implements ServicePackCreateUC {

    private final ServicePackRepository servicePackRepository;

    private ServicePack servicePack;
    private int executedRecord;

    public ServicePackCreateUCImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
    }

    @Override
    public ServicePackCreateUC applyCreateInfo(ServicePackID servicePackID, String name, AuditLog auditLog) {
        servicePack = new ServicePack();
        servicePack.setServicePackID(servicePackID);
        servicePack.setName(name);
        servicePack.setAuditLog(auditLog);
        return this;
    }

    @Override
    public ServicePackCreateUC create() {
        executedRecord = servicePackRepository.create(servicePack);
        return this;
    }

    @Override
    public ServicePackCreateUC fail() throws EntityInsertException {
        if (executedRecord == 0) {
            throw new EntityInsertException("Thêm mới gói cước giao nhanh không thành công");
        }
        return this;
    }

    @Override
    public ServicePackResponse end() {
        ServicePackResponse response = ServicePackMapper.getInstance().fromServicePack(servicePack);
        return response;
    }

}
