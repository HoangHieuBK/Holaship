package vn.vmg.ptdv.hola.api.rest.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.presentation.RegisterRequest;
import vn.vmg.ptdv.hola.account.presentation.RegisterResponse;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.api.common.APIResponse;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

@RestController
public class RegisterController {
    private final AccountService accountService;

    public RegisterController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterJSONRequest jsonReq) throws AccountDomainException,
            SecurityDESException {
        RegisterRequest registerRequest = new RegisterRequest(jsonReq.getPhoneNumber(), jsonReq.getEmail(),
                jsonReq.getPassword(), jsonReq.getConfirmPassword(), jsonReq.getServiceName(),
                jsonReq.getDeviceToken());

        RegisterResponse registerResponse = accountService.register(registerRequest);

        RegisterJSONResponse registerJSONResponse = new RegisterJSONResponse();
        registerJSONResponse.setEmail(registerResponse.getEmail());
        registerJSONResponse.setPhoneNumber(registerResponse.getPhoneNumber());
        registerJSONResponse.setServiceName(jsonReq.getServiceName());
        registerJSONResponse.setCode(1);
        APIResponse<RegisterJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(registerJSONResponse, HttpStatus.OK.value(), "Register success");
    }
}
