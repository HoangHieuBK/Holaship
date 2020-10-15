package vn.vmg.ptdv.hola.cms.rest.leadtimelog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.LeadtimeLogJsonMapper;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeLogRequest;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeLogService;

@RestController
public class LeadtimeLogController {

    private static final String LEADTIME_LOGS = "/leadtimeLogs";

    private final LeadtimeLogService leadtimeLogService;

    public LeadtimeLogController(LeadtimeLogService leadtimeLogService) {
        this.leadtimeLogService = leadtimeLogService;
    }

    @GetMapping(LEADTIME_LOGS)
    public ResponseEntity<?> getAll(LeadtimeLogJSONRequest jsonRequest) {
        LeadtimeLogRequest request = LeadtimeLogJsonMapper.getInstance().fromLeadtimeLogJsonRequest(jsonRequest);
        LeadtimeLogListResponse response = leadtimeLogService.getAll(request);
        LeadtimeLogListJSONResponse jsonResponse = LeadtimeLogJsonMapper.getInstance().fromLeadtimeLogListResponse(response);
        APIResponse<LeadtimeLogListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime_log.success.get_data");
    }

}
