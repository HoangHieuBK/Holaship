package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.ShipperProfileDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class SavingShipperProfile extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ")
                .append("  SHIPPER_PROFILES (")
                .append("     PROFILE_ID")
                .append("    ,UTIMESTAMP")
                .append(" )VALUES( ")
                .append("    :PROFILE_ID ")
                .append("    ,SYSTIMESTAMP")
                .append(" )");
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
        ShipperProfileDB shipperProfileDB = (ShipperProfileDB) parameters[0];
        params = new HashMap<>();
        params.put("PROFILE_ID", shipperProfileDB.getAppUserId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PROFILE_ID", Types.NUMERIC));
    }
}