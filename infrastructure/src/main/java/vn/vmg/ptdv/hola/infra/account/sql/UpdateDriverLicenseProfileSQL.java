package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.infra.account.factory.ShipperInfoDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class UpdateDriverLicenseProfileSQL extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE SHIPPER_PROFILES ")
                .append("SET")
                .append("    DRIVER_LICENSE = :DRIVER_LICENSE ")
                .append("   ,SPECIES_LICENSE_TYPE = :SPECIES_LICENSE_TYPE ")
                .append("   ,IMG_BEFORE_DRIVER_LICENSE = :IMG_BEFORE_DRIVER_LICENSE ")
                .append("   ,IMG_AFTER_DRIVER_LICENSE = :IMG_AFTER_DRIVER_LICENSE ")
                .append("   ,UTIMESTAMP = SYSTIMESTAMP ")
                .append(" WHERE UTIMESTAMP = :UTIMESTAMP AND ID = :ID ");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        return this.updateByNamedParam(params);
    }

    @Override
    public void prepareParams(Object... parameters) {
        ShipperInfoDB db = (ShipperInfoDB) parameters[0];
        AuditLog auditLog = (AuditLog) parameters[1];
        params = new HashMap<>();
        params.put("ID", db.getId());
        params.put("UTIMESTAMP", auditLog.getUTimestamp());
        params.put("DRIVER_LICENSE", db.getDriverLicense());
        params.put("SPECIES_LICENSE_TYPE", db.getSpeciesLicenseType());
        params.put("IMG_BEFORE_DRIVER_LICENSE", db.getImgBeforeDriverLicense());
        params.put("IMG_AFTER_DRIVER_LICENSE", db.getImgAfterDriverLicense());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("ID", Types.NUMERIC));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP));
        declareParameter(new SqlParameter("DRIVER_LICENSE", Types.VARCHAR));
        declareParameter(new SqlParameter("SPECIES_LICENSE_TYPE", Types.NUMERIC));
        declareParameter(new SqlParameter("IMG_BEFORE_DRIVER_LICENSE", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_AFTER_DRIVER_LICENSE", Types.VARCHAR));
    }
}
