package vn.vmg.ptdv.hola.account.repository;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.BankIMediaProvider;
import vn.vmg.ptdv.hola.account.core.BankInfo;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.CashInfo;

public interface CashRepository {
    CashInfo confirmInfoCash(AccountId accountId, BankIMediaProvider bankIMediaProvider, BankInfo bankInfo) throws
            AccountDomainException;

    boolean insertCash(CashInfo info, AccountId accountId) throws AccountDomainException;

    CashInfo getBankAccount(AccountId accountId, BankInfo bankInfo);
    
    BankInfo getBankNameByBankCode(String bankCode);
}
