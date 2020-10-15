package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.UserService;
@Data
public class AccountSearch {
    private AccountId accountId;
    private UserService userService;
    private String displayName;
    private Integer status;
}
