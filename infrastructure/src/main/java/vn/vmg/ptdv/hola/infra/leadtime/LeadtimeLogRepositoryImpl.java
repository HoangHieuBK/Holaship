package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeLogDB;
import vn.vmg.ptdv.hola.infra.leadtime.mapper.LeadtimeLogDBMapper;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLog;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeLogRepository;

import java.util.ArrayList;
import java.util.List;

public class LeadtimeLogRepositoryImpl implements LeadtimeLogRepository {

    private final LeadtimeLogJDBC jdbc;

    public LeadtimeLogRepositoryImpl(LeadtimeLogJDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<LeadtimeLog> findAll(LeadtimeLogSearch leadtimeLogSearch, PagingSortFilter pagingSortFilter) {
        List<LeadtimeLogDB> leadtimeLogDBs = jdbc.findAll(leadtimeLogSearch, pagingSortFilter);
        List<LeadtimeLog> result = new ArrayList<>();
        if (!leadtimeLogDBs.isEmpty()) {
            for (LeadtimeLogDB leadtimeLogDB : leadtimeLogDBs
            ) {
                result.add(LeadtimeLogDBMapper.getInstance().fromDB(leadtimeLogDB));
            }
        }
        return result;
    }

}
