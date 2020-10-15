package vn.vmg.ptdv.hola.province.factory.search;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;

@Data
public class ProvinceSearch {

    private AddressId provinceId;
    private String name;
    private Integer status;
    private Integer rank;

    public ProvinceSearch withProvinceId(Long id, String code) {
        provinceId = new AddressId(id, code);
        return this;
    }
}
