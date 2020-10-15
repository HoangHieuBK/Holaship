package vn.vmg.ptdv.hola.account.service.impl;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.BankIMediaProvider;
import vn.vmg.ptdv.hola.account.core.BankInfo;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.CashInfo;
import vn.vmg.ptdv.hola.account.factory.HolaAccount;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.BankAccountResponse;
import vn.vmg.ptdv.hola.account.presentation.VARegisterRequest;
import vn.vmg.ptdv.hola.account.presentation.VAResponse;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.account.service.usecase.VAUseCase;

public class VAUseCaseImpl implements VAUseCase {
    private final VARepository vaRepository;
    private final CashRepository cashRepository;
    private VAInfo vaInfo;
    private CashInfo cashInfo;
    private CashInfo cashInfoIMedia;
    private HolaAccount holaAccount;
    private boolean created = true;
    private boolean updated = true;
    private final AccountRepository accountRepository;

    public VAUseCaseImpl(VARepository vaRepository, CashRepository cashRepository,
            AccountRepository accountRepository) {
        this.vaRepository = vaRepository;
        this.cashRepository = cashRepository;
        this.accountRepository = accountRepository;
    }


    //getVaByUserId;
    @Override
    public VAUseCase findVAByUserId(AccountId accountId) {
        accountId.setId(holaAccount.getAccountId().getId());
        vaInfo = vaRepository.getVAAccount(accountId);
        return this;
    }

    @Override
    public VAUseCase findVAByUserIdNotFound() throws AccountDomainException {
        if (vaInfo == null) {
            throw new AccountDomainException("VA-wallet khong ton tai");
        }
        return this;
    }


    @Override
    public VAInfo endFindVAByUserId() {

        return vaInfo;
    }

    //Register VA-wallet;

    @Override
    public VAUseCase createVAWalletIMedia(VARegisterRequest request) throws AccountDomainException {
        vaInfo = vaRepository.registerVAAccount(request);
        return this;
    }

    @Override
    public VAUseCase findVAByUserIdIsExist() throws AccountDomainException {
        if (vaInfo != null) {
            //To do khi app-shop(update ship shop tuong ung)
            throw new AccountDomainException("VA-wallet da ton tai!");
        }
        return this;
    }

    @Override
    public VAUseCase createVAWallet() throws AccountDomainException {
        created = vaRepository.InsertVAAccount(vaInfo);
        return this;
    }

    @Override
    public VAUseCase failCreateVA() throws AccountDomainException {
        if (!created) {
            throw new AccountDomainException("Insert fail va wallet into db");
        }
        return this;
    }

    @Override
    public VAResponse endCreateVA() {
        VAResponse response = new VAResponse();
        response.setShipCode(vaInfo.getShipCode());
        response.setUserName(vaInfo.getUserName());
        response.setShopCode(vaInfo.getShopCode());
        return response;
    }


    //confirm-bank
    @Override
    public VAUseCase findBankAccount(AccountId accountId, BankInfo info) {
        accountId.setId(holaAccount.getAccountId().getId());
        cashInfo = cashRepository.getBankAccount(accountId, info);
        return this;
    }

    @Override
    public VAUseCase findBankAccountByPhoneNumberIsExist() throws AccountDomainException {
        if (cashInfo != null) {
            throw new AccountDomainException("Bank-account da ton tai");
        }
        return this;
    }

    @Override
    public VAUseCase confirmBankInfoIMedia(AccountId accountId, BankIMediaProvider bankIMediaProvider,
            BankInfo bankInfo, String otp) throws AccountDomainException {
        boolean verifyIMedia = accountRepository.verifyIMedia(accountId.getPhoneNumber(), otp);
        if (verifyIMedia) {
            cashInfoIMedia = cashRepository.confirmInfoCash(accountId, bankIMediaProvider, bankInfo);
        }
        return this;
    }

    @Override
    public VAUseCase failIMediaResponse() throws AccountDomainException {
        if (cashInfoIMedia == null) {
            throw new AccountDomainException("Response IMedia == null");
        }
        return this;
    }

    @Override
    public VAUseCase failGetAccount() throws AccountDomainException {
        if (holaAccount.getAccountId().getId() == null) {
            throw new AccountDomainException("Không tim thấy user");
        }
        return this;
    }

    @Override
    public VAUseCase getHolaAccount(AccountId accountId) {
        holaAccount = accountRepository.findByAccountId(accountId, null);
        return this;
    }

    @Override
    public VAUseCase createBankAccountIfNone(AccountId accountId) throws AccountDomainException {
        if (cashInfo.getBankInfo() == null) {
            accountId.setId(holaAccount.getAccountId().getId());
            created = cashRepository.insertCash(cashInfoIMedia, accountId);
        }
        return this;
    }

    @Override
    public VAUseCase updateBankAccountElse(AuditLog auditLog, AccountId accountId) throws AccountDomainException {
//        if (cashInfo.getBankInfo() != null) {
//            updated = cashRepository.updateCash(cashInfoIMedia, auditLog, accountId);
//        }
        return this;
    }

    @Override
    public VAUseCase failCreateBank() throws AccountDomainException {
        if (!created) {
            throw new AccountDomainException("Insert fail bank into db usecase");
        }
        return this;
    }

    @Override
    public VAUseCase failUpdateBank() throws AccountDomainException {
        if (!updated) {
            throw new AccountDomainException("Update fail bank usecase");
        }
        return this;
    }

    @Override
    public BankAccountResponse endConfirmBankAccount() {
        BankAccountResponse response = new BankAccountResponse();
        BankInfo info = cashRepository.getBankNameByBankCode(cashInfoIMedia.getBankInfo().getBankCode());
        response.setBankAccount(cashInfoIMedia.getBankInfo().getBankAccount());
        response.setBankAccountName(cashInfoIMedia.getBankInfo().getBankAccountName());
        response.setBankName(info.getBankName());
        response.setType(cashInfoIMedia.getBankInfo().getType());
        response.setBankCode(cashInfoIMedia.getBankInfo().getBankCode());
        response.setBankShortName(info.getCode());
        response.setPartnerCode(cashInfoIMedia.getBankIMediaProvider().getPartnerCode());
        response.setRequestAmount(cashInfoIMedia.getBankIMediaProvider().getRequestAmount());
        return response;
    }


}
