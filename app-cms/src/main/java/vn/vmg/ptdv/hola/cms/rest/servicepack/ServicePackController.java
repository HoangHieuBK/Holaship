package vn.vmg.ptdv.hola.cms.rest.servicepack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.LeadtimeJsonMapper;
import vn.vmg.ptdv.hola.cms.mapper.ServicePackJSONMapper;
import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.order.ServicePackJSONResponse;
import vn.vmg.ptdv.hola.exception.EntityInsertException;
import vn.vmg.ptdv.hola.exception.EntityUpdateException;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.presentation.*;
import vn.vmg.ptdv.hola.servicepack.service.ServicePackService;

@RestController
public class ServicePackController {

    private final ServicePackService servicePackService;
    private final String ACTIVEORDEACTIVE = "ACTIVEORDEACTIVE";
    private final String CREATE = "CREATE";

    public ServicePackController(ServicePackService servicePackService) {
        this.servicePackService = servicePackService;
    }

    @GetMapping("/service-pack")
    public ResponseEntity<?> getAll(ServicePackSearchJSONRequest jsonRequest) {
        ServicePackSearchRequest request = ServicePackJSONMapper.getInstance().fromServicePackSearchJSONRequest(jsonRequest);
        ServicePackListResponse response = servicePackService.getAll(request);
        ServicePackListJSONResponse jsonResponse = ServicePackJSONMapper.getInstance().fromServicePackListResponse(response);
        APIResponse<ServicePackListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Lấy dữ liệu gói cước giao nhanh thành công");
    }

    @GetMapping("/service-pack/{code}")
    @ResponseBody
    public ResponseEntity<?> getServicePackByCode(@PathVariable("code") String code) throws OrderDomainException {
        ServicePackSearch servicePackSearch = new ServicePackSearch().withServicePackID(null, code);
        ServicePackSearchRequest request = new ServicePackSearchRequest(servicePackSearch, null);
        ServicePackResponse response = servicePackService.getByCode(request);
        ServicePackJSONResponse jsonResponse = ServicePackJSONMapper.getInstance().fromServicePackResponse(response);
        APIResponse<ServicePackJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Lấy dữ liệu gói cước giao nhanh thành công");
    }

    @PostMapping("/service-pack")
    public ResponseEntity<?> create(@RequestBody ServicePackJSONRequest jsonRequest) throws EntityInsertException {
        ServicePackRequest request = ServicePackJSONMapper.getInstance().fromServicePackJSONRequest(jsonRequest);
        ServicePackResponse response = servicePackService.create(request);
        ServicePackJSONResponse jsonResponse = ServicePackJSONMapper.getInstance().fromServicePackResponse(response);
        APIResponse<ServicePackJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Thêm mới gói cước giao nhanh thành công");
    }

    @PutMapping("/service-pack" + "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ServicePackJSONRequest jsonRequest) throws EntityUpdateException {
        jsonRequest.setId(id);
        ServicePackRequest request = ServicePackJSONMapper.getInstance().fromServicePackJSONRequest(jsonRequest);
        ServicePackResponse response = servicePackService.update(request);
        ServicePackJSONResponse jsonResponse = ServicePackJSONMapper.getInstance().fromServicePackResponse(response);
        APIResponse<ServicePackJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Chỉnh sửa gói cước giao nhanh thành công");
    }

    @PatchMapping("/service-pack/{code}" + "/status")
    @ResponseBody
    public ResponseEntity<?> changeStatus(@PathVariable("code") String code, @RequestBody
            ServicePackJSONRequest jsonRequest) throws EntityUpdateException {
        jsonRequest.setCode(code);
        ServicePackRequest request = ServicePackJSONMapper.getInstance().fromServicePackJSONRequest(jsonRequest);
        ServicePackResponse response = servicePackService.changeStatus(request);
        ServicePackJSONResponse jsonResponse = ServicePackJSONMapper.getInstance().fromServicePackResponse(response);
        APIResponse<ServicePackJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Cập nhật gói cước giao nhanh thành công");
    }















//    @PostMapping("/service-pack")
//    @ResponseBody
//    public ResponseEntity<?> create(ServicePackJSONRequest request) throws
//            OrderDomainException {
//        ServicePackRequest servicePackRequest = ServicePackJSONMapper.getInstance()
//                .fromServicePackJSONRequest(request);
//        SPUpdateResponse response = servicePackService.createServicePack(servicePackRequest);
//        APIResponse<SPUpdateResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "servicepack.success.create");
//    }

    @PutMapping("/service-pack")
    @ResponseBody
    public ResponseEntity<?> update(ServicePackJSONRequest request) throws
            OrderDomainException {
        SPUpdateRequest spUpdateRequest = new SPUpdateRequest();
        SPUpdateResponse response = servicePackService.updateServicePack(spUpdateRequest);
        APIResponse<SPUpdateResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "servicepack.success.create");
    }




//    @GetMapping("/servicePack")
//    public ResponseEntity<?> getListServicePack(ServicePackSearchJSONRequest request) throws
//            OrderDomainException {
//        ServicePackRequest servicePackRequest = ServicePackJSONMapper.getInstance()
//                .fromServicePackSearchJSONRequest(request);
//
//        ServicePackResponse response = servicePackService.listServicePack(servicePackRequest);
//        ServicePackJSONResponse servicePackJSONResponse = new ServicePackJSONResponse();
//        if (response != null && !response.getListServicePacks().isEmpty()) {
//            servicePackJSONResponse.setPageIndex(response.getPageIndex());
//            servicePackJSONResponse.setPageSize(response.getPageSize());
//            servicePackJSONResponse.setTotalRecord(response.getTotalRecord());
//            servicePackJSONResponse.setList(response.getListServicePacks());
//        }
//        APIResponse<ServicePackJSONResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(servicePackJSONResponse, HttpStatus.OK.value(),
//                "Lấy danh sách gói cước giao ngay thành công");
//    }

//    @GetMapping("/servicePack/history")
//    public ResponseEntity<?> getListServicePackHistory(HistoryServicePackJSONRequest request) throws
//            OrderDomainException {
//        HistoryServicePackRequest history = new HistoryServicePackRequest(request.getId(),
//                request.getEffectiveAt(), request.getPageIndex(), request.getPageSize(), request.getAsc(),
//                request.getFieldSort());
//
//        ServicePackResponse response = servicePackService.getHistoryServicePack(history);
//
//        HistoryServicePackJSONResponse historyServicePack = new HistoryServicePackJSONResponse();
//        List<HistoryServicePackJSON> data = new ArrayList<>();
//        if (!response.getListServicePacks().isEmpty()) {
//            for (ServicePackInfo servicePackInfo : response.getListServicePacks()) {
//                data.add(HistoryServicePackJSONMapper.getInstance().fromServicePackHistory(servicePackInfo));
//            }
//            historyServicePack.setListHistorys(data);
//        }
//        historyServicePack.setPageIndex(response.getPageIndex());
//        historyServicePack.setPageSize(response.getPageSize());
//        historyServicePack.setTotalRecords(response.getTotalRecord());
//        APIResponse<HistoryServicePackJSONResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(historyServicePack, HttpStatus.OK.value(), "thanh cong");
//    }

    @GetMapping("/servicePack/servicePackSetting")
    public ResponseEntity<?> getServicePackSettingByID(@RequestParam Integer id) throws
            OrderDomainException {
        ServicePackInfo response = servicePackService.getServicePackSettingByID(id);
        APIResponse<ServicePackInfo> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "thanh cong");
    }

//    @GetMapping("/servicePack/{code}")
//    public ResponseEntity<?> getServicePackByCode(@PathVariable String code) throws OrderDomainException {
//        ServicePackInfo response = servicePackService.getServicePackByCode(code);
//        ServicePackDetailJSONResponse servicePackDetailJSONResponse = new ServicePackDetailJSONResponse();
//        if (response != null) {
//            servicePackDetailJSONResponse = ServicePackJSONMapper.getInstance().fromServicePackDetail(response);
//        }
//        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(servicePackDetailJSONResponse, HttpStatus.OK.value(),
//                "Lấy gói cước giao ngay thành công");
//    }

//    @PostMapping("/servicePack/autoSuggest")
//    public ResponseEntity<?> getListServicePackAutoSuggest(ServicePackJSONRequest request) throws
//            OrderDomainException {
//        ServicePackRequest servicePackRequest = new ServicePackRequest(request.getName(), request.getCode(),
//                request.getStatus());
//
//        ServicePackResponse response = servicePackService.getListServicePack(servicePackRequest);
//
//        ServicePackAutoSuggestJSONResponse orderJSONResponse = new ServicePackAutoSuggestJSONResponse();
//        List<ServicePackAutoSuggestJSON> data = new ArrayList<>();
//        if (!response.getListServicePacks().isEmpty()) {
//            for (ServicePackInfo servicePackInfo : response.getListServicePacks()) {
//                data.add(ServicePackJSONMapper.getInstance().fromServicePack(servicePackInfo));
//            }
//            orderJSONResponse.setListNameCode(data);
//        }
//
//        APIResponse<ServicePackAutoSuggestJSONResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(orderJSONResponse, HttpStatus.OK.value(), "Thành công");
//    }

//    @PostMapping("/servicePack")
//    @ResponseBody
//    public ResponseEntity<?> servicePackHandle(@RequestParam String action,
//            @RequestBody ServicePackJSONRequest servicePackJSONRequest) throws OrderDomainException {
////        if (ACTIVEORDEACTIVE.equalsIgnoreCase(action)) {
////            return activeOrDeactive(servicePackJSONRequest);
////        }else
//        if(CREATE.equalsIgnoreCase(action)) {
//            return createServicePack(servicePackJSONRequest);
//        }
//        throw new OrderDomainException("Không hỗ trợ hành động này!");
//    }

//    public ResponseEntity<?> activeOrDeactive(ServicePackJSONRequest request) throws OrderDomainException {
//        ServicePackRequest servicePackRequest = ServicePackJSONMapper.getInstance().fromServicePackJSONRequest(request);
//        SPUpdateResponse response = servicePackService.activeOrDeactive(servicePackRequest);
//        APIResponse<SPUpdateResponse> apiResponse = new APIResponse<>();
//        return apiResponse
//                .sendResponse(response, HttpStatus.OK.value(), "Đổi trạng thái gói cước giao ngay thành công");
//    }
//
//    public ResponseEntity<?> createServicePack(ServicePackJSONRequest request) throws
//            OrderDomainException {
//        ServicePackRequest servicePackRequest = ServicePackJSONMapper.getInstance()
//                .fromServicePackJSONRequest(request);
//        SPUpdateResponse response = servicePackService.createServicePack(servicePackRequest);
//        APIResponse<SPUpdateResponse> apiResponse = new APIResponse<>();
//        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "Tạo gói cước giao ngay thành công");
//    }
}
