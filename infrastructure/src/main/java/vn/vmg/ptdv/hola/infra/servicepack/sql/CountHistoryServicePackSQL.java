package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class CountHistoryServicePackSQL extends MappingSqlQuery implements MappingSQLCommand {
    private HashMap<String, Object> params;

    @Override
    protected Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt("TOTAL_COUNT");
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT COUNT(*) TOTAL_COUNT ")
                .append(" FROM SERVICE_PACK_SETTINGS sps ")
//                .append(" WHERE sps.SERVICE_PACK_ID = :"+ ServicePackDB.SERVICE_PACK_ID)
                .append(" AND sps.EFFECTIVE_AT <= :" + ServicePackDB.EFFECTIVE_AT);
        return builder.toString();
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        return this.executeByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        Integer id =  (Integer) parameters[0];
        LocalDate effectiveAt = (LocalDate) parameters[1];
        params = new HashMap<>();
//        params.put(ServicePackDB.SERVICE_PACK_ID, id);
        params.put(ServicePackDB.EFFECTIVE_AT, effectiveAt);
    }

    @Override
    public void declareParameters() {
//        declareParameter(new SqlParameter(ServicePackDB.SERVICE_PACK_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.EFFECTIVE_AT, Types.DATE));
    }
}
