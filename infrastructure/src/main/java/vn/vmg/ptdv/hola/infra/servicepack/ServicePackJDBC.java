package vn.vmg.ptdv.hola.infra.servicepack;


import vn.vmg.ptdv.hola.infra.leadtime.context.LeadtimeInfraContext;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.leadtime.sql.ChangeStatusLeadtime;
import vn.vmg.ptdv.hola.infra.leadtime.sql.CreateLeadtime;
import vn.vmg.ptdv.hola.infra.leadtime.sql.UpdateLeadtime;
import vn.vmg.ptdv.hola.infra.servicepack.context.ServicePackContext;
import vn.vmg.ptdv.hola.infra.servicepack.factory.CountRecord;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.servicepack.sql.*;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.shared.BaseFilter;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ServicePackJDBC {

    private final DataSource dataSource;
    private ServicePackContext jdbcContext;

    public ServicePackJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ServicePackDB> findAll(ServicePackSearch servicePackSearch, PagingSortFilter pagingSortFilter) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindServicePacks())
                .withParams(servicePackSearch, pagingSortFilter)
                .executeCommand();

        return (List<ServicePackDB>) rs;
    }

    public ServicePackDB findByCode(String code) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindServicePackByCode())
                .withParams(code)
                .executeCommand();
        if (rs.size() == 0) {
            return null;
        }

        return (ServicePackDB) rs.get(0);
    }

    public int create(ServicePackDB servicePackDB, ServicePackID servicePackID) {
        this.jdbcContext = new ServicePackContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new CreateServicePack())
                .withParams(servicePackDB)
                .executeUpdate(servicePackID);
        return executedRecord;
    }

    public int update(ServicePackDB servicePackDB) {
        this.jdbcContext = new ServicePackContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new UpdateServicePack())
                .withParams(servicePackDB)
                .executeUpdate(null);
        return executedRecord;
    }

    public int changeStatus(Long id, int status, Timestamp uTimestamp) {
        this.jdbcContext = new ServicePackContext(dataSource);
        int executedRecord = jdbcContext
                .withSQLCommand(new ChangeStatusServicePack())
                .withParams(id, status, uTimestamp)
                .executeUpdate(null);
        return executedRecord;
    }


















    public List<ServicePackDB> findListServicePack(ServicePackSearch servicePackSearch) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> result = jdbcContext.withSQLCommand(new ListServicePackAutoSuggestSQL())
                .withParams(servicePackSearch)
                .executeCommand();
        return (result.isEmpty() || result == null) ? Collections.emptyList() : (List<ServicePackDB>) result;
    }

    public Integer countListServicePack(ServicePackSearch servicePackSearch) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> result = jdbcContext.withSQLCommand(new CountListServicePackSQL())
                .withParams(servicePackSearch)
                .executeCommand();
        List<CountRecord> countRecords = (List<CountRecord>) result;
        return countRecords.get(0).getTotalRecords();
    }

//    public List<ServicePackDB> listServicePack(ServicePackSearch servicePackSearch, BaseFilter baseFilter) {
//        this.jdbcContext = new ServicePackContext(dataSource);
//        List<?> result = jdbcContext.withSQLCommand(new ListServicePackSQL(baseFilter))
//                .withParams(servicePackSearch)
//                .executeCommand();
//        return (result.isEmpty() || result == null) ? Collections.emptyList() : (List<ServicePackDB>) result;
//    }

    public List<ServicePackDB> findListHistoryServicePack(Long id, LocalDate effectiveAt, BaseFilter baseFilter) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> result = jdbcContext.withSQLCommand(new ListHistoryServicePackSQL())
                .withParams(id, effectiveAt, baseFilter)
                .executeCommand();
        return (result.isEmpty() || result == null) ? Collections.emptyList() : (List<ServicePackDB>) result;
    }

    public Integer countTotalByHistoryServicePack(Long id, LocalDate effectiveAt) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new CountHistoryServicePackSQL())
                .withParams(id, effectiveAt)
                .executeCommand();
        List<Integer> countRecords = (List<Integer>) result;
        return countRecords.get(0);
    }

    public Optional<ServicePackDB> findServicePackById(int id) {
        this.jdbcContext = new ServicePackContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new FindServicePackByIdSQL())
                .withParams(id)
                .executeCommand();
        if (result.size() == 0) {
            return Optional.empty();
        }
        return Optional.of((ServicePackDB) result.get(0));
    }

    public Optional<ServicePackDB> findServicePackByCode(String code) {
        List<?> result = jdbcContext.withSQLCommand(new FindServicePackByCodeSQL())
                .withParams(code)
                .executeCommand();
        return result.isEmpty() ? Optional.empty() : Optional.of((ServicePackDB) result.get(0));
    }

    public Integer activeOrDeactive(ServicePackDB servicePackDB) {
        List<?> result = jdbcContext.withSQLCommand(new ActiveOrDeactiveSQL())
                .withParams(servicePackDB)
                .executeCommand();
        List<Integer> list = (List<Integer>) result;
        return list.get(0);
    }

    public long saveServicePack(ServicePackDB servicePackDB, ServicePackID servicePackId) {
        this.jdbcContext = new ServicePackContext(dataSource);
        long created = jdbcContext
                .withSQLCommand(new CreateServicePackSQL())
                .withParams(servicePackDB)
                .executeUpdate(servicePackId);
        return created;
    }

    public int saveServicePackSetting(ServicePackDB servicePack) {
        this.jdbcContext = new ServicePackContext(dataSource);
        int created = jdbcContext
                .withSQLCommand(new CreateServicePackSettingSQL())
                .withParams(servicePack)
                .executeUpdate(null);
        return created;
    }
}
