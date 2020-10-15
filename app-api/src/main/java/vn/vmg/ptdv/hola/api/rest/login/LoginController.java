package vn.vmg.ptdv.hola.api.rest.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.presentation.LoginRequest;
import vn.vmg.ptdv.hola.account.presentation.LoginResponse;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.api.common.APIResponse;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

@RestController
public class LoginController {
    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginJSONRequest loginJSONRequest) throws AccountDomainException,
            SecurityDESException {
        LoginRequest loginRequest = new LoginRequest(loginJSONRequest.getPhoneNumber(), loginJSONRequest.getPassword(),
                loginJSONRequest.getDeviceToken(), loginJSONRequest.getServiceName());
        LoginResponse loginResponse = accountService.login(loginRequest);
        LoginJSONResponse loginJSONResponse = new LoginJSONResponse();
        loginJSONResponse.setSessionKey(loginResponse.getSessionKey().getValue());
        loginJSONResponse.setUserName(loginResponse.getUsername().getPhoneNumber());
        loginJSONResponse.setCode(1);
        loginJSONResponse.setIsFirstLogin(loginResponse.getIsFirstLogin());
        loginJSONResponse.setUTimestamp(loginResponse.getAuditLog().getUTimestamp().toInstant());
        APIResponse<LoginJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(loginJSONResponse, HttpStatus.OK.value(), "Login success");
    }
}
