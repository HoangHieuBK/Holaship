package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.shared.BaseFilter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ListHistoryServicePackSQL extends MappingSqlQuery implements MappingSQLCommand {
    private HashMap<String, Object> params;

    @Override
    protected ServicePackDB mapRow(ResultSet resultSet, int i) throws SQLException {
        ServicePackDB servicePackDB = new ServicePackDB();
        servicePackDB.setId(resultSet.getLong(ServicePackDB.ID));
        servicePackDB.setName(resultSet.getString(ServicePackDB.NAME));
        servicePackDB.setCode(resultSet.getString(ServicePackDB.CODE));
        servicePackDB.setUpdatedBy(resultSet.getLong(ServicePackDB.UPDATED_BY));
//        servicePackDB.setNameUpdatedBy(resultSet.getString(ServicePackDB.NAME_UPDATED_BY));
//        servicePackDB.setUpdatedAt(resultSet.getObject(ServicePackDB.UPDATED_AT, LocalDate.class));
//        servicePackDB.setServicePackSettingId(resultSet.getLong(ServicePackDB.SERVICE_PACK_SETTING_ID));
//        servicePackDB.setEffectiveAt(resultSet.getObject(ServicePackDB.EFFECTIVE_AT, LocalDate.class));
        servicePackDB.setActive(resultSet.getInt(ServicePackDB.ACTIVE));
        return servicePackDB;
    }

    @Override
    public String prepareSQL() {

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT sp.ID ,sp.NAME, sp.CODE, sp.UPDATED_BY, aa.NAME NAME_UPDATED_BY,  ")
                .append(" sp.UPDATED_AT, sps.ID SERVICE_PACK_SETTING_ID, sps.EFFECTIVE_AT, sps.ACTIVE ")
                .append(" FROM SERVICE_PACK_SETTINGS sps ")
                .append(" LEFT JOIN SERVICE_PACKS sp ON sps.SERVICE_PACK_ID = sp.ID  ")
                .append(" LEFT JOIN  APP_ADMINS aa ON aa.ID = sp.UPDATED_BY  ")
                .append(" WHERE sp.ID = :"+ ServicePackDB.ID)
                .append(" AND sps.EFFECTIVE_AT <= :" + ServicePackDB.EFFECTIVE_AT)
                .append(" ORDER BY UPDATED_AT ASC")
                .append(" OFFSET :" + ServicePackDB.OFFSET + " ROWS")
                .append(" FETCH NEXT :" + ServicePackDB.FETCH_NEXT + " ROWS ONLY");

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
        Integer id = (Integer) parameters[0];
        LocalDate effectiveAt = (LocalDate) parameters[1];
        BaseFilter baseFilter = (BaseFilter) parameters[2];
        int offset = (baseFilter.getPageIndex() - 1) * baseFilter.getPageSize();
        if (offset < 0) {
            offset = 0;
        }

        params = new HashMap<>();
        params.put(ServicePackDB.ID, id);
        params.put(ServicePackDB.EFFECTIVE_AT, effectiveAt);
        params.put(ServicePackDB.OFFSET, offset);
        params.put(ServicePackDB.FETCH_NEXT, baseFilter.getPageSize() <= 0 ? 15 : baseFilter.getPageSize());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.ID, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.EFFECTIVE_AT, Types.DATE));
        declareParameter(new SqlParameter(ServicePackDB.OFFSET, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.FETCH_NEXT, Types.NUMERIC));
    }
}
