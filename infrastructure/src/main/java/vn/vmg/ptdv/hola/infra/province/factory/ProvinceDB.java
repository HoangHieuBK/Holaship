package vn.vmg.ptdv.hola.infra.province.factory;

import lombok.Data;

@Data
public class ProvinceDB {

    public static final String ID = "ID";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";

    private Long id;
    private String code;
    private String name;

}
