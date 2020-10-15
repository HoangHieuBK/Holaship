package vn.vmg.ptdv.hola.api.rest.password;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class PasswordJSONRequest {
    private String phoneNumber;
    private String step;
    private String otp;
    private String password;
    private String confirmPassword;

    public PasswordJSONRequest() {
    }

    public PasswordJSONRequest(
            @RequestParam String phoneNumber,
            @RequestParam String step) {
        this.phoneNumber = phoneNumber;
        this.step = step;
    }

    public PasswordJSONRequest(
            @JsonProperty String phoneNumber,
            @JsonProperty String step,
            @JsonProperty String otp,
            @JsonProperty String password,
            @JsonProperty String confirmPassword) {
        this.phoneNumber = phoneNumber;
        this.step = step;
        this.otp = otp;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
