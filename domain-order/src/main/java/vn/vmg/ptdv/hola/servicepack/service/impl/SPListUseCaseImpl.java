package vn.vmg.ptdv.hola.servicepack.service.impl;

import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;
import vn.vmg.ptdv.hola.servicepack.presentation.SPListResponse;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.usecase.SPListUseCase;

import java.util.ArrayList;
import java.util.List;

public class SPListUseCaseImpl implements SPListUseCase {
    private SPListSearch search;
    private SPPagingAndSort pagingAndSort;
    private List<ServicePack> listOfServicePack;

    private final ServicePackRepository servicePackRepository;

    public SPListUseCaseImpl(ServicePackRepository servicePackRepository) {
        this.servicePackRepository = servicePackRepository;
    }

    @Override
    public SPListUseCase withSearch(SPListSearch search) {
        this.search = search;
        return this;
    }

    @Override
    public SPListUseCase withPagingAndSort(SPPagingAndSort pagingAndSort) {
        this.pagingAndSort = pagingAndSort;
        return this;
    }

    @Override
    public SPListUseCase search() {
        listOfServicePack = servicePackRepository.findListPagingAndSort(search, pagingAndSort);
        return this;
    }

    @Override
    public SPListResponse end() {
        SPListResponse response = new SPListResponse();
        response.setPagingAndSort(pagingAndSort);
        response.setServicePackList(listOfServicePack == null ? new ArrayList<>() : listOfServicePack);
        return response;
    }
}
