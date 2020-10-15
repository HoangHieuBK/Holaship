package vn.vmg.ptdv.hola.common.masterData.bank.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.common.masterData.bank.core.BankId;

@Data
public class Bank {
    private BankId bankId;
    private String imageBank;
    private String name;
    public Bank(){

    }
    public Bank(BankId bankId,String imageBank,String name){
        this.bankId = bankId;
        this.imageBank = imageBank;
        this.name = name;
    }
}
