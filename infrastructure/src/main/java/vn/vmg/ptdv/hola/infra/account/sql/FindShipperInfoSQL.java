package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.vmg.ptdv.hola.infra.account.factory.ShipperInfoDB;
import vn.vmg.ptdv.hola.infra.shared.MappingSQLCommand;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindShipperInfoSQL extends MappingSqlQuery implements MappingSQLCommand {
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
    protected ShipperInfoDB mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShipperInfoDB db = new ShipperInfoDB();
        db.setId(rs.getLong("ID"));
        db.setUtimestamp(rs.getTimestamp("UTIMESTAMP"));

        db.setNumberCardID(rs.getString("NUMBER_ID_CARD"));
        db.setDateCardID(rs.getObject("DATE_ID_CARD", LocalDate.class));
        db.setPlaceCardID(rs.getString("PLACE_ID_CARD"));
        db.setImgBeforeCardID(rs.getString("IMG_BEFORE_ID_CARD"));
        db.setImgAfterCardID(rs.getString("IMG_AFTER_ID_CARD"));

        db.setVehicleType(rs.getInt("VEHICLE_TYPE"));
        db.setLicensePlace(rs.getString("LICENSE_PLATE"));
        db.setImgBeforeDriverRegister(rs.getString("IMG_BEFORE_DRIVER_REGIST"));
        db.setImgAfterDriverRegister(rs.getString("IMG_AFTER_DRIVER_REGIST"));
        db.setImgBeforeInsurance(rs.getString("IMG_BEFORE_INSURRANCE"));
        db.setImgAfterInsurance(rs.getString("IMG_AFTER_INSURRANCE"));

        db.setDriverLicense(rs.getString("DRIVER_LICENSE"));
        db.setSpeciesLicenseType(rs.getInt("SPECIES_LICENSE_TYPE"));
        db.setImgBeforeDriverLicense(rs.getString("IMG_BEFORE_DRIVER_LICENSE"));
        db.setImgAfterDriverLicense(rs.getString("IMG_AFTER_DRIVER_LICENSE"));

        return db;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT ")
                .append("  SP.ID ")
                .append(" ,SP.UTIMESTAMP ")

                .append(" ,SP.NUMBER_ID_CARD ")
                .append(" ,SP.DATE_ID_CARD ")
                .append(" ,SP.PLACE_ID_CARD ")
                .append(" ,SP.IMG_BEFORE_ID_CARD ")
                .append(" ,SP.IMG_AFTER_ID_CARD ")

                .append(" ,SP.VEHICLE_TYPE ")
                .append(" ,SP.LICENSE_PLATE ")
                .append(" ,SP.IMG_BEFORE_DRIVER_REGIST ")
                .append(" ,SP.IMG_AFTER_DRIVER_REGIST ")
                .append(" ,SP.IMG_BEFORE_INSURRANCE ")
                .append(" ,SP.IMG_AFTER_INSURRANCE ")

                .append(" ,SP.DRIVER_LICENSE ")
                .append(" ,SP.SPECIES_LICENSE_TYPE ")
                .append(" ,SP.IMG_BEFORE_DRIVER_LICENSE ")
                .append(" ,SP.IMG_AFTER_DRIVER_LICENSE ")

                .append(" FROM SHIPPER_PROFILES SP ")
                .append(" LEFT JOIN APP_USERS AU ON AU.ID = SP.PROFILE_ID ")
                .append(" WHERE AU.PHONE = :PHONE ")
                .append("    AND AU.SHIP = 1 ");

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
        params = new HashMap<>();
        params.put("PHONE", phone);
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
    }
}
