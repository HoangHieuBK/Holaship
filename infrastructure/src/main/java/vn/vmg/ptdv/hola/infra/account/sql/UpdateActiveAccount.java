package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.HolaAccountDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class UpdateActiveAccount extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;
    private final Integer ship;
    private final Integer shop;

    public UpdateActiveAccount(Integer ship, Integer shop) {
        this.ship = ship;
        this.shop = shop;
    }

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" UPDATE APP_USERS SET ")
                .append("  UTIMESTAMP = SYSTIMESTAMP ");
        if (ship != null && ship != 1) {
            builder.append("  ,SHIP = 0 ");
        }
        if (shop != null) {
            builder.append("  ,SHOP = 0 ");
        }

        builder.append(" WHERE ID = :ID ");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(this.prepareSQL());
        this.declareParameters();
        compile();
        int created = this.updateByNamedParam(params);
        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        HolaAccountDB holaAccountDB = (HolaAccountDB) parameters[0];
        params = new HashMap<>();
        params.put("ID", holaAccountDB.getId());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("ID", Types.NUMERIC));
    }
}
