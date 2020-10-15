package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class BankInfo {
    private Long bankId;
    private String bankAccount;
    private String bankAccountName;
    private int type;
    private String bankCode;
    private String bankName;
    private String code;

    public BankInfo() {

    }
    public BankInfo(String bankAccount, String bankAccountName,int type,String bankCode,String bankName,String code){
        this.bankAccount = bankAccount;
        this.bankAccountName = bankAccountName;
        this.type = type;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.code = code;
    }
    public BankInfo(Long bankId, String bankAccount, String bankAccountName, int type, String bankCode,
            String bankName) {
        this.bankId = bankId;
        this.bankAccount = bankAccount;
        this.bankAccountName = bankAccountName;
        this.type = type;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public BankInfo(String bankCode, String bankAccount, int type, String bankAccountName) {
        this.bankCode = bankCode;
        this.bankAccount = bankAccount;
        this.type = type;
        this.bankAccountName = bankAccountName;
    }

    public BankInfo(String bankAccount, String bankAccountName, int type, String bankCode, String bankName) {
        this.bankAccount = bankAccount;
        this.bankAccountName = bankAccountName;
        this.type = type;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public BankInfo(String bankCode, String bankAccount, int type) {
        this.bankCode = bankCode;
        this.bankAccount = bankAccount;
        this.type = type;
    }

    public BankInfo(String bankName, String code) {
        this.bankName = bankName;
        this.code = code;
    }
}
