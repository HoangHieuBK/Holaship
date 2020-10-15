package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class ProfileJSONRequest {
    private String phoneNumber;
    private String deviceToken;
    private String serviceName;
    private String displayName;
    private LocalDate birthday;
    private String avatar;
    private String email;
    private String gender;
    private Instant utimestamp;

    public ProfileJSONRequest() {
    }

    public ProfileJSONRequest(@PathVariable String phoneNumber, @RequestParam String deviceToken) {
        this.phoneNumber = phoneNumber;
        this.deviceToken = deviceToken;
    }

    public ProfileJSONRequest(@JsonProperty String phoneNumber, @JsonProperty String displayName,
            @JsonProperty LocalDate birthday, @JsonProperty String avatar, @JsonProperty String gender,
            @JsonProperty Instant utimestamp) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.birthday = birthday;
        this.avatar = avatar;
        this.gender = gender;
        this.utimestamp = utimestamp;
    }
}
