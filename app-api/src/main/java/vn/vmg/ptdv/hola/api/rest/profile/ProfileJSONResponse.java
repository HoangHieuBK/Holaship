package vn.vmg.ptdv.hola.api.rest.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vn.vmg.ptdv.hola.account.factory.HolaProfile;

import java.time.Instant;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileJSONResponse {
    private String phoneNumber;
    private String displayName;
    //    private String deviceToken;
//    private Boolean isFirstLogin;
    private LocalDate birthday;
    private String avatar;
    private String email;
    private String gender;
    private Instant uTimestamp;

    public ProfileJSONResponse(HolaProfile profile) {
        this.phoneNumber = profile.getAccountId().getPhoneNumber();
        this.displayName = profile.getDisplayName();
        this.avatar = profile.getAvatar().getPath();
//        this.deviceToken = profile.getDeviceToken().getUniqueToken();
        this.email = profile.getEmail().getAddress();
        this.birthday = profile.getBirthDay();
//        this.isFirstLogin = profile.getDeviceToken().getActive();
        this.gender = profile.getGender().display();
        this.uTimestamp = profile.getAuditLog().getUTimestamp().toInstant();
    }

    public ProfileJSONResponse(HolaProfile profile, Boolean status) {
        this.phoneNumber = profile.getAccountId().getPhoneNumber();
        this.displayName = profile.getDisplayName();
        this.avatar = profile.getAvatar().getPath();
        this.birthday = profile.getBirthDay();
        this.gender = profile.getGender().display();
        this.uTimestamp = profile.getAuditLog().getUTimestamp().toInstant();
        this.email = profile.getEmail().getAddress();
    }

    public ProfileJSONResponse() {

    }
}
