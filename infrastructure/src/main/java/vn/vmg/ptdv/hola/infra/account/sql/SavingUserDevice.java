package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.UserDevice;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class SavingUserDevice extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO APP_USER_DEVICES (")
                .append("  APP_USER_ID, DEVICE_TOKEN, SHOP, SHIP, CONFIRM, UTIMESTAMP")
                .append(" ) VALUES ( ")
                .append(" :APP_USER_ID")
                .append(" ,:DEVICE_TOKEN")
                .append(" ,:SHOP")
                .append(" ,:SHIP")
                .append(" ,:CONFIRM")
                .append(" ,SYSTIMESTAMP")
                .append(" )");
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
        UserDevice userDevice = (UserDevice) parameters[0];
        params = new HashMap<>();
        params.put("APP_USER_ID", userDevice.getAppUserId());
        params.put("DEVICE_TOKEN", userDevice.getDeviceToken());
        params.put("SHOP", userDevice.getShop());
        params.put("SHIP", userDevice.getShip());
        params.put("CONFIRM", userDevice.getConfirm());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter("APP_USER_ID", Types.NUMERIC));
        declareParameter(new SqlParameter("DEVICE_TOKEN", Types.VARCHAR));
        declareParameter(new SqlParameter("SHOP", Types.NUMERIC));
        declareParameter(new SqlParameter("SHIP", Types.NUMERIC));
        declareParameter(new SqlParameter("CONFIRM", Types.VARCHAR));
    }
}
