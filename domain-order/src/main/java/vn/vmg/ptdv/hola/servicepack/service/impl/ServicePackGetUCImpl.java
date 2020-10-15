package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.exception.EntityNotFoundException;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.mapper.ServicePackMapper;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackListResponse;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.ServicePackGetUC;

import java.util.List;

public class ServicePackGetUCImpl implements ServicePackGetUC {

    private final ServicePackRepository servicePackRepository;

    private ServicePackSearch servicePackSearch;
    private PagingSortFilter pagingSortFilter;
    private List<ServicePack> servicePacks;
    private ServicePack servicePack;
    private ServicePackID servicePackID;
    private Integer totalRecords;

    public ServicePackGetUCImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
    }

    @Override
    public ServicePackGetUC applySearch(ServicePackSearch servicePackSearch) {
        this.servicePackSearch = servicePackSearch;
        return this;
    }

    @Override
    public ServicePackGetUC applyPagingSortFilter(PagingSortFilter pagingSortFilter) {
        this.pagingSortFilter = pagingSortFilter;
        return this;
    }

    @Override
    public ServicePackGetUC applyServicePackID(ServicePackID servicePackID) {
        this.servicePackID = servicePackID;
        return this;
    }

    @Override
    public ServicePackGetUC getAll() {
        servicePacks = servicePackRepository.findAll(servicePackSearch, pagingSortFilter);
        return this;
    }

    @Override
    public ServicePackGetUC getByCode() {
        servicePack = servicePackRepository.findByCode(servicePackID.getCode());
        return this;
    }

    @Override
    public ServicePackGetUC notFound() throws EntityNotFoundException {
        if (servicePack == null) {
            throw new EntityNotFoundException(404, "Không tìm thấy gói cước giao ngay");
        }
        return this;
    }

    @Override
    public ServicePackListResponse endGetAll() {
        ServicePackListResponse response = new ServicePackListResponse();
        response.setServicePacks(servicePacks);
        response.setPageIndex(pagingSortFilter.getPageIndex());
        response.setPageSize(pagingSortFilter.getPageSize());
        response.setTotalCount(totalRecords);
        return response;
    }

    @Override
    public ServicePackResponse endGetByCode() {
        ServicePackResponse response = ServicePackMapper.getInstance().fromServicePack(servicePack);
        return response;
    }

}
