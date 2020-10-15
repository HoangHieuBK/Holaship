package vn.vmg.ptdv.hola.infra.imedia;


import vn.vmg.ptdv.hola.account.factory.LoginInfo;
import vn.vmg.ptdv.hola.account.factory.RegisterInfo;

public class IMediaDataMapper {
    public static LoginInfo fromRest(IMediaResponse iMediaLogged, LoginInfo loginInfo) {
        loginInfo.setPhone(iMediaLogged.getUsername());
        loginInfo.setSessionKey(iMediaLogged.getSessionKey());
        loginInfo.setAccountEpurseId(iMediaLogged.getAccountEpurseId());
        loginInfo.setDisplayName(iMediaLogged.getDisplayName());
        loginInfo.setEmail(iMediaLogged.getEmail());
        return loginInfo;
    }

    public static RegisterInfo fromRest(IMediaResponse iMediaLogged, RegisterInfo registerInfo) {
        registerInfo.setAccountEpurseId(iMediaLogged.getAccountEpurseId());
        registerInfo.setEmail(iMediaLogged.getEmail());
        registerInfo.setPhone(iMediaLogged.getPhone());
        registerInfo.setFullName(iMediaLogged.getIdFullName());
        registerInfo.setStatus(iMediaLogged.getStatus());

        return registerInfo;
    }

}
