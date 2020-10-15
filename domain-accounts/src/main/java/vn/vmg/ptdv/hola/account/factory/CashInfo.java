package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.BankIMediaProvider;
import vn.vmg.ptdv.hola.account.core.BankInfo;

@Data
public class CashInfo {
    private AccountId accountId;
    private BankInfo bankInfo;
    private BankIMediaProvider bankIMediaProvider;
}
