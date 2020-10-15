package vn.vmg.ptdv.hola.cms.rest.wallet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.cms.common.APIResponse;

@RestController
public class WalletController {
    private static final String WALLET = "/wallet";

    @GetMapping(WALLET)
    public ResponseEntity<?> getAll(WalletJSONRequest jsonRequest) {
//        WalletListRequest walletListRequest = WalletJSONMapper.getInstance().mapAccountSearchRequest(request);
        WalletListJSONResponse walletListJSONResponse = new WalletListJSONResponse();
        walletListJSONResponse.setList(WalletJSONData.getInstance().listWalletResponse());
        walletListJSONResponse.setPageIndex(jsonRequest.getPageIndex());
        walletListJSONResponse.setPageSize(jsonRequest.getPageSize());
        walletListJSONResponse.setTotalRecord(15);
        APIResponse<WalletListJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(walletListJSONResponse, HttpStatus.OK.value(), "leadtime.success.get_data");
    }
}
