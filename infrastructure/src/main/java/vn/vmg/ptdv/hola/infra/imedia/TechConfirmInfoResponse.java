package vn.vmg.ptdv.hola.infra.imedia;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Map;

@Data
public class TechConfirmInfoResponse {

    private static final String REQUEST_ID = "RequestId";
    private static final String REQUEST_TIME = "RequestTime";
    private static final String PARTNER_CODE = "PartnerCode";
    private static final String OPERATION = "Operation";
    private static final String BANK_NO = "BankNo";
    private static final String ACC_NO = "AccNo";
    private static final String ACC_TYPE = "AccType";
    private static final String SIGNATURE = "Signature";
    private static final String REQUEST_AMOUNT = "RequestAmount";
    private static final String RESPONSE_CODE = "ResponseCode";
    private static final String RESPONSE_MESSAGE = "ResponseMessage";
    private static final String ACC_NAME = "AccName";

    private String requestId;
    private String requestTime;
    private String partnerCode;
    private int operation;
    private String bankNo;
    private String accNo;
    private int accType;
    private String signature;

    private String requestAmount;
    private int responseCode;
    private String responseMessage;
    private String accName;

    public TechConfirmInfoResponse() {

    }

    public TechConfirmInfoResponse(Map<String, Object> dataAsJson) {
        if (dataAsJson == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Du lieu khong ton tai");
        } else {
            requestId = dataAsJson.get("RequestId").toString();
            requestTime = dataAsJson.get("RequestTime").toString();
            partnerCode = dataAsJson.get("PartnerCode").toString();
            operation = (int) dataAsJson.get("Operation");
            bankNo = dataAsJson.get("BankNo").toString();
            accNo = dataAsJson.get("AccNo").toString();
            accType = Integer.valueOf(dataAsJson.get("AccType").toString());
            signature = dataAsJson.get("Signature").toString();
            requestAmount = dataAsJson.get("RequestAmount").toString();
            responseCode = (int) dataAsJson.get("ResponseCode");
            responseMessage = dataAsJson.get("ResponseMessage").toString();
            accName = dataAsJson.get("AccName").toString();
        }
    }
}
