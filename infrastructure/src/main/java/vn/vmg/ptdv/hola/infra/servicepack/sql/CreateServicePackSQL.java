package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CreateServicePackSQL extends SqlUpdate implements UpdateSQLCommand<ServicePackID> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO SERVICE_PACKS (")
                .append(" NAME, CODE, STATUS, CREATED_AT, CREATED_BY, UTIMESTAMP ")
                .append(" ) VALUES ( ")
                .append(" :NAME")
                .append(", :CODE")
                .append(", 1")
                .append(", SYSDATE")
                .append(", :CREATED_BY")
                .append(", SYSTIMESTAMP )");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, ServicePackID servicePackId) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        setGeneratedKeysColumnNames("ID");
        setReturnGeneratedKeys(true);
        compile();
        int created = this.updateByNamedParam(params, keyHolder);
        if (created > 0) {
            servicePackId.setId(keyHolder.getKey().longValue());
        }
        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        ServicePackDB servicePackDB = (ServicePackDB) parameters[0];
        params = new HashMap<String, Object>();
        params.put("NAME", servicePackDB.getName());
        params.put("CODE", servicePackDB.getCode());
        params.put("CREATED_BY", servicePackDB.getCreatedBy());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("NAME", Types.NUMERIC));
        declareParameter(new SqlParameter("CODE", Types.NVARCHAR));
        declareParameter(new SqlParameter("CREATED_BY", Types.NUMERIC));
    }
}
