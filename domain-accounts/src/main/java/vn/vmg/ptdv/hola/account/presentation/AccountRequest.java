package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;

@Data
public class AccountRequest {
    private AccountId accountId;
}
