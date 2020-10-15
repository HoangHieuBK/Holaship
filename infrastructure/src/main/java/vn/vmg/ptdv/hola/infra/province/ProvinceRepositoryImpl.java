package vn.vmg.ptdv.hola.infra.province;

import vn.vmg.ptdv.hola.infra.province.factory.ProvinceDB;
import vn.vmg.ptdv.hola.infra.province.mapper.ProvinceDBMapper;
import vn.vmg.ptdv.hola.province.factory.Province;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;

import java.util.ArrayList;
import java.util.List;

public class ProvinceRepositoryImpl implements ProvinceRepository {

    private final ProvinceJDBC jdbc;

    public ProvinceRepositoryImpl(ProvinceJDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Province> findAll(ProvinceSearch provinceSearch) {
        List<ProvinceDB> provinceDBs = jdbc.findAll(provinceSearch);
        List<Province> provinces = new ArrayList<>();
        if (!provinceDBs.isEmpty()) {
            for (ProvinceDB objDB : provinceDBs
            ) {
                provinces.add(ProvinceDBMapper.getInstance().fromDB(objDB));
            }
        }
        return provinces;
    }

}
