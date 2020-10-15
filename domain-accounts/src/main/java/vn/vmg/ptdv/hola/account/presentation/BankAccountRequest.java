package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.service.impl.PartInfo;

@Data
public class BankAccountRequest {
    private PartInfo pathInfo;
    private AccountId accountId;
    private BankInfo bankInfo;
    private BankIMediaProvider bankIMediaProvider;
    private AuditLog auditLog;
    private String otp;
    private HolaProvider serviceName;

    public BankAccountRequest() {

    }

    public BankAccountRequest(String phoneNumber, String partnerCode, Integer operation, String bankAccount,
            String bankCode, int type, String otp) {
        this.accountId = new AccountId(phoneNumber);
        this.bankIMediaProvider = new BankIMediaProvider(partnerCode, operation);
        this.bankInfo = new BankInfo(bankCode, bankAccount, type);
        this.otp = otp;
    }

    public BankAccountRequest(String phoneNumber, String serviceName) {
        this.accountId = new AccountId(phoneNumber);
        this.serviceName = HolaProvider.valueOf(serviceName);
    }

    public BankAccountRequest(String phoneNumber) {
        this.accountId = new AccountId(accountId.getPhoneNumber());
    }

    public BankAccountRequest withBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
        return this;
    }

    public BankAccountRequest withBankIMediaProvider(BankIMediaProvider bankIMediaProvider) {
        this.bankIMediaProvider = bankIMediaProvider;
        return this;
    }

    public BankAccountRequest withBankAuditLog(AuditLog bankAuditLog) {
        this.auditLog = bankAuditLog;
        return this;
    }

    public BankAccountRequest build() {
        return this;
    }
}
