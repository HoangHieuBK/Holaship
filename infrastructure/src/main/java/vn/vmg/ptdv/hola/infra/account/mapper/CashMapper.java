package vn.vmg.ptdv.hola.infra.account.mapper;

import org.springframework.beans.BeanUtils;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.factory.CashInfo;
import vn.vmg.ptdv.hola.infra.account.factory.BankAccountDB;

public class CashMapper {
    private static CashMapper INSTANCE;

    private CashMapper() {

    }

    public static CashMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CashMapper();
        }
        return INSTANCE;
    }

    public static CashInfo fromDB(BankAccountDB bankAccountDB) {
        CashInfo cashInfo = new CashInfo();
        BeanUtils.copyProperties(bankAccountDB, cashInfo);
        return cashInfo;
    }

    public static BankAccountDB fromDomainInsertCash(CashInfo info) {
        BankAccountDB bankAccountDB = new BankAccountDB();
        if (info != null) {
            bankAccountDB.setBankAccount(info.getBankInfo().getBankAccount());
            bankAccountDB.setBankAccountName(info.getBankInfo().getBankAccountName());
            bankAccountDB.setBankCode(info.getBankInfo().getBankCode());
            bankAccountDB.setType(info.getBankInfo().getType());
        }
        return bankAccountDB;
    }

    public static BankAccountDB fromDomainUpdateCash(CashInfo info, AuditLog auditLog) {
        BankAccountDB bankAccountDB = new BankAccountDB();
        if (info != null) {
            bankAccountDB.setBankAccount(info.getBankInfo().getBankAccount());
            bankAccountDB.setBankAccountName(info.getBankInfo().getBankAccountName());
            bankAccountDB.setBankCode(info.getBankInfo().getBankCode());
            bankAccountDB.setType(info.getBankInfo().getType());
//            bankAccountDB.setUTimestamp(auditLog.getUTimestamp());
        }
        return bankAccountDB;
    }

}
