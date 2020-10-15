package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.infra.leadtime.context.LeadtimeRouteInfraContext;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.leadtime.sql.*;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.util.List;

public class LeadtimeRouteJDBC {

    private final DataSource dataSource;
    private LeadtimeRouteInfraContext jdbcContext;

    public LeadtimeRouteJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeadtimeRouteDB> findAll(LeadtimeRouteSearch leadtimeRouteSearch, PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new LeadtimeRouteInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindLeadtimeRoutes(pagingSortFilter))
                .withParams(leadtimeRouteSearch, pagingSortFilter)
                .executeCommand();

        return (List<LeadtimeRouteDB>) rs;
    }

    public LeadtimeRouteDB findById(Long id) {
        this.jdbcContext = new LeadtimeRouteInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindLeadtimeRouteById())
                .withParams(id)
                .executeCommand();
        if (rs.size() == 0) {
            return null;
        }

        return (LeadtimeRouteDB) rs.get(0);
    }

    public int create(LeadtimeRouteDB leadtimeRouteDB, LeadtimeRouteId leadtimeRouteId) {
        this.jdbcContext = new LeadtimeRouteInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new CreateLeadTimeRoute())
                .withParams(leadtimeRouteDB)
                .executeUpdate(leadtimeRouteId);
        return executedRecord;
    }

    public int update(LeadtimeRouteDB leadtimeRouteDB, LeadtimeRouteId leadtimeRouteId) {
        this.jdbcContext = new LeadtimeRouteInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new UpdateLeadTimeRoute())
                .withParams(leadtimeRouteDB)
                .executeUpdate(leadtimeRouteId);
        return executedRecord;
    }

    public int delete(Long id) {
        this.jdbcContext = new LeadtimeRouteInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new DeleteLeadtimeRoute())
                .withParams(id)
                .executeUpdate(null);
        return executedRecord;
    }

}
