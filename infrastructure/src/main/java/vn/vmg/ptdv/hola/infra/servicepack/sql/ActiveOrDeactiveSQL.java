package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActiveOrDeactiveSQL extends SqlUpdate implements MappingSQLCommand {
    HashMap<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE SERVICE_PACKS S")
                .append(" SET")
                .append(" S.UTIMESTAMP = SYSTIMESTAMP,")
                .append(" S.STATUS = :" + ServicePackDB.STATUS)
                .append(" WHERE")
                .append(" S.CODE = :" + ServicePackDB.CODE)
                .append(" AND S.UTIMESTAMP = :" + ServicePackDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public void prepareParams(Object... parameters) {
        params = new HashMap<>();
        ServicePackDB servicePackDB = (ServicePackDB) parameters[0];
        params.put(ServicePackDB.CODE, servicePackDB.getCode());
        params.put(ServicePackDB.UTIMESTAMP, servicePackDB.getUTimestamp());
        params.put(ServicePackDB.STATUS, servicePackDB.getStatus());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.UTIMESTAMP, Types.TIMESTAMP));
        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        List<Integer> results = new ArrayList<>();
        int result = updateByNamedParam(params);
        results.add(result);
        return results;
    }
}
