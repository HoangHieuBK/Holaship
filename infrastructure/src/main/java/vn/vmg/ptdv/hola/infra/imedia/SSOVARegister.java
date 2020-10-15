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
public class SSOVARegister {
    private String username;
    private String otpCode;
    private String sesstionKey;
    private String customerName;
    private String requestId;
    private Integer serviceCode;

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> VARegister = new HashMap<>();
        VARegister.put("username", this.username);
        VARegister.put("otp_code", this.otpCode);
        VARegister.put("session_key", this.sesstionKey);
        VARegister.put("customer_name", this.customerName);
        VARegister.put("request_id", this.requestId);
        return mapper.writeValueAsString(VARegister);

    }
}
