package vn.vmg.ptdv.hola.api.rest.masterData.Bank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vmg.ptdv.hola.api.common.APIResponse;
import vn.vmg.ptdv.hola.common.masterData.bank.presentation.BankListResponse;
import vn.vmg.ptdv.hola.common.masterData.bank.service.BankService;
import vn.vmg.ptdv.hola.common.masterData.exception.MasterDataException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BankController {
    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/bank")
    public ResponseEntity<?> getBank() throws MasterDataException {
        BankListResponse response = bankService.getBank();
        List<BankJSONResponse> listOfJson= response.getBankList().stream().map(bank -> new BankJSONResponse(bank)).collect(Collectors.toList());
        APIResponse<List<BankJSONResponse> > apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(listOfJson, HttpStatus.OK.value(), "get bank success ");
    }
}
