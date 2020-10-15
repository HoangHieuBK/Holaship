package vn.vmg.ptdv.hola.servicepack.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeCreateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeUpdateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeCreateUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeUpdateUC;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.presentation.*;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.impl.*;
import vn.vmg.ptdv.hola.servicepack.service.usecase.*;

public class ServicePackService {

    private final ServicePackRepository servicePackRepository;

    public ServicePackService(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ServicePackListResponse getAll(ServicePackSearchRequest request) {
        ServicePackGetUC useCase = new ServicePackGetUCImpl(servicePackRepository);
        ServicePackListResponse response = useCase
                .applySearch(request.getServicePackSearch())
                .applyPagingSortFilter(request.getPagingSortFilter())
                .getAll()
                .endGetAll();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ServicePackResponse getByCode(ServicePackSearchRequest request) throws EntityNotFoundException {
        ServicePackGetUC useCase = new ServicePackGetUCImpl(servicePackRepository);
        ServicePackResponse servicePackResponse = useCase
                .applyServicePackID(request.getServicePackSearch().getServicePackID())
                .getByCode()
                .notFound()
                .endGetByCode();
        return servicePackResponse;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ServicePackResponse create(ServicePackRequest request) throws EntityInsertException {
        ServicePackCreateUC useCase = new ServicePackCreateUCImpl(servicePackRepository);
        ServicePackResponse response = useCase
                .applyCreateInfo(request.getServicePackID(), request.getName(), request.getAuditLog())
                .create()
                .fail()
                .end();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ServicePackResponse update(ServicePackRequest request) throws EntityUpdateException {
        ServicePackUpdateUC useCase = new ServicePackUpdateUCImpl(servicePackRepository);
        ServicePackResponse response = useCase
                .applyUpdateInfo(request.getServicePackID(), request.getName(), request.getStatus(), request.getAuditLog())
                .update()
                .fail()
                .endUpdate();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ServicePackResponse changeStatus(ServicePackRequest request) throws EntityUpdateException {
        ServicePackUpdateUC useCase = new ServicePackUpdateUCImpl(servicePackRepository);
        ServicePackResponse response = useCase
                .applyChangeStatusInfo(request.getServicePackID(), request.getStatus(), request.getAuditLog())
                .changeStatus()
                .fail()
                .endChangeStatus();
        return response;
    }











    public SPUpdateResponse updateServicePack(SPUpdateRequest request) throws EntityUpdateException,
            EntityNotFoundException {
        SPUpdateUseCase useCase = new SPUpdateUseCaseImpl(servicePackRepository);
        SPUpdateResponse response = useCase
                .applyIdentity(request.getServicePackID(), request.getAuditLog())
                .applyBasicInfo(request.getName(), request.getDescription(), request.getDeliveryTimeMax(),
                        request.getEffectiveDate(), request.getPriceBase(), request.getState())
                .applyDistance(request.getDistance())
                .applyDestination(request.getDestination())
                .applyOrderDetail(request.getOrderDetailSetting())
                .applyOtherCost(request.getPorterage(), request.getD2dCost(), request.getRefundCost(),
                        request.getDeclarePriceCost(), request.getOtherCost())
                .update()
                .createNewWhenEffective()
                .fail()
                .end();

        return response;
    }

    public SPUpdateResponse getServicePackById(SPUpdateRequest request) throws EntityNotFoundException {
        SPUpdateUseCase useCase = new SPUpdateUseCaseImpl(servicePackRepository);
        SPUpdateResponse response = useCase
                .getByIdentity(request.getServicePackID(), request.getAuditLog())
                .notFound()
                .endGetInfo();

        return response;
    }

    public SPListResponse listOfServicePack(SPListRequest request) {
        SPListUseCase userCase = new SPListUseCaseImpl(servicePackRepository);
        SPListResponse response = userCase
                .withSearch(request.getSearch())
                .withPagingAndSort(request.getPagingAndSort())
                .search()
                .end();

        return response;
    }


//    public ServicePackResponse listServicePack(ServicePackRequest request) throws OrderDomainException {
//        ServicePackSearch servicePackSearch = ServicePackMapper.getInstance().buildServicePackSearch(request);
//        BaseFilter filter = ServicePackMapper.getInstance().buildServicePackFilter(request);
//        List<ServicePackInfo> listServicePack = servicePackRepository.findListServicePack(servicePackSearch, filter);
//        ServicePackResponse response = new ServicePackResponse();
//        response.setPageIndex(request.getPageIndex());
//        response.setPageSize(request.getPageSize());
//        response.setTotalRecord(servicePackRepository.countListServicePack(servicePackSearch));
//        response.setListServicePacks(listServicePack);
//        return response;
//    }

//    public ServicePackResponse getListServicePack(ServicePackRequest request) throws OrderDomainException {
//        ServicePackSearch servicePackSearch = ServicePackMapper.getInstance().buildServicePackSearch(request);
//        List<ServicePackInfo> listServicePack = servicePackRepository.getServicePack(servicePackSearch);
//        ServicePackResponse response = new ServicePackResponse();
//        response.setListServicePacks(listServicePack);
//        return response;
//    }

//    public ServicePackResponse getHistoryServicePack(HistoryServicePackRequest request) throws
//            OrderDomainException {
//        BaseFilter baseFilter = ServicePackMapper.getInstance().buildFilterServicePack(request);
//        List<ServicePackInfo> listServicePack = servicePackRepository
//                .getHistoryServicePack(request.getServicePackHistoryId().getId(), request.getEffectiveAt(), baseFilter);
//        Integer totalRecord = servicePackRepository
//                .countTotalByHistoryServicePack(request.getServicePackHistoryId().getId(), request.getEffectiveAt());
//        ServicePackResponse response = new ServicePackResponse();
//        response.setListServicePacks(listServicePack);
//        response.setPageIndex(request.getPagingSortFilter().getPageIndex());
//        response.setPageSize(request.getPagingSortFilter().getPageSize());
//        response.setTotalRecord(totalRecord);
//        return response;
//    }

    public ServicePackInfo getServicePackSettingByID(Integer id) throws OrderDomainException {
        ServicePackInfo servicePackSetting = servicePackRepository
                .findServicePackById(id);
        return servicePackSetting;
    }

    public ServicePackInfo getServicePackByCode(String code) throws OrderDomainException {
        ServicePackInfo servicePackSetting = servicePackRepository.findServicePackByCode(code);
        return servicePackSetting;
    }

    public SPUpdateResponse activeOrDeactive(SPUpdateRequest request) throws OrderDomainException {
        SPUpdateUseCase useCase = new SPUpdateUseCaseImpl(servicePackRepository);
        SPUpdateResponse response = useCase
                .activeOrDeactive(request.getServicePackID(), request.getState(), request.getAuditLog())
                .end();
        return response;
    }

//    public SPUpdateResponse createServicePack(ServicePackRequest servicePackRequest) throws OrderDomainException {
//        ServicePackInfo servicePackInfo = ServicePackMapper.getInstance().buildServicePackInfo(servicePackRequest);
//
//        ServicePackID servicePackId = new ServicePackID(GenerationCode.generationServicePack());
//        SPCreateUseCase useCase = new SPCreateUseCaseImpl(servicePackRepository);
//        SPUpdateResponse response = useCase
//                .create(servicePackInfo, servicePackId)
//                .end();
//
//        return response;
//    }
}
