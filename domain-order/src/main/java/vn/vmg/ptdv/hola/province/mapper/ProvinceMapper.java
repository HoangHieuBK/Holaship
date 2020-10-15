package vn.vmg.ptdv.hola.province.mapper;

public class ProvinceMapper {

    private static ProvinceMapper INSTANCE;

    private ProvinceMapper() {
    }

    public static ProvinceMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProvinceMapper();
        }

        return INSTANCE;
    }

}
