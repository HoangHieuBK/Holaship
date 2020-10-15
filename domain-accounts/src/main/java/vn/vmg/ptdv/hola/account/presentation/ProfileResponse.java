package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.factory.HolaProfile;

@Data
public class ProfileResponse {
    private HolaProfile profile;
    private Boolean status;
}
