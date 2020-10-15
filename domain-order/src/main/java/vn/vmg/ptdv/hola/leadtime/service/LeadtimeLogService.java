package vn.vmg.ptdv.hola.leadtime.service;

import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogRequest;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeLogRepository;
import vn.vmg.ptdv.hola.leadtime.service.impl.LeadtimeLogUCImpl;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeLogUC;

public class LeadtimeLogService {

    private final LeadtimeLogRepository leadtimeLogRepository;

    public LeadtimeLogService(LeadtimeLogRepository leadtimeLogRepository) {
        this.leadtimeLogRepository = leadtimeLogRepository;
    }

    public LeadtimeLogListResponse getAll(LeadtimeLogRequest request) {
        LeadtimeLogUC useCase = new LeadtimeLogUCImpl(leadtimeLogRepository);
        LeadtimeLogListResponse response = useCase
                .getAll(request.getLeadtimeLogSearch(), request.getPagingSortFilter())
                .endGetAll();
        return response;
    }

}
