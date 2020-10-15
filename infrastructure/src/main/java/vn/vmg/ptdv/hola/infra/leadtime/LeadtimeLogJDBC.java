package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.infra.leadtime.context.LeadtimeLogInfraContext;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeLogDB;
import vn.vmg.ptdv.hola.infra.leadtime.sql.FindLeadtimeLogs;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.util.List;

public class LeadtimeLogJDBC {

    private final DataSource dataSource;
    private LeadtimeLogInfraContext jdbcContext;

    public LeadtimeLogJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeadtimeLogDB> findAll(LeadtimeLogSearch leadtimeLogSearch, PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new LeadtimeLogInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindLeadtimeLogs())
                .withParams(leadtimeLogSearch, pagingSortFilter)
                .executeCommand();

        return (List<LeadtimeLogDB>) rs;
    }

}
