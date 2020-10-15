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
public class SSOUserData {
    private String username;
    private String password;
    private String newPassword;
    private Long remainBalance;
    private Long holdingBalance;
    private Integer requireChangePass;
    private String otherSystemAuthUserId;
    private String displayName;
    private Integer loginFrom;
    private String clientIdentityStr;
    private String email;
    private String phone;
    private String address;
    private String idNumber;
    private String sessionKey;
    private String idFullName;
    private String birthday;
    private String otpCode;
    private long accountEpurseId;

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> register = new HashMap<>();
        register.put("username", this.username);

        return mapper.writeValueAsString(register);
    }
}
