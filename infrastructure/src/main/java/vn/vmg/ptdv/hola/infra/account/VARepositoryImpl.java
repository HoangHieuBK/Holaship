package vn.vmg.ptdv.hola.infra.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.VARegisterRequest;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.infra.account.factory.VADB;
import vn.vmg.ptdv.hola.infra.account.mapper.VAMapper;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestAPI;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestException;
import vn.vmg.ptdv.hola.infra.imedia.IMediaVAResponse;
import vn.vmg.ptdv.hola.infra.imedia.SSOVARegister;

public class VARepositoryImpl implements VARepository {
    private final IMediaRestAPI iMediaRestAPI;
    private final VAJDBC vajdbc;

    public VARepositoryImpl(IMediaRestAPI iMediaRestAPI, VAJDBC vajdbc) {
        this.iMediaRestAPI = iMediaRestAPI;
        this.vajdbc = vajdbc;
    }

    @Override
    public VAInfo getVAAccount(AccountId accountId) {
        VAInfo info = new VAInfo();
        VADB vaAccountDB = vajdbc.findVAWallet(accountId);
        if (vaAccountDB != null) {
            info.setBankCode(vaAccountDB.getBankCode());
            info.setBankName(vaAccountDB.getBankName());
            info.setShipCode(vaAccountDB.getShipCode());
            info.setShopCode(vaAccountDB.getShopCode());
            info.setUserName(vaAccountDB.getUserName());
            return info;
        }
        return null;

    }

    @Override
    public VAInfo registerVAAccount(VARegisterRequest vaRegisterRequest) throws AccountDomainException {
        SSOVARegister ssovaRegister = new SSOVARegister();
        ssovaRegister.setUsername(vaRegisterRequest.getUserName());
        ssovaRegister.setOtpCode(vaRegisterRequest.getOtp());
        ssovaRegister.setSesstionKey(vaRegisterRequest.getSessionKey().getValue());
        ssovaRegister.setCustomerName(vaRegisterRequest.getCustomerName());
        IMediaVAResponse iMediaVAResponse;
        try {
            iMediaVAResponse = iMediaRestAPI.registerVAAccount(ssovaRegister);
            VAInfo vaAccount = new VAInfo();
            vaAccount.setUserId(vaRegisterRequest.getAccountId().getId());
            vaAccount.setUserName(iMediaVAResponse.getAccountName());
            vaAccount.setShipCode(iMediaVAResponse.getAccountNo());
            vaAccount.setShopCode(null); //Update kho co shop
            vaAccount.setBankCode(iMediaVAResponse.getBankCode());
            vaAccount.setBankName(iMediaVAResponse.getBankName());
            return vaAccount;
        } catch (IMediaRestException exception) {
            throw new AccountDomainException(exception.getCode(), exception.getMessage());
        } catch (JsonProcessingException | SecurityDESException exception) {
            throw new AccountDomainException(exception.getMessage());
        }

    }

    @Override
    public boolean InsertVAAccount(VAInfo vaAccount) throws AccountDomainException {
        VADB vaAccountDB = VAMapper.getInstance().fromDomainRegisterVA(vaAccount);
        boolean created = vajdbc.InsertVA(vaAccountDB);
        if (!created) {
            throw new AccountDomainException(" insert VA error to DB ");
        }
        return true;
    }

    @Override
    public int updateVAAccount(VAInfo vaAccount) {
        //Todo khi co app shop
        return 0;
    }

}
