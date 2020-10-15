package vn.vmg.ptdv.hola.api.rest.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.CardID;
import vn.vmg.ptdv.hola.account.core.DriverLicense;
import vn.vmg.ptdv.hola.account.core.Vehicle;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.presentation.*;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.api.common.APIResponse;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProfileController {

    private final AccountService accountService;

    public ProfileController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/{phoneNumber}")
    public ResponseEntity<?> getProfile(ProfileJSONRequest jsonRequest) throws AccountNotFoundException {
        ProfileRequest profileRequest = new ProfileRequest(jsonRequest.getPhoneNumber(), jsonRequest.getDeviceToken());
        ProfileResponse response = accountService.getProfileById(profileRequest);

        ProfileJSONResponse profileJSONResponse = new ProfileJSONResponse(response.getProfile());
        APIResponse<ProfileJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(profileJSONResponse, HttpStatus.OK.value(), "Get Profile success");
    }

    @PutMapping("/user/{phoneNumber}")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileJSONRequest jsonRequest) throws AccountDomainException {
        ProfileRequest profileRequest = new ProfileRequest()
                .withIdentity(jsonRequest.getPhoneNumber(), jsonRequest.getUtimestamp())
                .withBasicInfo(jsonRequest.getAvatar(), jsonRequest.getBirthday(), jsonRequest.getDisplayName(),
                        jsonRequest.getGender())
                .build();
        ProfileResponse response = accountService.updateProfile(profileRequest);
        ProfileJSONResponse profileJSONResponse = new ProfileJSONResponse(response.getProfile(), response.getStatus());
        APIResponse<ProfileJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(profileJSONResponse, HttpStatus.OK.value(), "Update Profile success");
    }

    @GetMapping("/user/{phoneNumber}/shipper")
    public ResponseEntity<?> getShipperProfile(ShipperJSONRequest jsonRequest) throws AccountDomainException {
        ShipperRequest shipperRequest = new ShipperRequest(jsonRequest.getPhoneNumber());
        ShipperResponse response = accountService.getShipperProfile(shipperRequest);
        ShipperJSONResponse shipperJSONResponse = new ShipperJSONResponse(jsonRequest.getPhoneNumber(), response);
        APIResponse<ShipperJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(shipperJSONResponse, HttpStatus.OK.value(), "Get Shipper Profile success");
    }

    @PutMapping("/user/{phoneNumber}/shipper")
    public ResponseEntity<?> updateShipperProfile(@PathVariable String phoneNumber,
            @RequestBody ShipperJSONRequest json, @RequestParam("part") String part) throws AccountDomainException {
        ShipperRequest shipperRequest = new ShipperRequest(phoneNumber)
                .withCardID(new CardID()
                        .withIdentity(json.getNumberCardID(), json.getDateCardID(), json.getPlaceCardID())
                        .withRegistration(json.getImgBeforeCardID(), json.getImgAfterCardID())
                        .build()
                ).withDriverLicense(new DriverLicense()
                        .withIdentity(json.getDriverLicense(), json.getSpeciesLicenseType())
                        .withRegistration(json.getImgAfterDriverLicense(), json.getImgBeforeDriverLicense())
                        .build()
                ).withVehicle(new Vehicle()
                        .withIdentity(json.getVehicleType(), json.getLicensePlace())
                        .withInsurance(json.getImgBeforeInsurance(), json.getImgAfterInsurance())
                        .withRegistration(json.getImgBeforeDriverRegister(), json.getImgAfterDriverRegister())
                        .build()
                ).withAuditLog(new AuditLog(Timestamp.from(json.getUtimestamp())))
                .withPartInfo(part)
                .build();
        ShipperResponse response = accountService.updateShipperProfile(shipperRequest);
        ShipperJSONResponse shipperJSONResponse = new ShipperJSONResponse(json.getPhoneNumber(), response);
        APIResponse<ShipperJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(shipperJSONResponse, HttpStatus.OK.value(), "Get Shipper Profile success");
    }


    @PutMapping("/user/{phoneNumber}/shipper/verify")
    public ResponseEntity<?> verification(@PathVariable String phoneNumber,
            @RequestBody VerificationJSONRequest request)
            throws AccountDomainException {

        OTPVerifyRequest otpRequest = new OTPVerifyRequest(phoneNumber, request.getOtp(), request.getUTimestamp(),
                request.getDeviceToken(), request.getServiceName());

        VerifyResponse response = accountService.identity(otpRequest);
        VerificationJSONResponse otpjsonResponse = new VerificationJSONResponse();
        otpjsonResponse.setStatus(response.getStatus());
        APIResponse<VerificationJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(otpjsonResponse, HttpStatus.OK.value(), "");
    }


    @PostMapping("user/{phoneNumber}/shipper/confirmInfoBank")
    @ResponseBody
    public ResponseEntity<?> confirmInfoCash(@PathVariable("phoneNumber") String phoneNumber,
            @RequestBody BankAccountJSONRequest request) throws AccountDomainException {
        BankAccountRequest accountRequest = new BankAccountRequest(phoneNumber, request.getPartnerCode(),
                request.getOperation(), request.getBankAccount(), request.getBankCode(),
                request.getType(), request.getOtp());
        BankAccountResponse response = accountService.confirmBankInfo(accountRequest);
        BankAccountJSONResponse bankAccountJSONResponse = new BankAccountJSONResponse(
                response.getBankCode(),
                response.getBankAccount(),
                response.getType(),
                response.getBankAccountName(),
                response.getBankName(),
                response.getBankShortName(),
                response.getPartnerCode(),
                response.getRequestAmount()
        );
        APIResponse<BankAccountJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(bankAccountJSONResponse, HttpStatus.OK.value(), "confirm cash info success ");
    }

    @GetMapping("user/{phoneNumber}/bank")
    public ResponseEntity<?> getBankAccount(@PathVariable("phoneNumber") String phoneNumber,
            @RequestParam("serviceName") String serviceName) throws AccountDomainException {
        BankAccountRequest accountRequest = new BankAccountRequest(phoneNumber, serviceName);

        BankAccountListResponse listResponse = accountService
                .getBankAccount(accountRequest);
        List<BankAccountJSONResponse> listOfJson = listResponse.getBankAccountList().stream()
                .map(bankAccount -> new BankAccountJSONResponse(bankAccount)).collect(
                        Collectors.toList());
        APIResponse<List<BankAccountJSONResponse>> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(listOfJson, HttpStatus.OK.value(), "get bankAccount success ");
    }
}
