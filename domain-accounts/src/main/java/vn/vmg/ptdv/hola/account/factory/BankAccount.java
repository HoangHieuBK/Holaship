package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.BankInfo;

@Data
public class BankAccount {
    private BankInfo bankInfo;
    private String imageBank;
    public BankAccount() {

    }
    public BankAccount(String imageBank,String bankCode,String bankAccount,int type,String bankAccountName,String bankName,String code){
        this.imageBank = imageBank;
        this.bankInfo = new BankInfo(bankAccount,bankAccountName,type,bankCode,bankName,code);

    }

}
