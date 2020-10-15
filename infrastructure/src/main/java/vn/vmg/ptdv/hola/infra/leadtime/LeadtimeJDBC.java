package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.infra.leadtime.context.LeadtimeInfraContext;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.leadtime.factory.TotalRecordDB;
import vn.vmg.ptdv.hola.infra.leadtime.sql.*;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public class LeadtimeJDBC {

    private final DataSource dataSource;
    private LeadtimeInfraContext jdbcContext;

    public LeadtimeJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeadtimeDB> findAll(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindLeadtimes())
                .withParams(leadtimeSearch, pagingSortFilter)
                .executeCommand();

        return (List<LeadtimeDB>) rs;
    }

    public LeadtimeDB findById(Long id) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindLeadtimeById())
                .withParams(id)
                .executeCommand();
        if (rs.size() == 0) {
            return null;
        }

        return (LeadtimeDB) rs.get(0);
    }

    public TotalRecordDB getTotal(LeadtimeSearch leadtimeSearch) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        List<TotalRecordDB> totalRecord = (List<TotalRecordDB>) jdbcContext
                .withSQLCommand(new CountLeadtimes())
                .withParams(leadtimeSearch)
                .executeCommand();
        return (totalRecord.isEmpty() ? new TotalRecordDB() : totalRecord.get(0));
    }

    public List<LeadtimeDB> getDataSuggest(LeadtimeSearch leadtimeSearch, PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindListSuggestData(pagingSortFilter))
                .withParams(leadtimeSearch, pagingSortFilter)
                .executeCommand();

        return (List<LeadtimeDB>) rs;
    }

    public int create(LeadtimeDB leadtimeDB, LeadtimeId leadtimeId) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new CreateLeadtime())
                .withParams(leadtimeDB)
                .executeUpdate(leadtimeId);
        return executedRecord;
    }

    public int update(LeadtimeDB leadtimeDB) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new UpdateLeadtime())
                .withParams(leadtimeDB)
                .executeUpdate(null);
        return executedRecord;
    }

    public int changeStatus(Long id, int status, Timestamp uTimestamp) {
        this.jdbcContext = new LeadtimeInfraContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new ChangeStatusLeadtime())
                .withParams(id, status, uTimestamp)
                .executeUpdate(null);
        return executedRecord;
    }

}
