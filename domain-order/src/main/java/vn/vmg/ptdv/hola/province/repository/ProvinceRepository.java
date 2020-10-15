package vn.vmg.ptdv.hola.province.repository;

import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;

import java.util.List;

public interface ProvinceRepository {

    List<Province> findAll(ProvinceSearch provinceSearch);

}
