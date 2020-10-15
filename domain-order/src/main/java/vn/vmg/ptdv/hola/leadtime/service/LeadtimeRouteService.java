package vn.vmg.ptdv.hola.leadtime.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteSearchRequest;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeRouteCreateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeRouteDeleteUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeRouteGetUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeRouteUpdateUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteCreateUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteDeleteUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteGetUC;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeRouteUpdateUC;

public class LeadtimeRouteService {

    private final LeadtimeRouteRepository leadtimeRouteRepository;

    public LeadtimeRouteService(LeadtimeRouteRepository leadtimeRouteRepository) {
        this.leadtimeRouteRepository = leadtimeRouteRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeRouteListResponse getAll(LeadtimeRouteSearchRequest request) {
        LeadtimeRouteGetUC useCase = new LeadtimeRouteGetUCImpl(leadtimeRouteRepository);
        LeadtimeRouteListResponse response = useCase
                .getAll(request.getLeadtimeRouteSearch(), request.getPagingSortFilter())
                .endGetAll();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeRouteResponse getById(LeadtimeRouteSearchRequest request) {
        LeadtimeRouteGetUC useCase = new LeadtimeRouteGetUCImpl(leadtimeRouteRepository);
        LeadtimeRouteResponse response = useCase
                .getById(request.getLeadtimeRouteSearch().getLeadtimeRouteId())
                .endGetById();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeRouteResponse create(LeadtimeRouteRequest request) throws OrderDomainException {
        LeadtimeRouteCreateUC useCase = new LeadtimeRouteCreateUCImpl(leadtimeRouteRepository);
        LeadtimeRouteResponse response = useCase
                .create(request.getLeadtimeRouteId(), request.getFromProvince(), request.getToProvince(),
                        request.getFromDistrict(), request.getToDistrict(), request.getWeight(),
                        request.getLeadtimeId(), request.getType(), request.getStatus(),
                        request.getEstimatedDeliveryDay(), request.getVehicle())
                .fail()
                .end();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeRouteResponse update(LeadtimeRouteRequest request) throws OrderDomainException {
        LeadtimeRouteUpdateUC useCase = new LeadtimeRouteUpdateUCImpl(leadtimeRouteRepository);
        LeadtimeRouteResponse response = useCase
                .update(request.getLeadtimeRouteId(), request.getFromProvince(), request.getToProvince(),
                        request.getFromDistrict(), request.getToDistrict(), request.getWeight(),
                        request.getLeadtimeId(), request.getType(), request.getStatus(),
                        request.getEstimatedDeliveryDay(), request.getVehicle(), request.getAuditLog())
                .fail()
                .end();
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public LeadtimeRouteResponse delete(Long id) throws OrderDomainException {
        LeadtimeRouteDeleteUC useCase = new LeadtimeRouteDeleteUCImpl(leadtimeRouteRepository);
        LeadtimeRouteResponse response = useCase
                .delete(id)
                .fail()
                .end();
        return response;
    }

}
