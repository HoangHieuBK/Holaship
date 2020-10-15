package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.util.HashMap;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;

@Data
public class IMediaVAResponse {
    private static final String BANK_CODE = "bank_code";
    private static final String BANK_NAME = "bank_name";
    private static final String ACCOUNT_NO = "account_no";
    private static final String ACCOUNT_NAME = "account_name";
    private String bankCode;
    private String bankName;
    private String accountNo;
    private String accountName;

    public IMediaVAResponse() {
    }

    public IMediaVAResponse(Map<String, Object> dataAsJson) throws HttpServerErrorException {
        if (dataAsJson == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Du lieu khong ton tai");
        }
        if (dataAsJson.containsKey(BANK_CODE)) {
            this.bankCode = String.valueOf(dataAsJson.getOrDefault(BANK_CODE, ""));
        }
        if (dataAsJson.containsKey(BANK_NAME)) {
            this.bankName = String.valueOf(dataAsJson.getOrDefault(BANK_NAME, ""));
        }
        if (dataAsJson.containsKey(ACCOUNT_NO)) {
            this.accountNo = String.valueOf(dataAsJson.getOrDefault(ACCOUNT_NO, ""));
        }
        if (dataAsJson.containsKey(ACCOUNT_NAME)) {
            this.accountName = String.valueOf(dataAsJson.getOrDefault(ACCOUNT_NAME, ""));
        }
    }

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> vARegister = new HashMap<>();
        vARegister.put("bank_code", this.bankCode);
        vARegister.put("bank_name", this.bankName);
        vARegister.put("account_no", this.accountNo);
        vARegister.put("account_name", this.bankName);
        return mapper.writeValueAsString(vARegister);
    }
}
