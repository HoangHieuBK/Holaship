package vn.vmg.ptdv.hola.api.rest.va;

import lombok.Data;

@Data
public class VAJSONRequest {
    private Long userId;
    private String userName;
    private String otpCode;
    private String sessionKey;
    private String customerName;
    //    private String serviceName;
}
