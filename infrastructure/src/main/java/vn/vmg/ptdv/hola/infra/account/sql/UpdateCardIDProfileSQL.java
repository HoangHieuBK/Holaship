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

public class UpdateCardIDProfileSQL extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE SHIPPER_PROFILES ")
                .append("SET")
                .append("    NUMBER_ID_CARD = :NUMBER_ID_CARD ")
                .append("   ,DATE_ID_CARD = :DATE_ID_CARD ")
                .append("   ,PLACE_ID_CARD = :PLACE_ID_CARD ")
                .append("   ,IMG_BEFORE_ID_CARD = :IMG_BEFORE_ID_CARD ")
                .append("   ,IMG_AFTER_ID_CARD = :IMG_AFTER_ID_CARD ")
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
        params.put("NUMBER_ID_CARD", db.getNumberCardID());
        params.put("DATE_ID_CARD", db.getDateCardID());
        params.put("PLACE_ID_CARD", db.getPlaceCardID());
        params.put("IMG_BEFORE_ID_CARD", db.getImgBeforeCardID());
        params.put("IMG_AFTER_ID_CARD", db.getImgAfterCardID());
        params.put("ID", db.getId());
        params.put("UTIMESTAMP", auditLog.getUTimestamp());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("NUMBER_ID_CARD", Types.VARCHAR));
        declareParameter(new SqlParameter("DATE_ID_CARD", Types.DATE));
        declareParameter(new SqlParameter("PLACE_ID_CARD", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_BEFORE_ID_CARD", Types.VARCHAR));
        declareParameter(new SqlParameter("IMG_AFTER_ID_CARD", Types.VARCHAR));
        declareParameter(new SqlParameter("ID", Types.NUMERIC));
        declareParameter(new SqlParameter("UTIMESTAMP", Types.TIMESTAMP_WITH_TIMEZONE));
    }
}
