package vn.vmg.ptdv.hola.api.rest.profile;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vmg.ptdv.hola.account.core.AccountId;

import java.time.Instant;

@Data
public class BankAccountJSONRequest {
    private AccountId accountId;
    private Instant utimestamp;
    private String partnerCode;
    private Integer operation;
    private String bankCode;
    private String bankAccount;
    private int type;
    private String otp;
    private String serviceName;
    public BankAccountJSONRequest() {

    }

    public BankAccountJSONRequest(String phoneNumber, String partnerCode, Integer operation, String bankNo,
            String accNo, int accType) {
        this.accountId = new AccountId(phoneNumber);
        this.partnerCode = partnerCode;
        this.operation = operation;
        this.bankCode = bankNo;
        this.bankAccount = accNo;
        this.type = accType;
    }

    public BankAccountJSONRequest(
            @PathVariable String phoneNumber,
            @RequestParam String serviceName
    ) {
        this.accountId = new AccountId(phoneNumber);
        this.serviceName = serviceName;
    }
}
