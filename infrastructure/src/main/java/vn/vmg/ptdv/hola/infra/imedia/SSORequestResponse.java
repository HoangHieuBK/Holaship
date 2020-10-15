package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@Data
public class SSORequestResponse {
    private static final String P_PCODE = "p_code";
    private static final String P_DATA = "data";
    private static final String P_SIGNATURE = "signature";
    private static final String P_SERVICE_CODE = "service_code";
    private static final String P_CLIENT_TYPE = "client_type";
    private static final String P_CLIENT_VERSION = "client_version";
    private static final String P_STATUS = "status";
    private static final String P_RESPONSE_MSG = "response_msg";
    private static final String SESSION_KEY = "session_key";
    private int pCode;
    private String data;
    private String responseData;
    private long balBefore;
    private long balAfter;
    private long holdingBalBefore;
    private long holdingBalAfter;
    private SSOUserData ssoUserData;
    private String sessionKey;
    private String signature;
    private String serviceCode;
    private int clientType;
    private String clientVersion;
    private int status;
    private String responseMsg;
    private String macAddress;
    private String totalAmount;
    private String totalQuantity;

    public SSORequestResponse(int pCode, String data, String signature, String serviceCode, int clientType,
            String clientVersion) {
        this.pCode = pCode;
        this.data = data;
        this.signature = signature;
        this.serviceCode = serviceCode;
        this.clientType = clientType;
        this.clientVersion = clientVersion;
    }

    public SSORequestResponse(int pCode, String data, String signature, String serviceCode, int clientType,
            String clientVersion, String sessionKey) {
        this.pCode = pCode;
        this.data = data;
        this.signature = signature;
        this.serviceCode = serviceCode;
        this.clientType = clientType;
        this.clientVersion = clientVersion;
        this.sessionKey = sessionKey;
    }

    public SSORequestResponse(Map<String, Object> dataAsJson) throws HttpServerErrorException {
        if (dataAsJson == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Du lieu khong ton tai");
        }
        if (dataAsJson.containsKey(P_DATA)) {
            this.data = String.valueOf(dataAsJson.getOrDefault(P_DATA, ""));
        }
        if (dataAsJson.containsKey(P_STATUS)) {
            this.status = (int) dataAsJson.getOrDefault(P_STATUS, 0);
        }
        if (dataAsJson.containsKey(P_RESPONSE_MSG)) {
            this.responseMsg = String.valueOf(dataAsJson.getOrDefault(P_RESPONSE_MSG, ""));
        }
    }

    public String asJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> sso = new HashMap<>();
        sso.put(P_PCODE, this.pCode);
        sso.put(P_DATA, this.data);
        sso.put(P_SIGNATURE, this.signature);
        sso.put(P_SERVICE_CODE, this.serviceCode);
        sso.put(P_CLIENT_TYPE, this.clientType);
        sso.put(P_CLIENT_VERSION, this.clientVersion);
        sso.put(SESSION_KEY, this.sessionKey);

        return mapper.writeValueAsString(sso);
    }

}
