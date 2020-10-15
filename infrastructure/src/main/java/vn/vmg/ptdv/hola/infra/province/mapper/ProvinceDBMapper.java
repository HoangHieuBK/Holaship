package vn.vmg.ptdv.hola.infra.province.mapper;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.infra.province.factory.ProvinceDB;
import vn.vmg.ptdv.hola.province.factory.Province;

public class ProvinceDBMapper {

    private static ProvinceDBMapper INSTANCE;

    private ProvinceDBMapper() {

    }

    public static ProvinceDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProvinceDBMapper();
        }
        return INSTANCE;
    }

    public Province fromDB(ProvinceDB provinceDB) {
        Province province = new Province();
        province.setProvinceId(new AddressId(provinceDB.getId(), provinceDB.getCode()));
        province.setName(provinceDB.getName());
        return province;
    }
    
}
