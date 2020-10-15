package vn.vmg.ptdv.hola.common.masterData.bank.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;

import java.util.List;

@Data
public class BankListResponse {
    private List<Bank> bankList;
}
