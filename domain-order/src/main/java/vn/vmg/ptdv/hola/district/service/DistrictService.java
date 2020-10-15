package vn.vmg.ptdv.hola.district.service;

import vn.vmg.ptdv.hola.district.repository.DistrictRepository;

public class DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

}
