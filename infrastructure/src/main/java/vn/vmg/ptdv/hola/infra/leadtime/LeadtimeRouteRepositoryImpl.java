package vn.vmg.ptdv.hola.infra.leadtime;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.infra.leadtime.mapper.LeadtimeRouteDBMapper;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.util.ArrayList;
import java.util.List;

public class LeadtimeRouteRepositoryImpl implements LeadtimeRouteRepository {

    private final LeadtimeRouteJDBC jdbc;

    public LeadtimeRouteRepositoryImpl(LeadtimeRouteJDBC jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<LeadtimeRoute> findAll(LeadtimeRouteSearch leadtimeRouteSearch, PagingSortFilter pagingSortFilter) {
        List<LeadtimeRouteDB> dbRs = jdbc.findAll(leadtimeRouteSearch, pagingSortFilter);

        List<LeadtimeRoute> rs = new ArrayList<>();
        for (LeadtimeRouteDB obj : dbRs) {
            LeadtimeRoute leadtimeRoute = LeadtimeRouteDBMapper.getInstance().fromDB(obj);
            rs.add(leadtimeRoute);
        }
        return rs;
    }

    @Override
    public LeadtimeRoute findById(Long id) {
        LeadtimeRouteDB dbRs = jdbc.findById(id);
        if (dbRs == null) {
            return null;
        }
        LeadtimeRoute rs = LeadtimeRouteDBMapper.getInstance().fromDB(dbRs);
        return rs;
    }

    @Override
    public int create(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince, AddressId fromDistrict, AddressId toDistrict, Weight weight,
                      LeadtimeId leadtimeId, Integer type, Integer status, Integer estimatedDeliveryDay, Vehicle vehicle) {
        LeadtimeRouteDB leadtimeRouteDB = new LeadtimeRouteDB(leadtimeRouteId, fromProvince, toProvince, fromDistrict, toDistrict, weight,
                leadtimeId, type, status, estimatedDeliveryDay, vehicle, null);
        return jdbc.create(leadtimeRouteDB, leadtimeRouteId);
    }

    @Override
    public int update(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince, AddressId fromDistrict, AddressId toDistrict, Weight weight,
                      LeadtimeId leadtimeId, Integer type, Integer status, Integer estimatedDeliveryDay, Vehicle vehicle, AuditLog auditLog) {
        LeadtimeRouteDB leadtimeRouteDB = new LeadtimeRouteDB(leadtimeRouteId, fromProvince, toProvince, fromDistrict, toDistrict, weight,
                leadtimeId, type, status, estimatedDeliveryDay, vehicle, auditLog);
        return jdbc.update(leadtimeRouteDB, leadtimeRouteId);
    }

    @Override
    public int delete(Long id) {
        return jdbc.delete(id);
    }

}
