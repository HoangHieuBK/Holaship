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
public class SSOForgotPassword {
    private String userName;
    private String otpCode;
    private String newPassword;

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> forgotPassword = new HashMap<>();
        forgotPassword.put("username", this.userName);
        forgotPassword.put("otp_code", this.otpCode);
        forgotPassword.put("newpassword", this.newPassword);
        return mapper.writeValueAsString(forgotPassword);
    }
}
