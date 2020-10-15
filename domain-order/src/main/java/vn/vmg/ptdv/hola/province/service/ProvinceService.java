package vn.vmg.ptdv.hola.province.service;

import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;

public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

}
