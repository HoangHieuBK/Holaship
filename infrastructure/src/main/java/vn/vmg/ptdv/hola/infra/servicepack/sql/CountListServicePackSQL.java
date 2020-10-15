package vn.vmg.ptdv.hola.infra.servicepack.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.servicepack.factory.CountRecord;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountListServicePackSQL extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;

    @Override
    protected CountRecord mapRow(ResultSet rs, int i) throws SQLException {
        CountRecord countRecord = new CountRecord();
        countRecord.setTotalRecords(rs.getInt(CountRecord.TOTAL));
        return countRecord;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT COUNT(*) TOTAL")
//                .append(" FROM SERVICE_PACKS sp INNER JOIN SERVICE_PACK_SETTINGS sps ON sp.ID= sps.SERVICE_PACK_ID ")
//                .append(" WHERE ( :" + ServicePackDB.NAME + " IS NULL OR LOWER(sp.NAME) LIKE :" + ServicePackDB.NAME +
//                        " )")
//                .append(" AND ( :" + ServicePackDB.CODE + " IS NULL OR LOWER(sp.CODE) =  :" + ServicePackDB.CODE + ")")
//                .append(" AND ( :" + ServicePackDB.STATUS + " IS NULL OR sp.STATUS = :" + ServicePackDB.STATUS + ")")
//                .append(" AND ( :" + ServicePackDB.EFFECTIVE_AT + " IS NULL OR TRUNC(sps.EFFECTIVE_AT) = TRUNC(:" +
//                        ServicePackDB.EFFECTIVE_AT + "))")
//                .append(" AND ( :" + ServicePackDB.CREATED_FROM + " IS NULL OR TRUNC(sp.CREATED_AT) >= TRUNC(:" +
//                        ServicePackDB.CREATED_FROM + "))")
//                .append(" AND ( :" + ServicePackDB.CREATED_TO + " IS NULL OR TRUNC(sp.CREATED_AT) <= TRUNC(:" +
//                        ServicePackDB.CREATED_TO + "))")
//                .append(" AND sps.ACTIVE = 1 ")
//                .append(" AND ( ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR LOWER(sp.CODE) LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR LOWER(sp.NAME) LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_TIME LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_DISTANCE LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_POINT LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ")")
//                .append(" OR ( :" + ServicePackDB.COMMON_SEARCH + " IS NULL OR sps.MAX_ORDER LIKE  :" +
//                        ServicePackDB.COMMON_SEARCH + ") )");
        return builder.toString();
    }

    @Override
    public void prepareParams(Object... parameters) {
        ServicePackSearch servicePackSearch = (ServicePackSearch) parameters[0];
        params = new HashMap<>();
        params.put(ServicePackDB.NAME,
                servicePackSearch.getName() != null ? "%" + servicePackSearch.getName().toLowerCase() + "%" : null);
//        params.put(ServicePackDB.CODE,
//                servicePackSearch.getCode() != null ? servicePackSearch.getCode().toLowerCase() : null);
        params.put(ServicePackDB.STATUS, servicePackSearch.getStatus());
//        params.put(ServicePackDB.EFFECTIVE_AT, servicePackSearch.getEffectiveAt());
//        params.put(ServicePackDB.CREATED_FROM, servicePackSearch.getCreatedFrom());
//        params.put(ServicePackDB.CREATED_TO, servicePackSearch.getCreatedTo());
//        params.put(ServicePackDB.COMMON_SEARCH, servicePackSearch.getCommonSearch() != null ?
//                "%" + servicePackSearch.getCommonSearch().toLowerCase() + "%" : null);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(ServicePackDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(ServicePackDB.STATUS, Types.NUMERIC));
        declareParameter(new SqlParameter(ServicePackDB.EFFECTIVE_AT, Types.DATE));
//        declareParameter(new SqlParameter(ServicePackDB.COMMON_SEARCH, Types.VARCHAR));
//        declareParameter(new SqlParameter(ServicePackDB.CREATED_FROM, Types.DATE));
//        declareParameter(new SqlParameter(ServicePackDB.CREATED_TO, Types.DATE));
    }

    @Override
    public List<?> executeCommand(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        return this.executeByNamedParam(params);
    }
}