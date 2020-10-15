package vn.vmg.ptdv.hola.infra.imedia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TechConfirmInfoRequest {
    private static final String REQUEST_ID = "RequestId";
    private static final String REQUEST_TIME = "RequestTime";
    private static final String PARTNER_CODE = "PartnerCode";
    private static final String OPERATION = "Operation";
    private static final String BANK_NO ="BankNo";
    private static final String ACC_NO = "AccNo";
    private static final String ACC_TYPE="AccType";
    private static final String SIGNATURE= "Signature";

    private String requestId;
    private String requestTime;
    private String partnerCode;
    private Integer operation;
    private String bankNo;
    private String accNo;
    private int accType;
    private String signature;
    public TechConfirmInfoRequest(){

    }
    public TechConfirmInfoRequest(String requestId,String requestTime,String partnerCode,Integer operation,String bankNo,
            String accNo, int accType,String signature){
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.partnerCode = partnerCode;
        this.operation = operation;
        this.bankNo = bankNo;
        this.accNo = accNo;
        this.accType = accType;
        this.signature = signature;
    }
    public String asJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> sso = new HashMap<>();
        sso.put(REQUEST_ID, this.requestId);
        sso.put(REQUEST_TIME, this.requestTime);
        sso.put(PARTNER_CODE, this.partnerCode);
        sso.put(OPERATION, this.operation);
        sso.put(BANK_NO, this.bankNo);
        sso.put(ACC_NO, this.accNo);
        sso.put(ACC_TYPE, this.accType);
        sso.put(SIGNATURE,this.signature);
        return mapper.writeValueAsString(sso);
    }
}
