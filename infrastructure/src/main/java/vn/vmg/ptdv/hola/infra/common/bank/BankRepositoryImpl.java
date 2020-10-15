package vn.vmg.ptdv.hola.infra.common.bank;

import vn.vmg.ptdv.hola.common.masterData.bank.core.BankId;
import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;
import vn.vmg.ptdv.hola.common.masterData.bank.repository.BankRepository;
import vn.vmg.ptdv.hola.infra.account.factory.BankDB;

import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {
    private final BankJDBC bankJDBC;

    public BankRepositoryImpl(BankJDBC bankJDBC) {
        this.bankJDBC = bankJDBC;
    }

    @Override
    public List<Bank> getBank() {
        List<Bank> banks = new ArrayList<>();
        List<BankDB> response = bankJDBC.findBank();
        if (response != null) {
            for (BankDB bankDB : response) {
                BankId bankId = new BankId();
                bankId.setBankCode(bankDB.getBankCode());
                bankId.setCode(bankDB.getCode());
                Bank bank = new Bank(bankId, bankDB.getImageBank(),bankDB.getName());
                banks.add(bank);
            }
        }
        return banks;
    }

}
