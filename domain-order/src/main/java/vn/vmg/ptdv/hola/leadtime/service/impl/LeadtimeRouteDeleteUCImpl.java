package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteDeleteUC;

public class LeadtimeRouteDeleteUCImpl implements LeadtimeRouteDeleteUC {

    private final LeadtimeRouteRepository leadtimeRouteRepository;

    private LeadtimeRouteResponse leadtimeRouteResponse;

    private LeadtimeRoute leadtimeRoute;

    private int executedRecord;

    public LeadtimeRouteDeleteUCImpl(LeadtimeRouteRepository leadtimeRouteRepository) {
        this.leadtimeRouteRepository = leadtimeRouteRepository;
    }

    @Override
    public LeadtimeRouteDeleteUC delete(Long id) throws OrderDomainException {
        executedRecord = leadtimeRouteRepository.delete(id);
        return this;
    }

    @Override
    public LeadtimeRouteDeleteUC fail() throws OrderDomainException {
        if (executedRecord == 0) {
            throw new OrderDomainException("Xóa leadtime route thất bại");
        }
        return this;
    }

    @Override
    public LeadtimeRouteResponse end() {
        leadtimeRouteResponse = new LeadtimeRouteResponse();
        return leadtimeRouteResponse;
    }

}
