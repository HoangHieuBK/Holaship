package vn.vmg.ptdv.hola.infra.leadtime.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeadtimeByCodeAndUtimestamp extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    @Override
    protected Object mapRow(ResultSet resultSet, int i) throws SQLException {
        LeadtimeDB leadtimeDB = new LeadtimeDB();
        leadtimeDB.setCode(resultSet.getString(LeadtimeDB.CODE));
        return resultSet;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT l.ID,l.NAME,l.CODE,l.STATUS,l.CREATED_BY,l.EFFECTIVE_AT,l.UTIMESTAMP ")
                .append(" FROM LEADTIME_PACKS l ")
                .append(" WHERE l.CODE = :" + LeadtimeDB.CODE)
                .append(" AND l.UTIMESTAMP = :" + LeadtimeDB.UTIMESTAMP)
        ;

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
        params = new HashMap<>();
        String code = (String) parameters[0];
        params.put(LeadtimeDB.CODE, code);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(LeadtimeDB.CODE, Types.VARCHAR));
    }
}
