package vn.vmg.ptdv.hola.province.service.impl;

import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;
import vn.vmg.ptdv.hola.province.service.usecase.ProvinceGetUseCase;

import java.util.List;

public class ProvinceGetUseCaseImpl implements ProvinceGetUseCase {

    private final ProvinceRepository provinceRepository;

    private List<Province> provinces;
    private Integer totalRecords;

    public ProvinceGetUseCaseImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ProvinceGetUseCase getAll(ProvinceSearch provinceSearch) {
        provinces = provinceRepository.findAll(provinceSearch);
        return this;
    }

    @Override
    public ProvinceListResponse endGetAll() {
        ProvinceListResponse response = new ProvinceListResponse();
        response.setProvinces(provinces);
        response.setTotalCount(totalRecords);
        return response;
    }

}
