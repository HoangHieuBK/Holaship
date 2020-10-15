package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.account.factory.VADB;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class CreateVAAccount extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    private Map<String, Object> params;

    @Override
    public String prepareSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO VA_WALLETS (")
                .append(" USER_ID, USER_NAME, SHIP_CODE, SHOP_CODE , BANK_CODE , BANK_NAME, UTIMESTAMP ")
                .append(" ) VALUES ( ")
                .append(" :" + VADB.USER_ID)
                .append(", :" + VADB.USER_NAME)
                .append(", :" + VADB.SHIP_CODE)
                .append(", :" + VADB.SHOP_CODE)
                .append(", :" + VADB.BANK_CODE)
                .append(", :" + VADB.BANK_NAME)
                .append(", SYSTIMESTAMP )");
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
        VADB vaAccountDB = (VADB) parameters[0];
        params = new HashMap<String, Object>();
        params.put(VADB.USER_ID, vaAccountDB.getUserId());
        params.put(VADB.USER_NAME, vaAccountDB.getUserName());
        params.put(VADB.SHIP_CODE, vaAccountDB.getShipCode());
        params.put(VADB.SHOP_CODE, vaAccountDB.getShopCode());
        params.put(VADB.BANK_NAME, vaAccountDB.getBankName());
        params.put(VADB.BANK_CODE, vaAccountDB.getBankCode());
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(VADB.USER_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(VADB.USER_NAME, Types.NVARCHAR));
        declareParameter(new SqlParameter(VADB.SHIP_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(VADB.SHOP_CODE, Types.VARCHAR));
        declareParameter(new SqlParameter(VADB.BANK_NAME, Types.NVARCHAR));
        declareParameter(new SqlParameter(VADB.BANK_CODE, Types.VARCHAR));
    }

}
