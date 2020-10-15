package vn.vmg.ptdv.hola.api.rest.masterData.Bank;

import lombok.Data;
import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;

import java.util.List;

@Data
public class BankListJSONResponse {
    private List<Bank> list;
    public BankListJSONResponse(List<Bank> list){
        this.list = list;
    }
    public BankListJSONResponse(){

    }
}
