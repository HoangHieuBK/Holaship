package vn.vmg.ptdv.hola.api.rest.va;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.VARegisterRequest;
import vn.vmg.ptdv.hola.account.presentation.VARequest;
import vn.vmg.ptdv.hola.account.presentation.VAResponse;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.api.common.APIResponse;

@RestController
public class VAController {
    private AccountService accountService;

    public VAController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/va/{phoneNumber}")
    @ResponseBody
    public ResponseEntity<?> getVA(@PathVariable("phoneNumber") String phoneNumber
    ) throws AccountDomainException {
        VARequest request = new VARequest(phoneNumber);
        VAInfo info = accountService.getVAByUserId(request);
        VAJSONResponse response = new VAJSONResponse();
        response.setShipCode(info.getShipCode());
        response.setShopCode(info.getShopCode());
        response.setUserName(info.getUserName());
        response.setUserId(info.getUserId());
        APIResponse<VAJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(response, HttpStatus.OK.value(), "Get va-wallet success");
    }

    @PostMapping("/va/{phoneNumber}/registerVA")
    @ResponseBody
    public ResponseEntity<?> registerVA(@PathVariable("phoneNumber") String phoneNumber, @RequestBody VAJSONRequest request) throws
            AccountDomainException {
        VARegisterRequest vaRegisterRequest = new VARegisterRequest(phoneNumber, request.getUserName(),
                request.getOtpCode(), request.getSessionKey(), request.getCustomerName());
        VAResponse response = accountService.registerVA(vaRegisterRequest);
        VAJSONResponse vajsonResponse = new VAJSONResponse(response.getUserName(), response.getShipCode(),
                response.getShopCode());
        APIResponse<VAJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(vajsonResponse, HttpStatus.OK.value(), "register va success ");
    }
}
