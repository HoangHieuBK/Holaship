package vn.vmg.ptdv.hola.infra.district;

import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.infra.district.factory.DistrictDB;
import vn.vmg.ptdv.hola.infra.district.mapper.DistrictDBMapper;

import java.util.ArrayList;
import java.util.List;

public class DistrictRepositoryImpl implements DistrictRepository {

    private final DistrictJDBC jdbc;

    public DistrictRepositoryImpl(DistrictJDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<District> findAll(DistrictSearch districtSearch) {
        List<DistrictDB> objDbs = jdbc.findAll(districtSearch);
        List<District> result = new ArrayList<>();
        if (!objDbs.isEmpty()) {
            for (DistrictDB objDb : objDbs
            ) {
                result.add(DistrictDBMapper.getInstance().fromDB(objDb));
            }
        }
        return result;
    }

}
