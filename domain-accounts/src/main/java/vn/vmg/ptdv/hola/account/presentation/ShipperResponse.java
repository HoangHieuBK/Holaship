package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.factory.ShipperProfile;

@Data
public class ShipperResponse {
    private ShipperProfile shipperProfile;
    private Boolean status;
}
