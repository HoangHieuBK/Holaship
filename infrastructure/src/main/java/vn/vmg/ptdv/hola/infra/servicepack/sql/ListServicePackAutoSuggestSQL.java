package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

public class ListServicePackAutoSuggestSQL extends MappingSqlQuery implements MappingSQLCommand {
    private HashMap<String, Object> params;

    @Override
    protected ServicePackDB mapRow(ResultSet resultSet, int i) throws SQLException {
        ServicePackDB servicePackDB = new ServicePackDB();
        servicePackDB.setName(resultSet.getString(ServicePackDB.NAME));
        servicePackDB.setCode(resultSet.getString(ServicePackDB.CODE));
        return servicePackDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT sp.NAME, sp.CODE ")
                .append(" FROM SERVICE_PACKS sp")
                .append(" WHERE (:" + ServicePackDB.NAME + " IS NULL OR sp.NAME = :" +
                        ServicePackDB.NAME + ") ")
                .append(" AND (:" + ServicePackDB.CODE + " IS NULL OR sp.CODE = :" + ServicePackDB.CODE + " ) ")
                .append(" ORDER BY ")
                .append(" NAME ASC")
                .append(" OFFSET 0 ROWS FETCH NEXT 20 ROWS  ONLY");


        return builder.toString();
    }

    @Override
    public void prepareParams(Object... parameters) {
        ServicePackSearch servicePackSearch = (ServicePackSearch) parameters[0];
        params = new HashMap<>();
        params.put(ServicePackDB.NAME, servicePackSearch.getName());
//        params.put(ServicePackDB.CODE, servicePackSearch.getCode());

    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        return this.executeByNamedParam(params);
    }
}
