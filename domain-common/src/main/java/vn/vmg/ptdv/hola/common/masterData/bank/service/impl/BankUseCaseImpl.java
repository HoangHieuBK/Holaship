package vn.vmg.ptdv.hola.common.masterData.bank.service.impl;

import vn.vmg.ptdv.hola.common.masterData.bank.factory.Bank;
import vn.vmg.ptdv.hola.common.masterData.bank.presentation.BankListResponse;
import vn.vmg.ptdv.hola.common.masterData.bank.repository.BankRepository;
import vn.vmg.ptdv.hola.common.masterData.bank.service.usecase.BankUseCase;

import java.util.List;

public class BankUseCaseImpl implements BankUseCase {
    private BankRepository bankRepository;
    private List<Bank> banks;
    public BankUseCaseImpl(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    @Override
    public BankUseCase getBank() {
        banks = bankRepository.getBank();
        return this;
    }

    @Override
    public BankListResponse endGetBank() {
        BankListResponse bankListResponse = new BankListResponse();
        bankListResponse.setBankList(banks);
        return bankListResponse;
    }
}
