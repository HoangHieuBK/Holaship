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

public class UpdateVehicleProfileSQL extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE SHIPPER_PROFILES ")
                .append("SET")
                .append("    VEHICLE_TYPE = :VEHICLE_TYPE ")
                .append("   ,LICENSE_PLATE = :LICENSE_PLATE ")
                .append("   ,IMG_BEFORE_DRIVER_REGIST = :IMG_BEFORE_DRIVER_REGIST ")
                .append("   ,IMG_AFTER_DRIVER_REGIST = :IMG_AFTER_DRIVER_REGIST ")
                .append("   ,IMG_BEFORE_INSURRANCE = :IMG_BEFORE_INSURRANCE ")
                .append("   ,IMG_AFTER_INSURRANCE = :IMG_AFTER_INSURRANCE ")
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
        params.put("VEHICLE_TYPE", db.getVehicleType());
        params.put("LICENSE_PLATE", db.getLicensePlace());
        params.put("IMG_BEFORE_DRIVER_REGIST", db.getImgBeforeDriverRegister());
        params.put("IMG_AFTER_DRIVER_REGIST", db.getImgAfterDriverRegister());
        params.put("IMG_BEFORE_INSURRANCE", db.getImgBeforeInsurance());
        params.put("IMG_AFTER_INSURRANCE", db.getImgAfterInsurance());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("ID", Types.NUMERIC));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP));
        declareParameter(new SqlParameter("VEHICLE_TYPE", Types.NUMERIC));
        declareParameter(new SqlParameter("LICENSE_PLATE", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_BEFORE_DRIVER_REGIST", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_AFTER_DRIVER_REGIST", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_BEFORE_INSURRANCE", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_AFTER_INSURRANCE", Types.VARCHAR));
    }
}
