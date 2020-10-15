package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.HolaService;

@Data
public class VARequest {
    private AccountId userId;
    private String userName;
    private String otp;
    private String sessionKey;
    private String customerName;
    private HolaService serviceName;

    public VARequest(String phoneNumber){
        this.userId = new AccountId(phoneNumber);
    }

    public VARequest(Long userId, String serviceName) {
        this.userId = new AccountId(userId);
        this.serviceName = new HolaService().withServiceName(serviceName);
    }
}
