package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.presentation.SPUpdateResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.SPCreateUseCase;
import vn.vmg.ptdv.hola.shared.AuditLog;

public class SPCreateUseCaseImpl implements SPCreateUseCase {
    private final ServicePackRepository servicePackRepository;
    private ServicePack servicePack;
    private ServicePack currentServicePack;
    private AuditLog auditLog;
    private boolean isEffective = false;
    private ServicePackID servicePackID;
    private int executedRecord;

    public SPCreateUseCaseImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
        this.servicePack = new ServicePack();
    }

    @Override
    public SPCreateUseCase create(ServicePackInfo servicePackInfo, ServicePackID servicePackId) throws OrderDomainException {
        executedRecord = servicePackRepository.createServicePack(servicePackInfo, servicePackId);
        return this;
    }

    @Override
    public SPUpdateResponse end() {
        SPUpdateResponse response = new SPUpdateResponse();
        response.setAuditLog(auditLog);
        response.setSuccess(true);
        return response;
    }

}
