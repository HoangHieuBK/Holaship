package vn.vmg.ptdv.hola.infra.district.sql;

import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.district.factory.search.DistrictSearch;
import vn.vmg.ptdv.hola.infra.district.factory.DistrictDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDistricts extends MappingSqlQuery implements MappingSQLCommand {

    private Map<String, Object> params;
    private DistrictSearch search;

    public FindDistricts() {
    }

    @Override
    protected DistrictDB mapRow(ResultSet rs, int i) throws SQLException {
        DistrictDB objDB = new DistrictDB();
        objDB.setId(rs.getLong(DistrictDB.ID));
        objDB.setCode(rs.getString(DistrictDB.CODE));
        objDB.setName(rs.getString(DistrictDB.NAME));
        objDB.setProvinceId(rs.getLong(DistrictDB.PROVINCE_ID));
        return objDB;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT d.ID,");
        builder.append(" d.CODE,");
        builder.append(" d.NAME, ");
        builder.append(" d.PROVINCE_ID,");
        builder.append(" d.IS_INNER, ");
        builder.append(" d.STATUS ");
        builder.append(" FROM DISTRICTS d ");
//        builder.append(" WHERE ( :" + DistrictDB.PROVINCE_ID + " IS NULL OR d.PROVINCE_ID = :" + DistrictDB.PROVINCE_ID + ") ");
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
        this.search = (DistrictSearch) parameters[0];

        params = new HashMap<>();
//        params.put(DistrictDB.PROVINCE_ID, search.getProvinceId().getId());
    }

    @Override
    public void declareParameters() {
//        declareParameter(new SqlParameter(DistrictDB.PROVINCE_ID, Types.NUMERIC));
    }

}
