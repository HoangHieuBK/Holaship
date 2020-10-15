package vn.vmg.ptdv.hola.infra.district.factory;

import lombok.Data;

@Data
public class DistrictDB {

    public static final String ID = "ID";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String PROVINCE_ID = "PROVINCE_ID";

    private Long id;
    private String code;
    private String name;
    private Long provinceId;

}
