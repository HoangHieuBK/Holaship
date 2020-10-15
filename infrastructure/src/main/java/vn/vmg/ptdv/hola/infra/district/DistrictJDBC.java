package vn.vmg.ptdv.hola.infra.district;

import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.infra.district.context.DistrictInfraContext;
import vn.vmg.ptdv.hola.infra.district.factory.DistrictDB;
import vn.vmg.ptdv.hola.infra.district.sql.FindDistricts;

import javax.sql.DataSource;
import java.util.List;

public class DistrictJDBC {

    private final DataSource dataSource;
    private DistrictInfraContext jdbcContext;

    public DistrictJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DistrictDB> findAll(DistrictSearch districtSearch) {
        this.jdbcContext = new DistrictInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindDistricts())
                .withParams(districtSearch)
                .executeCommand();

        return (List<DistrictDB>) rs;
    }

}
