package vn.vmg.ptdv.hola.infra.common.bank;

import vn.vmg.ptdv.hola.infra.account.factory.BankDB;
import vn.vmg.ptdv.hola.infra.account.sql.FindBank;
import vn.vmg.ptdv.hola.infra.common.bank.context.BankInfraContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class BankJDBC {
    private final DataSource dataSource;
    private BankInfraContext jdbcContext;

    public BankJDBC(DataSource ds) {
        this.dataSource = ds;
    }

    public List<BankDB> findBank() {
        this.jdbcContext = new BankInfraContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new FindBank())
                .withParams()
                .executeCommand();
        if (result.size() > 0) {
            return (List<BankDB>) result;
        }
        return new ArrayList<>();
    }
}
