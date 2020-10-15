package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.util.HashMap;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;


@Data
public class SSORegister {
    private int accountEpurseId;
    private String accIdentify;
    private String displayName;
    private String accPwd;
    private String cellPhone;
    private String accEmail;
    private int loginFrom;
    private int accStatus;
    private int receiveNotify;

    public SSORegister(String phoneNumber, String clearText, String email) {
        this.accIdentify = phoneNumber;
        this.accEmail = email;
        this.accPwd = clearText;
        this.cellPhone = phoneNumber;
        this.accountEpurseId = 0;
        this.receiveNotify = 1;
        this.accStatus = 0;
        this.loginFrom = 0;
        this.displayName = email;
    }

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> register = new HashMap<>();
        register.put("account_epurse_id", this.accountEpurseId);
        register.put("acc_identify", this.accIdentify);
        register.put("display_name", this.displayName);
        register.put("acc_pwd", this.accPwd);
        register.put("cell_phone", this.cellPhone);
        register.put("acc_email", this.accEmail);
        register.put("login_from", this.loginFrom);
        register.put("acc_status", this.accStatus);
        register.put("receive_notify", this.receiveNotify);

        return mapper.writeValueAsString(register);
    }

    private String toJsonGetInfo() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> register = new HashMap<>();
        register.put("username", this.cellPhone);

        return mapper.writeValueAsString(register);
    }

    public String encryptGetInfo()
            throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJsonGetInfo());
    }
}
