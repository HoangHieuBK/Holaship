package vn.vmg.ptdv.hola.api.rest.masterData.Bank;

import lombok.Data;
import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;

@Data
public class BankJSONResponse {
    private String imageBank;
    private String name;
    private String code;
    private String shortName;

    public BankJSONResponse(Bank bank) {
        this.shortName = bank.getBankId().getCode();
        this.code = bank.getBankId().getBankCode();
        this.imageBank = bank.getImageBank();
        this.name = bank.getName();
    }
}
