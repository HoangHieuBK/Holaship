package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class VerificationJSONRequest {
    private String otp;
    private Instant uTimestamp;
    private String serviceName;
    private String deviceToken;

    public VerificationJSONRequest(
            @JsonProperty("otp") String otp,
            @JsonProperty("uTimestamp") Instant uTimestamp,
            @JsonProperty("serviceName") String serviceName,
            @JsonProperty("deviceToken") String deviceToken) {
        this.otp = otp;
        this.uTimestamp = uTimestamp;
        this.serviceName = serviceName;
        this.deviceToken = deviceToken;
    }
}
