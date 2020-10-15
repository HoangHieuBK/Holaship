package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.HolaAccountDB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class SavingHolaAccount extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO APP_USERS (")
                .append(" PHONE, EMAIL, SHOP, SHIP, UTIMESTAMP")
                .append(" ) VALUES ( ")
                .append(" :PHONE")
                .append(" ,:EMAIL")
                .append(" ,:SHOP")
                .append(" ,:SHIP")
                .append(" ,SYSTIMESTAMP)");
        return builder.toString();
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        this.setDataSource(dataSource);
        this.setSql(prepareSQL());
        this.declareParameters();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        setGeneratedKeysColumnNames("ID");
        setReturnGeneratedKeys(true);
        compile();
        int created = this.updateByNamedParam(params, keyHolder);
        if (created > 0) {
            accountId.setId(keyHolder.getKey().longValue());
        }

        return created;
    }

    @Override
    public void prepareParams(Object... parameters) {
        HolaAccountDB holaAccountDB = (HolaAccountDB) parameters[0];
        params = new HashMap<>();
        params.put("PHONE", holaAccountDB.getPhone());
        params.put("EMAIL", holaAccountDB.getEmail());
        params.put("SHIP", holaAccountDB.getShip());
        params.put("SHOP", holaAccountDB.getShop());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("PHONE", Types.VARCHAR));
        declareParameter(new SqlParameter("EMAIL", Types.VARCHAR));
        declareParameter(new SqlParameter("SHIP", Types.NUMERIC));
        declareParameter(new SqlParameter("SHOP", Types.NUMERIC));
    }
}
