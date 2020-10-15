package vn.vmg.ptdv.hola.infra.imedia;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.util.HashMap;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;
import static vn.vmg.ptdv.hola.infra.imedia.IMediaAPIConfig.CLIENT_IDENTITY;


@Data
public class SSOLogin {
    private String username;
    private String password;
    private int loginFrom;
    private String clientIdentityStr;
    private Long accountEpurseId;

    public SSOLogin() {
    }

    public SSOLogin(String phoneNumber, String passClearText) {
        this.username = phoneNumber;
        this.password = passClearText;
        this.clientIdentityStr = CLIENT_IDENTITY;
        this.loginFrom = 0;
    }

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> register = new HashMap<>();
        register.put("username", this.username);
        register.put("password", this.password);
        register.put("login_from", this.loginFrom);
        register.put("client_identity_str", this.clientIdentityStr);
        register.put("account_epurse_id", this.accountEpurseId);

        return mapper.writeValueAsString(register);
    }
}
