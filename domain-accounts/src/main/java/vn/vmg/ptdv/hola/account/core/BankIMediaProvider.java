package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class BankIMediaProvider {
    private String partnerCode;
    private Integer operation;
    private String requestAmount;

    public BankIMediaProvider() {
    }

    public BankIMediaProvider(String partnerCode, Integer operation) {
        this.partnerCode = partnerCode;
        this.operation = operation;
    }

    public BankIMediaProvider(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public BankIMediaProvider(String partnerCode, Integer operation, String requestAmount) {
        this.partnerCode = partnerCode;
        this.operation = operation;
        this.requestAmount = requestAmount;
    }
}
