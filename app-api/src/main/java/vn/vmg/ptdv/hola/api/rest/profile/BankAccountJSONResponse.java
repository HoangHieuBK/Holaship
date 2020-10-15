package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vn.vmg.ptdv.hola.account.factory.BankAccount;

@Data
public class BankAccountJSONResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String partnerCode;
    private String bankCode;
    private String bankAccount;
    private int type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestAmount;
    private String bankAccountName;
    private String bankName;
    private String bankShortName;
    private String imageBank;

    public BankAccountJSONResponse() {

    }

    public BankAccountJSONResponse(String bankCode, String bankAccount, int type, String bankAccountName,
            String bankName, String code, String partnerCode, String requestAmount) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.bankAccountName = bankAccountName;
        this.type = type;
        this.bankShortName = code;
        this.partnerCode = partnerCode;
        this.requestAmount = requestAmount;
    }

    public BankAccountJSONResponse(String imageBank, String bankCode, String bankAccount, int type,
            String bankAccountName, String bankName, String code) {
        this.imageBank = imageBank;
        this.bankCode = bankCode;
        this.bankAccount = bankAccount;
        this.type = type;
        this.bankAccountName = bankAccountName;
        this.bankName = bankName;
        this.bankShortName = code;
    }

    public BankAccountJSONResponse(BankAccount bankAccount) {
        this.imageBank = bankAccount.getImageBank();
        this.bankCode = bankAccount.getBankInfo().getBankCode();
        this.bankAccount = bankAccount.getBankInfo().getBankAccount();
        this.type = bankAccount.getBankInfo().getType();
        this.bankAccountName = bankAccount.getBankInfo().getBankAccountName();
        this.bankName = bankAccount.getBankInfo().getBankName();
        this.bankShortName = bankAccount.getBankInfo().getCode();
    }
}

