package vn.vmg.ptdv.hola.infra.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.BankIMediaProvider;
import vn.vmg.ptdv.hola.account.core.BankInfo;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.CashInfo;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.infra.account.factory.BankAccountDB;
import vn.vmg.ptdv.hola.infra.account.factory.BankDB;
import vn.vmg.ptdv.hola.infra.account.mapper.CashMapper;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestAPI;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestException;
import vn.vmg.ptdv.hola.infra.imedia.TechConfirmInfoRequest;
import vn.vmg.ptdv.hola.infra.imedia.TechConfirmInfoResponse;

public class CashRepositoryImpl implements CashRepository {
    private final IMediaRestAPI iMediaRestAPI;
    private final CashJDBC cashJDBC;

    public CashRepositoryImpl(IMediaRestAPI iMediaRestAPI, CashJDBC cashJDBC) {
        this.iMediaRestAPI = iMediaRestAPI;
        this.cashJDBC = cashJDBC;
    }

    @Override
    public CashInfo confirmInfoCash(AccountId accountId, BankIMediaProvider bankIMediaProvider,
            BankInfo bankInfo) throws AccountDomainException {
        TechConfirmInfoRequest techConfirmInfoRequest = new TechConfirmInfoRequest();
        techConfirmInfoRequest.setPartnerCode(bankIMediaProvider.getPartnerCode());
        techConfirmInfoRequest.setOperation(bankIMediaProvider.getOperation());
        techConfirmInfoRequest.setBankNo(bankInfo.getBankCode());
        techConfirmInfoRequest.setAccNo(bankInfo.getBankAccount());
        techConfirmInfoRequest.setAccType(bankInfo.getType());
        TechConfirmInfoResponse techConfirmInfoResponse;
        try {
            techConfirmInfoResponse = iMediaRestAPI.confirmInfoCash(techConfirmInfoRequest);
            CashInfo cashInfo = new CashInfo();
            cashInfo.setBankIMediaProvider(new BankIMediaProvider(techConfirmInfoResponse.getPartnerCode(),
                    techConfirmInfoResponse.getOperation(), techConfirmInfoResponse.getRequestAmount()));


            cashInfo.setBankInfo(new BankInfo(techConfirmInfoResponse.getBankNo(), techConfirmInfoResponse.getAccNo(),
                    techConfirmInfoResponse.getAccType(), techConfirmInfoResponse.getAccName()));

            return cashInfo;
        } catch (IMediaRestException exception) {
            throw new AccountDomainException(exception.getCode(), exception.getMessage());
        } catch (JsonProcessingException | SecurityDESException e) {
            throw new AccountDomainException(e.getMessage());
        }
    }

    @Override
    public boolean insertCash(CashInfo info, AccountId accountId) throws AccountDomainException {
        BankAccountDB bankAccountDB = CashMapper.getInstance().fromDomainInsertCash(info);
        bankAccountDB.setPhone(accountId.getPhoneNumber());
        bankAccountDB.setAppUserId(accountId.getId());
        boolean created = cashJDBC.InsertBank(bankAccountDB);
        if (!created) {
            throw new AccountDomainException("fail insert bank to db");
        }
        return true;
    }

    @Override
    public BankInfo getBankNameByBankCode(String bankCode) {
        BankDB bankDB = cashJDBC.findBankNameByBankCode(bankCode);
        BankInfo info = new BankInfo();
        info.setBankName(bankDB.getName());
        info.setCode(bankDB.getCode());
        return info;
    }


    @Override
    public CashInfo getBankAccount(AccountId accountId, BankInfo bankInfo) {
        CashInfo info = new CashInfo();
        BankAccountDB response = cashJDBC.findBankAccount(accountId,bankInfo);
        if (response != null) {
            info.setBankInfo(new BankInfo(response.getBankAccount(), response.getBankAccountName(), response.getType(),
                    response.getBankCode(), response.getBankName()));
            info.setAccountId(new AccountId((response.getAppUserId())));
        }
        return info;
    }

}
