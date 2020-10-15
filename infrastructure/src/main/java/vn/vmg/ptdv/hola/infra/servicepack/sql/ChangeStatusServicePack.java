package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class ChangeStatusServicePack extends SqlUpdate implements UpdateSQLCommand<ServicePackID> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE SERVICE_PACKS ")
                .append(" SET")
                .append(" UTIMESTAMP = SYSTIMESTAMP,")
                .append(" STATUS = :" + ServicePackDB.STATUS)
                .append(" WHERE")
                .append(" ID = :" + ServicePackDB.ID)
                .append(" AND UTIMESTAMP = :" + ServicePackDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, ServicePackID servicePackID) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        compile();
        return this.updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        Long id = (Long) parameters[0];
        int status = (int) parameters[1];
        Timestamp uTimestamp = (Timestamp) parameters[2];
        params = new HashMap<>();
        params.put(ServicePackDB.STATUS, status);
        params.put(ServicePackDB.ID, id);
        params.put(ServicePackDB.UTIMESTAMP, uTimestamp);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.ID, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.UTIMESTAMP, Types.TIMESTAMP));
    }
}
