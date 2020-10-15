package vn.vmg.ptdv.hola.account.repository;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.VARegisterRequest;

public interface VARepository {
    VAInfo getVAAccount(AccountId accountId);

    VAInfo registerVAAccount(VARegisterRequest vaRegisterRequest) throws AccountDomainException;

    boolean InsertVAAccount(VAInfo vaAccount) throws AccountDomainException;

    int updateVAAccount(VAInfo vaAccount);  //Update khi co app-shop
}
