package vn.vmg.ptdv.hola.infra.district.mapper;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.district.factory.District;
import vn.vmg.ptdv.hola.infra.district.factory.DistrictDB;

public class DistrictDBMapper {

    private static DistrictDBMapper INSTANCE;

    private DistrictDBMapper() {

    }

    public static DistrictDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DistrictDBMapper();
        }
        return INSTANCE;
    }

    public District fromDB(DistrictDB objDB) {
        District district = new District();
        district.setDistrictId(new AddressId(objDB.getId(), objDB.getCode()));
        district.setName(objDB.getName());
        district.setProvinceId(new AddressId(objDB.getProvinceId(), null));
        return district;
    }

}
