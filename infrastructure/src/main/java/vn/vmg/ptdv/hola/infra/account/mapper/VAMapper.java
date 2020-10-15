package vn.vmg.ptdv.hola.infra.account.mapper;

import org.springframework.beans.BeanUtils;
import vn.vmg.ptdv.hola.account.factory.VAInfo;
import vn.vmg.ptdv.hola.account.presentation.VARequest;
import vn.vmg.ptdv.hola.infra.account.factory.VADB;
import vn.vmg.ptdv.hola.infra.imedia.SSOVARegister;

public class VAMapper {
    private static VAMapper INSTANCE;

    private VAMapper() {

    }

    public static VAMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VAMapper();
        }
        return INSTANCE;
    }


    public static VAInfo fromDB(VADB vaAccountDB) {
        VAInfo vaAccount = new VAInfo();
        BeanUtils.copyProperties(vaAccountDB, vaAccount);
        return vaAccount;
    }

    public static VADB fromDomain() {
        VADB vaAccountDB = new VADB();
        return vaAccountDB;
    }

    public static SSOVARegister mapFromDomain(VARequest vaRegisterRequest) {
        SSOVARegister ssovaRegister = new SSOVARegister();
        if (vaRegisterRequest != null) {
            ssovaRegister.setCustomerName(vaRegisterRequest.getUserName());
            ssovaRegister.setOtpCode(vaRegisterRequest.getOtp());
            ssovaRegister.setSesstionKey(vaRegisterRequest.getSessionKey());
            ssovaRegister.setUsername(vaRegisterRequest.getUserName());
        }
        return ssovaRegister;
    }

    public static VADB fromDomainRegisterVA(VAInfo vaInfo) {
        VADB vaAccountDB = new VADB();
        if (vaInfo != null) {
            vaAccountDB.setUserId(vaInfo.getUserId());
            vaAccountDB.setUserName(vaInfo.getUserName());
            vaAccountDB.setShopCode(vaInfo.getShopCode());
            vaAccountDB.setShipCode(vaInfo.getShipCode());
            vaAccountDB.setBankCode(vaInfo.getBankCode());
            vaAccountDB.setBankName(vaInfo.getBankName());
        }
        return vaAccountDB;
    }
}
