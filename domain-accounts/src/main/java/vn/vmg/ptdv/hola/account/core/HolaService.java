package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class HolaService {
    private HolaProvider shipService;
    private HolaProvider shopService;


    public HolaService withShipService(Integer ship) {
        this.shipService = ship == 1 ? HolaProvider.SHIP : null;
        return this;
    }

    public HolaService withShopService(Integer shop) {
        this.shopService = shop == 1 ? HolaProvider.SHOP : null;
        return this;
    }

    public HolaService withServiceName(String serviceName) {
        if (HolaProvider.SHIP.name().equals(serviceName)) {
            return withShipService(1);
        }
        if (HolaProvider.SHOP.name().equals(serviceName)) {
            return withShopService(1);
        }
        return this;
    }
}
