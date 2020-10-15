package vn.vmg.ptdv.hola.district.mapper;

public class DistrictMapper {

    private static DistrictMapper INSTANCE;

    private DistrictMapper() {
    }

    public static DistrictMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DistrictMapper();
        }

        return INSTANCE;
    }

}
