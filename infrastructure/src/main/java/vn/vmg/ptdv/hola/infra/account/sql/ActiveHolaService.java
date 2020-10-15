package vn.vmg.ptdv.hola.infra.account.sql;

import org.springframework.jdbc.object.SqlUpdate;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.infra.shared.UpdateSQLCommand;

import javax.sql.DataSource;

public class ActiveHolaService extends SqlUpdate implements UpdateSQLCommand<AccountId> {
    @Override
    public String prepareSQL() {
        return null;
    }

    @Override
    public int updateCommand(DataSource dataSource, AccountId accountId) {
        return 0;
    }

    @Override
    public void prepareParams(Object... parameters) {

    }

    @Override
    public void declareParameters() {

    }
}