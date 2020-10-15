package vn.vmg.ptdv.hola.account.service.usecase;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.BankIMediaProvider;
import vn.vmg.ptdv.hola.account.core.BankInfo;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.BankAccountResponse;
import vn.vmg.ptdv.hola.account.presentation.VARegisterRequest;
import vn.vmg.ptdv.hola.account.presentation.VAResponse;

public interface VAUseCase {
    VAUseCase findVAByUserId(AccountId accountId);

    VAUseCase findVAByUserIdNotFound() throws AccountDomainException;

    VAInfo endFindVAByUserId();

    VAUseCase createVAWalletIMedia(VARegisterRequest request) throws AccountDomainException;

    VAUseCase findVAByUserIdIsExist() throws AccountDomainException;

    VAUseCase createVAWallet() throws AccountDomainException;

    VAUseCase failCreateVA() throws AccountDomainException;

    VAResponse endCreateVA();

    VAUseCase findBankAccount(AccountId accountId, BankInfo info);

    VAUseCase findBankAccountByPhoneNumberIsExist() throws AccountDomainException;

    VAUseCase confirmBankInfoIMedia(AccountId accountId, BankIMediaProvider bankIMediaProvider,
            BankInfo bankInfo,String otp) throws AccountDomainException;

    VAUseCase createBankAccountIfNone(AccountId accountId) throws AccountDomainException;

    VAUseCase updateBankAccountElse(AuditLog auditLog, AccountId accountId) throws AccountDomainException;

    BankAccountResponse endConfirmBankAccount() throws AccountDomainException;

    VAUseCase failCreateBank() throws AccountDomainException;

    VAUseCase failUpdateBank() throws AccountDomainException;

    VAUseCase failIMediaResponse() throws AccountDomainException;

    VAUseCase getHolaAccount(AccountId accountId);

    VAUseCase failGetAccount() throws AccountDomainException;

}
