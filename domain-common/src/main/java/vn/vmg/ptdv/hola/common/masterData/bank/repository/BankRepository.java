package vn.vmg.ptdv.hola.common.masterData.bank.repository;

import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;

import java.util.List;

public interface BankRepository {
    List<Bank> getBank();
}
