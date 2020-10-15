package vn.vmg.ptdv.hola.district.repository;

import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;

import java.util.List;

public interface DistrictRepository {

    List<District> findAll(DistrictSearch districtSearch);

}
