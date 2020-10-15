package vn.vmg.ptdv.hola.common.masterData.bank.service.usecase;

import vn.vmg.ptdv.hola.common.masterData.bank.presentation.BankListResponse;

public interface BankUseCase {
    BankUseCase getBank();

    BankListResponse endGetBank();
}
