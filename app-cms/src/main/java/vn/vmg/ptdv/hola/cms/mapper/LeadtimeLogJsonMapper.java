package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.leadtimelog.LeadtimeLogJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.leadtimelog.LeadtimeLogListJSONResponse;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeLogId;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeLogSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogRequest;
import vn.vmg.ptdv.hola.shared.AuditLog;

public class LeadtimeLogJsonMapper {

    private static LeadtimeLogJsonMapper INSTANCE;

    private LeadtimeLogJsonMapper() {
    }

    public static LeadtimeLogJsonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeLogJsonMapper();
        }
        return INSTANCE;
    }

    public LeadtimeLogRequest fromLeadtimeLogJsonRequest(LeadtimeLogJSONRequest leadtimeLogJSONRequest) {
        LeadtimeLogRequest leadtimeLogRequest = new LeadtimeLogRequest();
        if (leadtimeLogJSONRequest != null) {
            LeadtimeLogId leadtimeLogId = new LeadtimeLogId(leadtimeLogJSONRequest.getId());

            LeadtimeLogSearch leadtimeLogSearch = new LeadtimeLogSearch();
            leadtimeLogSearch.setLeadtimeLogId(leadtimeLogId);
            leadtimeLogSearch.setLeadtimeId(new LeadtimeId(leadtimeLogJSONRequest.getLeadtimeId(), null));
            leadtimeLogSearch.setDataUpdated(leadtimeLogJSONRequest.getDataUpdated());
            leadtimeLogSearch.setAuditLog(new AuditLog());

            leadtimeLogRequest.setLeadtimeLogSearch(leadtimeLogSearch);

            PagingSortFilter pagingSortFilter = new PagingSortFilter();
            pagingSortFilter.setAsc(leadtimeLogJSONRequest.getAsc());
            pagingSortFilter.setFieldSort(leadtimeLogJSONRequest.getFieldSort());
            pagingSortFilter.setPageIndex(leadtimeLogJSONRequest.getPageIndex());
            pagingSortFilter.setPageSize(leadtimeLogJSONRequest.getPageSize());

            leadtimeLogRequest.setPagingSortFilter(pagingSortFilter);
        }
        return leadtimeLogRequest;
    }

    public LeadtimeLogListJSONResponse fromLeadtimeLogListResponse(LeadtimeLogListResponse leadtimeLogListResponse) {
        LeadtimeLogListJSONResponse leadtimeLogListJSONResponse = new LeadtimeLogListJSONResponse();
        if (leadtimeLogListResponse != null) {
            leadtimeLogListJSONResponse.setLeadtimeLogs(leadtimeLogListResponse.getLeadtimeLogList().getLeadtimeLogs());
            leadtimeLogListJSONResponse.setPageIndex(leadtimeLogListResponse.getLeadtimeLogList().getPageIndex());
            leadtimeLogListJSONResponse.setPageSize(leadtimeLogListResponse.getLeadtimeLogList().getPageSize());
            leadtimeLogListJSONResponse.setTotalRecord(leadtimeLogListResponse.getLeadtimeLogList().getTotalRecord());
        }
        return leadtimeLogListJSONResponse;
    }

}
