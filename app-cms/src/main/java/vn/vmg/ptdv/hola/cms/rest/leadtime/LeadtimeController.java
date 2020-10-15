package vn.vmg.ptdv.hola.cms.rest.leadtime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.DistrictJsonMapper;
import vn.vmg.ptdv.hola.cms.mapper.LeadtimeJsonMapper;
import vn.vmg.ptdv.hola.cms.mapper.LeadtimeRouteJsonMapper;
import vn.vmg.ptdv.hola.cms.mapper.ProvinceJsonMapper;
import vn.vmg.ptdv.hola.district.presentation.DistrictListResponse;
import vn.vmg.ptdv.hola.district.presentation.DistrictSearchRequest;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.presentation.*;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeRouteService;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeService;
import vn.vmg.ptdv.hola.province.presentation.ProvinceListResponse;
import vn.vmg.ptdv.hola.province.presentation.ProvinceSearchRequest;

@RestController
public class LeadtimeController {

    private static final String LEADTIMES = "/leadtimes";
    private static final String LEADTIME_ROUTES = "/leadtimeRoutes";
    private static final String OTHER_DATA = "/otherData";
    
    private final LeadtimeService leadtimeService;
    private final LeadtimeRouteService leadtimeRouteService;

    public LeadtimeController(LeadtimeService leadtimeService, LeadtimeRouteService leadtimeRouteService) {
        this.leadtimeService = leadtimeService;
        this.leadtimeRouteService = leadtimeRouteService;
    }

    @GetMapping(LEADTIMES)
    public ResponseEntity<?> getAll(LeadtimeSearchJSONRequest jsonRequest) {
        LeadtimeSearchRequest request = LeadtimeJsonMapper.getInstance().fromLeadtimeSearchJsonRequest(jsonRequest);
        LeadtimeListResponse response = leadtimeService.getAll(request);
        LeadtimeListJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeListResponse(response);
        APIResponse<LeadtimeListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.get_data");
    }

    @GetMapping(LEADTIMES + OTHER_DATA)
    public ResponseEntity<?> getOtherData(@RequestParam String action) throws OrderDomainException {
        Object jsonResponse;
        if (action.equals("getProvinces")) {
            ProvinceSearchRequest request = new ProvinceSearchRequest();
            ProvinceListResponse response = leadtimeService.getProvinces(request);
            jsonResponse = ProvinceJsonMapper.getInstance().fromProvinceListResponse(response);
        } else if (action.equals("getDistricts")) {
            DistrictSearchRequest request = new DistrictSearchRequest();
            DistrictListResponse response = leadtimeService.getDistricts(request);
            jsonResponse = DistrictJsonMapper.getInstance().fromDistrictListResponse(response);
        } else {
            throw new OrderDomainException("Action không hợp lệ");
        }
        APIResponse<Object> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.get_other_data");
    }

    @GetMapping(LEADTIMES + "/suggestSearch")
    public ResponseEntity<?> getBySuggest(LeadtimeSearchJSONRequest jsonRequest) {
        LeadtimeSearchRequest request = LeadtimeJsonMapper.getInstance().fromLeadtimeSearchJsonRequest(jsonRequest);
        LeadtimeListResponse response = leadtimeService.getBySuggest(request);
        LeadtimeListJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeListResponse(response);
        APIResponse<LeadtimeListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.get_data");
    }

    @GetMapping(LEADTIMES + "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws OrderDomainException {
        LeadtimeSearch leadtimeSearch = new LeadtimeSearch().withLeadtimeId(id, null);
        LeadtimeSearchRequest request = new LeadtimeSearchRequest(leadtimeSearch, null);
        LeadtimeResponse response = leadtimeService.getById(request);
        LeadtimeJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeResponse(response);
        APIResponse<LeadtimeJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.get_data");
    }

    @GetMapping(LEADTIMES + "/{id}" + LEADTIME_ROUTES)
    public ResponseEntity<?> getLeadtimeRoutesByLeadtimeId(@PathVariable("id") Long id) throws OrderDomainException {
        LeadtimeRouteSearch search = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeId(id);
        LeadtimeRouteSearchRequest searchRequest = new LeadtimeRouteSearchRequest(search, null);
        LeadtimeRouteListResponse response = leadtimeRouteService.getAll(searchRequest);
        LeadtimeRouteListJSONResponse jsonResponse = LeadtimeRouteJsonMapper.getInstance().fromLeadtimeRouteListResponse(response);
        APIResponse<LeadtimeRouteListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime_route.success.get_data");
    }

    @PostMapping(LEADTIMES)
    public ResponseEntity<?> create(@RequestBody LeadtimeJSONRequest jsonRequest) throws OrderDomainException {
        LeadtimeRequest request = LeadtimeJsonMapper.getInstance().fromLeadtimeJsonRequest(jsonRequest);
        LeadtimeResponse response = leadtimeService.create(request);
        LeadtimeJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeResponse(response);
        APIResponse<LeadtimeJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.insert");
    }

    @PutMapping(LEADTIMES + "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody LeadtimeJSONRequest jsonRequest) throws EntityUpdateException {
        jsonRequest.setId(id);
        LeadtimeRequest request = LeadtimeJsonMapper.getInstance().fromLeadtimeJsonRequest(jsonRequest);
        LeadtimeResponse response = leadtimeService.update(request);
        LeadtimeJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeResponse(response);
        APIResponse<LeadtimeJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.update");
    }

    @PatchMapping(LEADTIMES + "/{id}" + "/status")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id, @RequestBody LeadtimeJSONRequest jsonRequest) throws EntityUpdateException {
        jsonRequest.setId(id);
        LeadtimeRequest request = LeadtimeJsonMapper.getInstance().fromLeadtimeJsonRequest(jsonRequest);
        LeadtimeResponse response = leadtimeService.changeStatus(request);
        LeadtimeJSONResponse jsonResponse = LeadtimeJsonMapper.getInstance().fromLeadtimeResponse(response);
        APIResponse<LeadtimeJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "leadtime.success.update");
    }
}
