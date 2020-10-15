package vn.vmg.ptdv.hola.cms.rest.leadtimeRoute;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.LeadtimeRouteJsonMapper;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteSearchRequest;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeRouteService;

@RestController
public class LeadtimeRouteController {

    private static final String LEADTIME_ROUTES = "/leadtimeRoutes";

    private final LeadtimeRouteService leadtimeRouteService;

    public LeadtimeRouteController(LeadtimeRouteService leadtimeRouteService) {
        this.leadtimeRouteService = leadtimeRouteService;
    }

    @GetMapping(LEADTIME_ROUTES + "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws OrderDomainException {
        LeadtimeRouteSearch leadtimeRouteSearch = new LeadtimeRouteSearch().withLeadtimeRouteId(id, null);
        LeadtimeRouteSearchRequest request = new LeadtimeRouteSearchRequest(leadtimeRouteSearch, null);
        LeadtimeRouteResponse response = leadtimeRouteService.getById(request);
        LeadtimeRouteJSONResponse jsonResponse = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteResponse(response);
        APIResponse<LeadtimeRouteJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime_route.success.get_data");
    }

    @PostMapping(LEADTIME_ROUTES)
    public ResponseEntity<?> create(@RequestBody LeadtimeRouteJSONRequest jsonRequest) throws OrderDomainException {
        LeadtimeRouteRequest request = new LeadtimeRouteRequest(jsonRequest.getId(), jsonRequest.getLeadtimeId(),
                jsonRequest.getFromProvinceId(), jsonRequest.getFromProvinceCode(), jsonRequest.getToProvinceId(),
                jsonRequest.getToProvinceCode(), jsonRequest.getFromDistrictId(), jsonRequest.getToDistrictId(),
                jsonRequest.getFromDistrictCode(), jsonRequest.getToDistrictCode(), jsonRequest.getType(), jsonRequest.getStatus(),
                jsonRequest.getEstimatedDeliveryDay(), jsonRequest.getTransportType(), jsonRequest.getLimitWeight(),
                jsonRequest.getMaxWeight(), jsonRequest.getLimitType(), jsonRequest.getUTimestamp());
        LeadtimeRouteResponse response = leadtimeRouteService.create(request);
        LeadtimeRouteJSONResponse jsonResponse = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteResponse(response);
        APIResponse<LeadtimeRouteJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Thêm mới giá lead time thành công");
    }

    @PutMapping(LEADTIME_ROUTES + "/{id}")
    public ResponseEntity<?> update(@RequestBody LeadtimeRouteJSONRequest jsonRequest) throws OrderDomainException {
        LeadtimeRouteRequest request = new LeadtimeRouteRequest(jsonRequest.getId(), jsonRequest.getLeadtimeId(),
                jsonRequest.getFromProvinceId(), jsonRequest.getFromProvinceCode(), jsonRequest.getToProvinceId(),
                jsonRequest.getToProvinceCode(), jsonRequest.getFromDistrictId(), jsonRequest.getToDistrictId(),
                jsonRequest.getFromDistrictCode(), jsonRequest.getToDistrictCode(), jsonRequest.getType(), jsonRequest.getStatus(),
                jsonRequest.getEstimatedDeliveryDay(), jsonRequest.getTransportType(), jsonRequest.getLimitWeight(),
                jsonRequest.getMaxWeight(), jsonRequest.getLimitType(), jsonRequest.getUTimestamp());
        LeadtimeRouteResponse response = leadtimeRouteService.update(request);
        LeadtimeRouteJSONResponse jsonResponse = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteResponse(response);
        APIResponse<LeadtimeRouteJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Chỉnh sửa giá lead time thành công");
    }

    @DeleteMapping(LEADTIME_ROUTES + "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws OrderDomainException {
//        LeadtimeRouteRequest request = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteJsonRequest(jsonRequest);
        LeadtimeRouteResponse response = leadtimeRouteService.delete(id);
        LeadtimeRouteJSONResponse jsonResponse = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteResponse(response);
        APIResponse<LeadtimeRouteJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Xóa giá lead time thành công");
    }

}
