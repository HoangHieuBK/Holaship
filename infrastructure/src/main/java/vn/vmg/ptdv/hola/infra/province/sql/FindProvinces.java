package vn.vmg.ptdv.hola.infra.province.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.province.factory.ProvinceDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;
import vn.vmg.ptdv.hola.province.factory.search.ProvinceSearch;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindProvinces extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;
    private ProvinceSearch search;

    public FindProvinces() {
    }

    @Override
    protected ProvinceDB mapRow(ResultSet rs, int i) throws SQLException {
        ProvinceDB objDB = new ProvinceDB();
        objDB.setId(rs.getLong(ProvinceDB.ID));
        objDB.setCode(rs.getString(ProvinceDB.CODE));
        objDB.setName(rs.getString(ProvinceDB.NAME));
        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT p.ID,");
        builder.append(" p.CODE,");
        builder.append(" p.NAME, ");
        builder.append(" p.RANK,");
        builder.append(" p.STATUS ");
        builder.append(" FROM PROVINCES p ");
//        builder.append(" WHERE ( :" + LeadtimeDB.GLOBAL_SEARCH + " IS NULL OR (LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.GLOBAL_SEARCH + ") OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.GLOBAL_SEARCH + " || '%')))");
//        builder.append(" AND ( :" + LeadtimeDB.CODE + " IS NULL OR LOWER(lp.CODE) = LOWER(:" + LeadtimeDB.CODE + "))");
//        builder.append(" AND ( :" + LeadtimeDB.NAME + " IS NULL OR LOWER(lp.NAME) LIKE LOWER('%' || :" + LeadtimeDB.NAME + " || '%'))");
//        builder.append(" AND ( :" + LeadtimeDB.STATUS + " IS NULL OR lp.STATUS = :" + LeadtimeDB.STATUS + ")");

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
        this.search = (ProvinceSearch) parameters[0];

        params = new HashMap<>();
//        params.put(ProvinceDB.CODE, search.getProvinceId().getCode());
//        params.put(ProvinceDB.NAME, search.getName());
    }

    @Override
    public void declareParameters() {
//        declareParameter(new SqlParameter(LeadtimeDB.GLOBAL_SEARCH, Types.NVARCHAR));
//        declareParameter(new SqlParameter(LeadtimeDB.CODE, Types.VARCHAR));
//        declareParameter(new SqlParameter(LeadtimeDB.NAME, Types.VARCHAR));
//        declareParameter(new SqlParameter(LeadtimeDB.STATUS, Types.NUMERIC));
////        declareParameter(new SqlParameter(LeadtimeDB.NOTE, Types.NVARCHAR));
//        if (search.getEffectiveAt() != null) {
//            declareParameter(new SqlParameter(LeadtimeDB.EFFECTIVE_AT, Types.DATE));
//        }
//        if (!isCreateAtFromNull()) {
//            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_FROM, Types.DATE));
//        }
//        if (!isCreateAtToNull()) {
//            declareParameter(new SqlParameter(LeadtimeDB.CREATED_AT_TO, Types.DATE));
//        }
//        declareParameter(new SqlParameter(LeadtimeDB.OFFSET, Types.NUMERIC));
//        declareParameter(new SqlParameter(LeadtimeDB.FETCH_NEXT, Types.NUMERIC));
    }

}
