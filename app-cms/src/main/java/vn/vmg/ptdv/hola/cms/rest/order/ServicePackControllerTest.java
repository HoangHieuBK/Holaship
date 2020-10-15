package vn.vmg.ptdv.hola.cms.rest.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.rest.servicepack.HistoryServicePackJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.servicepack.ServicePackJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.servicepack.ServicePackSearchJSONRequest;
import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.servicepack.presentation.HistoryServicePackRequest;

import java.util.List;

@RestController
@RequestMapping("/servicePackTest")
public class ServicePackControllerTest {

    private final String ACTIVEORDEACTIVE = "ACTIVEORDEACTIVE";
    private final String CREATE = "CREATE";
    private final String UPDATE = "UPDATE";

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getLitServicePack(ServicePackSearchJSONRequest request) throws
            OrderDomainException {
        List<ServicePackDetailJSONResponse> list = ServicePackData.getInstance().listHistorysServicePackTest();
        ServicePackJSONResponseTest servicePackJSONResponse = new ServicePackJSONResponseTest();
        servicePackJSONResponse.setPageIndex(request.getPageIndex());
        servicePackJSONResponse.setPageSize(request.getPageSize());
        servicePackJSONResponse.setTotalRecord(2);
        servicePackJSONResponse.setList(list);
        APIResponse<ServicePackJSONResponseTest> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(servicePackJSONResponse, HttpStatus.OK.value(),
                "Lấy danh sách gói cước giao ngay thành công");
    }

    @GetMapping("/history")
    public ResponseEntity<?> getLitServicePackHistory(HistoryServicePackJSONRequest request) throws
            OrderDomainException {
        HistoryServicePackRequest history = new HistoryServicePackRequest(request.getId(),
                request.getEffectiveAt(), request.getPageIndex(), request.getPageSize(), request.getAsc(),
                request.getFieldSort());
        HistoryServicePackJSONResponse historyServicePack = new HistoryServicePackJSONResponse();
        List<HistoryServicePackJSON> data = ServicePackData.getInstance().listHistorysTest();
        historyServicePack.setPageIndex(request.getPageIndex());
        historyServicePack.setPageSize(request.getPageSize());
        historyServicePack.setTotalRecords(15);
        historyServicePack.setListHistorys(data);
        APIResponse<HistoryServicePackJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(historyServicePack, HttpStatus.OK.value(), "thanh cong");
    }

    @GetMapping("/servicePackSetting")
    public ResponseEntity<?> getServicePackSettingByID(@RequestParam Integer id) throws
            OrderDomainException {
        ServicePackDetailJSONResponse response = ServicePackData.getInstance().listHistorysServicePackTest().get(0);
        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "thanh cong");
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getServicePackByCode(@PathVariable String code) throws OrderDomainException {
        ServicePackDetailJSONResponse response = new ServicePackDetailJSONResponse();
        response = ServicePackData.getInstance().listHistorysServicePackTest().get(0);
        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(),
                "Lấy gói cước giao ngay thành công");
    }

    @GetMapping("/autoSuggest")
    public ResponseEntity<?> getLitServicePackAutoSuggest(ServicePackJSONRequest request) throws
            OrderDomainException {
        ServicePackAutoSuggestJSONResponse orderJSONResponse = new ServicePackAutoSuggestJSONResponse();
        List<ServicePackAutoSuggestJSON> data = ServicePackData.getInstance().listNameCode();
        orderJSONResponse.setListNameCode(data);
        APIResponse<ServicePackAutoSuggestJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(orderJSONResponse, HttpStatus.OK.value(), "Thành công");
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> ServicePackHandle(@RequestParam String action,
            @RequestBody ServicePackJSONRequest servicePackJSONRequest) throws OrderDomainException {
        if (ACTIVEORDEACTIVE.equalsIgnoreCase(action)) {
            return activeOrDeactive(servicePackJSONRequest);
        } else if (CREATE.equalsIgnoreCase(action)) {
            return createServicePack(servicePackJSONRequest);
        } else if (UPDATE.equalsIgnoreCase(action)) {
            return updateServicePack(servicePackJSONRequest);
        }
        throw new OrderDomainException("Không hỗ trợ hành động này!");
    }

    public ResponseEntity<?> activeOrDeactive(ServicePackJSONRequest request) throws OrderDomainException {
        ServicePackDetailJSONResponse response = ServicePackData.getInstance().listHistorysServicePackTest().get(0);
        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse
                .sendResponse(response, HttpStatus.OK.value(), "Đổi trạng thái gói cước giao ngay thành công");
    }

    public ResponseEntity<?> createServicePack(@RequestBody ServicePackJSONRequest request) throws
            OrderDomainException {
        ServicePackDetailJSONResponse response = ServicePackData.getInstance().listHistorysServicePackTest().get(0);
        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse
                .sendResponse(response, HttpStatus.OK.value(), " tao gói cước giao ngay thành công");
    }

    public ResponseEntity<?> updateServicePack(@RequestBody ServicePackJSONRequest request) throws
            OrderDomainException {
        ServicePackDetailJSONResponse response = ServicePackData.getInstance().listHistorysServicePackTest().get(0);
        APIResponse<ServicePackDetailJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse
                .sendResponse(response, HttpStatus.OK.value(), " update gói cước giao ngay thành công");
    }
}
