package vn.vmg.ptdv.hola.infra.province;

import vn.vmg.ptdv.hola.infra.province.context.ProvinceInfraContext;
import vn.vmg.ptdv.hola.infra.province.factory.ProvinceDB;
import vn.vmg.ptdv.hola.infra.province.sql.FindProvinces;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;

import javax.sql.DataSource;
import java.util.List;

public class ProvinceJDBC {

    private final DataSource dataSource;
    private ProvinceInfraContext jdbcContext;

    public ProvinceJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ProvinceDB> findAll(ProvinceSearch provinceSearch) {
        this.jdbcContext = new ProvinceInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindProvinces())
                .withParams(provinceSearch)
                .executeCommand();

        return (List<ProvinceDB>) rs;
    }

}
