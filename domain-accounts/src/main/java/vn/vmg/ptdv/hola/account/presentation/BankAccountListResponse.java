package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.factory.BankAccount;

import java.util.List;

@Data
public class BankAccountListResponse {
    private List<BankAccount> bankAccountList;
}
