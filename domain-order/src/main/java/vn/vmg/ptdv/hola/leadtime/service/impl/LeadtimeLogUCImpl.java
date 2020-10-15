package vn.vmg.ptdv.hola.leadtime.service.impl;

import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLog;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeLogList;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogListResponse;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeLogRepository;
import vn.vmg.ptdv.hola.leadtime.service.usecase.LeadtimeLogUC;

import java.util.List;

public class LeadtimeLogUCImpl implements LeadtimeLogUC {

    private final LeadtimeLogRepository leadtimeLogRepository;

    private List<LeadtimeLog> leadtimeLogs;

    public LeadtimeLogUCImpl(LeadtimeLogRepository leadtimeLogRepository) {
        this.leadtimeLogRepository = leadtimeLogRepository;
    }

    @Override
    public LeadtimeLogUC getAll(LeadtimeLogSearch leadtimeLogSearch, PagingSortFilter pagingSortFilter) {
        leadtimeLogs = leadtimeLogRepository.findAll(leadtimeLogSearch, pagingSortFilter);
        return this;
    }

    @Override
    public LeadtimeLogListResponse endGetAll() {
        LeadtimeLogList leadtimeLogList = new LeadtimeLogList();
        leadtimeLogList.setLeadtimeLogs(leadtimeLogs);

        LeadtimeLogListResponse response = new LeadtimeLogListResponse();
        response.setLeadtimeLogList(leadtimeLogList);
        return response;
    }

}
