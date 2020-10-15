package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.mapper.LeadtimeRouteMapper;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteGetUC;

import java.util.List;

public class LeadtimeRouteGetUCImpl implements LeadtimeRouteGetUC {

    private final LeadtimeRouteRepository leadtimeRouteRepository;

    private LeadtimeRouteResponse leadtimeRouteResponse;

    private List<LeadtimeRoute> leadtimeRoutes;

    private LeadtimeRoute leadtimeRoute;

    public LeadtimeRouteGetUCImpl(LeadtimeRouteRepository leadtimeRouteRepository) {
        this.leadtimeRouteRepository = leadtimeRouteRepository;
    }

    @Override
    public LeadtimeRouteGetUC getAll(LeadtimeRouteSearch leadtimeRouteSearch, PagingSortFilter pagingSortFilter) {
        leadtimeRoutes = leadtimeRouteRepository.findAll(leadtimeRouteSearch, pagingSortFilter);
        return this;
    }

    @Override
    public LeadtimeRouteGetUC getById(LeadtimeRouteId leadtimeRouteId) {
        leadtimeRoute = leadtimeRouteRepository.findById(leadtimeRouteId.getId());
        return this;
    }

    @Override
    public LeadtimeRouteListResponse endGetAll() {
        LeadtimeRouteListResponse response = new LeadtimeRouteListResponse();
        response.setLeadtimeRoutes(leadtimeRoutes);
        return response;
    }

    @Override
    public LeadtimeRouteResponse endGetById() {
        LeadtimeRouteResponse response = LeadtimeRouteMapper.getInstance().fromLeadtimeRoute(leadtimeRoute);
        return response;
    }

}
