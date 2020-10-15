package vn.vmg.ptdv.hola.common.masterData.bank.service;

import vn.vmg.ptdv.hola.common.masterData.bank.presentation.BankListResponse;
import vn.vmg.ptdv.hola.common.masterData.bank.repository.BankRepository;
import vn.vmg.ptdv.hola.common.masterData.bank.service.impl.BankUseCaseImpl;
import vn.vmg.ptdv.hola.common.masterData.bank.service.usecase.BankUseCase;
import vn.vmg.ptdv.hola.common.masterData.exception.MasterDataException;

public class BankService {
    private final BankRepository bankRepository;
    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    public BankListResponse getBank() throws MasterDataException {
        BankUseCase bankUseCase = new BankUseCaseImpl(bankRepository);
        BankListResponse bankListResponse = bankUseCase
                .getBank()
                .endGetBank();
        return bankListResponse;
    }

}
