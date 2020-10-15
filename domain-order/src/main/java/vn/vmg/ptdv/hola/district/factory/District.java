package vn.vmg.ptdv.hola.district.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;


@Data
public class District {

    private AddressId districtId;
    private String name;
    private AddressId provinceId;

}
