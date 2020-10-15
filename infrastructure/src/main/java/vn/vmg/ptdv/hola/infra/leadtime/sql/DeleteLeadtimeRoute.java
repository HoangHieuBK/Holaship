package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DeleteLeadtimeRoute extends SqlUpdate implements UpdateSQLCommand<Leadtime> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" DELETE LEADTIME_PACK_ROUTES ")
                .append(" WHERE")
                .append(" ID = :" + LeadtimeRouteDB.ID);
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, Leadtime leadtime) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        compile();
        return this.updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        Long id = (Long) parameters[0];
        params = new HashMap<>();
        params.put(LeadtimeRouteDB.ID, id);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeRouteDB.ID, Types.NUMERIC));
    }
}
