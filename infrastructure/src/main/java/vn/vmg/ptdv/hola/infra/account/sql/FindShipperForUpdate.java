package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.ShipperInfoDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindShipperForUpdate extends MappingSqlQuery implements MappingSQLCommand {
    private Map<String, Object> params;

    /**
     * Subclasses must implement this method to convert each row of the
     * ResultSet into an object of the result type.
     * <p>Subclasses of this class, as opposed to direct subclasses of
     * MappingSqlQueryWithParameters, don't need to concern themselves
     * with the parameters to the execute method of the query object.
     *
     * @param rs     the ResultSet we're working through
     * @param rowNum row number (from 0) we're up to
     * @return an object of the result type
     * @throws SQLException if there's an error extracting data.
     *                      Subclasses can simply not catch SQLExceptions, relying on the
     *                      framework to clean up.
     */
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipperInfoDB db = new ShipperInfoDB();
        db.setId(rs.getLong("ID"));
        db.setUtimestamp(rs.getTimestamp("UTIMESTAMP"));
        return db;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT ")
                .append("  SP.ID ")
                .append(" ,SP.UTIMESTAMP ")
                .append(" FROM SHIPPER_PROFILES SP ")
                .append(" LEFT JOIN APP_USERS AU ON AU.ID = SP.PROFILE_ID ")
                .append(" WHERE AU.PHONE = :PHONE ")
                .append("    AND SP.UTIMESTAMP = :UTIMESTAMP ");

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
        String phone = (String) parameters[0];
        Timestamp utimestamp = (Timestamp) parameters[1];
        params = new HashMap<>();
        params.put("PHONE", phone);
        params.put("UTIMESTAMP", utimestamp);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP_WITH_TIMEZONE));
    }
}
