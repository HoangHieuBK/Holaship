package vn.vmg.ptdv.hola.cms.rest.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.presentation.AccountSearchRequest;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.cms.common.APIResponse;
import vn.vmg.ptdv.hola.cms.mapper.AccountUserJSONMapper;

@RestController
public class AccountUserController {
    private final AccountService accountService;

    public AccountUserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/accounts")
    public ResponseEntity<?> getAllAccount(AccountUserJSONRequest request) {
        AccountSearchRequest accountSearch = AccountUserJSONMapper.getInstance().mapAccountSearchRequest(request);

        //TODO call Service
        AccountUserJSONResponse accountResponse = new AccountUserJSONResponse();
        accountResponse.setList(AccountUserData.getInstance().listAccountSearch());
        accountResponse.setPageIndex(request.getPageIndex());
        accountResponse.setPageSize(request.getPageSize());
        accountResponse.setTotalRecord(15);
        APIResponse<AccountUserJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(accountResponse, HttpStatus.OK.value(), "account.success.get_datas");
    }

    @GetMapping("/user/account")
    public ResponseEntity<?> findAccoutbyIdAndService(AccountUserJSONRequest request) throws AccountNotFoundException {
        AccountSearchRequest accountSearch = AccountUserJSONMapper.getInstance().mapAccountSearchRequest(request);
        //TODO call Service
        ProfileUserJSONResponse accountResponse = AccountUserData.getInstance()
                .mapDataUserProfile(request.getServiceName());
        APIResponse<ProfileUserJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(accountResponse, HttpStatus.OK.value(), "account.success.get_data");
    }

    @GetMapping("/user/account/autoSuggest")
    public ResponseEntity<?> getAutoSuggest(AccountUserAutoSuggestRequest request) throws AccountNotFoundException {

        AccountUserAutoSuggestResponse accountResponse = AccountUserData.getInstance()
                .mapDataAccountUserAutoSuggestResponse();
        //TODO call Service
        APIResponse<AccountUserAutoSuggestResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(accountResponse, HttpStatus.OK.value(), "account.success.autoSuggest");
    }

    @PostMapping("/user/account/active")
    public ResponseEntity<?> activeAccount(@RequestBody AccountUserActiveRequest request) throws
            AccountNotFoundException {

        //TODO call Service
        AccountUserActiveResponse accountResponse = new AccountUserActiveResponse();
        accountResponse.setId(request.getId());
        accountResponse.setStatus(request.getStatus());
        accountResponse.setUTimestamp(request.getUTimestamp());
        APIResponse<AccountUserActiveResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(accountResponse, HttpStatus.OK.value(), "account.success.active");
    }
}
