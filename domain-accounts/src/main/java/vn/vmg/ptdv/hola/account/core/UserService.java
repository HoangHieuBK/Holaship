package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class UserService {
    private HolaProvider serviceName;
    private boolean enabled;

    public UserService(String userService) {
        this.serviceName = HolaProvider.valueOf(userService);
    }

    public Integer getServiceShip() {
        if (serviceName == HolaProvider.SHIP) {
            return 1;
        }

        return null;
    }

    public Integer getServiceShop() {
        if (serviceName == HolaProvider.SHOP) {
            return 1;
        }

        return null;
    }
}
