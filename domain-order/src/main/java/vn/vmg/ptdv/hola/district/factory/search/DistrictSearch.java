package vn.vmg.ptdv.hola.district.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;

@Data
public class DistrictSearch {

    private AddressId districtId;
    private String name;
    private AddressId provinceId;
    private Integer isInner;
    private Integer status;

    public DistrictSearch withDistrictId(Long id, String code) {
        districtId = new AddressId(id, code);
        return this;
    }

    public DistrictSearch withProvinceId(Long id, String code) {
        provinceId = new AddressId(id, code);
        return this;
    }
}
