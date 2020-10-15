package vn.vmg.ptdv.hola.district.service.impl;

import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.district.service.usecase.DistrictGetUseCase;

import java.util.List;

public class DistrictGetUseCaseImpl implements DistrictGetUseCase {

    private final DistrictRepository districtRepository;

    private List<District> districts;
    private Integer totalRecords;

    public DistrictGetUseCaseImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public DistrictGetUseCase getAll(DistrictSearch districtSearch) {
        districts = districtRepository.findAll(districtSearch);
        return this;
    }

    @Override
    public DistrictListResponse endGetAll() {
        DistrictListResponse response = new DistrictListResponse();
        response.setDistricts(districts);
        response.setTotalCount(totalRecords);
        return response;
    }


}
