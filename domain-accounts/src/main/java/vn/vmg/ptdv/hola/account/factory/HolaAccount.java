package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.HolaProvider;
import vn.vmg.ptdv.hola.account.core.UserService;

@Data
public class HolaAccount {
    private AccountId accountId;
    private UserService shipService;
    private UserService shopService;
    private String email;
    private ShipperProfile shipperProfile;
    private String deviceToken;

    public boolean enabled(UserService userService) {
        return userService != null && (userService == shipService || userService == shopService);
    }

    public void setServiceFor(UserService userService) {
        if (userService.getServiceName() == HolaProvider.SHIP) {
            shipService = userService;
        }
        if (userService.getServiceName() == HolaProvider.SHOP) {
            shopService = userService;
        }
    }

}
