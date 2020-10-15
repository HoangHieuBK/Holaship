package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class IMediaTech {
    private String requestId;
    private String requestTime;
    private String partnerCode;
    private Integer operation;
    private String signature;
    private String requestAmount;

    public IMediaTech(String requestId, String requestTime, String partnerCode, Integer operation, String signature,
            String requestAmount) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.partnerCode = partnerCode;
        this.operation = operation;
        this.signature = signature;
        this.requestAmount = requestAmount;
    }
}
