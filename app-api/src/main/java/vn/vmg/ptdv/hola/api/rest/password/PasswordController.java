package vn.vmg.ptdv.hola.api.rest.password;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.Password;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.presentation.PasswordRequest;
import vn.vmg.ptdv.hola.account.presentation.PasswordResponse;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.api.common.APIResponse;
import vn.vmg.ptdv.hola.common.exception.OTPVerificationException;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;

@RestController
public class PasswordController {

    private final AccountService accountService;

    public PasswordController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/password")
    public ResponseEntity<?> requestForgotPassword(PasswordJSONRequest jsonRequest) throws
            AccountDomainException, OTPVerificationException, SecurityDESException {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setAccountId(new AccountId(jsonRequest.getPhoneNumber()));
        passwordRequest.setStep(jsonRequest.getStep());
        PasswordResponse passwordResponse = accountService.forgotPassword(passwordRequest);

        PasswordJSONResponse jsonResponse = new PasswordJSONResponse();
        jsonResponse.setPhoneNumber(jsonRequest.getPhoneNumber());
        jsonResponse.setCode(1);
        APIResponse<PasswordJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Request Forgot password success");
    }

    @PostMapping("/password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordJSONRequest jsonRequest) throws
            AccountDomainException, OTPVerificationException, SecurityDESException {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setAccountId(new AccountId(jsonRequest.getPhoneNumber()));
        passwordRequest.setStep(jsonRequest.getStep());
        passwordRequest.setPassword(
                new Password().withEncrypted(jsonRequest.getPassword()).withConfirm(jsonRequest.getConfirmPassword()));
        passwordRequest.setOtp(new OTPVerification(jsonRequest.getOtp()));

        PasswordResponse passwordResponse = accountService.forgotPassword(passwordRequest);

        PasswordJSONResponse jsonResponse = new PasswordJSONResponse();
        jsonResponse.setPhoneNumber(passwordResponse.getPhoneNumber());
        jsonResponse.setCode(1);
        APIResponse<PasswordJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Post Forgot password success");
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordJSONRequest jsonRequest) {

        PasswordJSONResponse jsonResponse = new PasswordJSONResponse();
        APIResponse<PasswordJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "Change password success");
    }
}
