package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class AccountUserAutoSuggestRequest {
    private String id;
    private String displayName;
    private String phone;

    public AccountUserAutoSuggestRequest(@RequestParam(required = false) String id, String displayName, String phone) {
        this.id = id;
        this.displayName = displayName;
        this.phone = phone;
    }
}
