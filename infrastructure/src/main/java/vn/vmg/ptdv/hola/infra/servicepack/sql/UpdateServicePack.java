package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class UpdateServicePack extends SqlUpdate implements UpdateSQLCommand<ServicePackID> {

    private Map<String, Object> params;

    public UpdateServicePack() {
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE SERVICE_PACKS SET ");
        builder.append("  NAME = :" + ServicePackDB.NAME + ", ");
        builder.append("  CODE = :" + ServicePackDB.CODE + ", ");
        builder.append("  STATUS = :" + ServicePackDB.STATUS + ", ");
        builder.append("  UPDATED_AT = SYSDATE ");
        builder.append("  UPDATED_BY = :" + ServicePackDB.UPDATED_BY + ", ");
        builder.append("  UTIMESTAMP = SYSTIMESTAMP ");
        builder.append(" WHERE ID = :" + ServicePackDB.ID + " AND UTIMESTAMP = :" + ServicePackDB.UTIMESTAMP);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, ServicePackID servicePackID) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        int executedRecord = this.updateByNamedParam(params);
        return executedRecord;
    }

    @Override
    public void prepareParams(Object... parameters) {
        ServicePackDB objDB = (ServicePackDB) parameters[0];
        params = new HashMap<>();
        params.put(ServicePackDB.NAME, objDB.getName());
        params.put(ServicePackDB.CODE, objDB.getCode());
        params.put(ServicePackDB.STATUS, objDB.getStatus());
        params.put(ServicePackDB.UPDATED_AT, objDB.getUpdatedAt() != null ? objDB.getUpdatedAt().toLocalDate() : null);
        params.put(ServicePackDB.UPDATED_BY, objDB.getUpdatedBy());
        params.put(ServicePackDB.UTIMESTAMP, objDB.getUTimestamp());
        params.put(ServicePackDB.ID, objDB.getId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.NAME, Types.NVARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.UPDATED_AT, Types.DATE));
        declareParameter(new SqlParameter(ServicePackDB.UPDATED_BY, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.UTIMESTAMP, Types.TIMESTAMP));
        declareParameter(new SqlParameter(ServicePackDB.ID, Types.NUMERIC));
    }
}
