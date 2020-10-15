package vn.vmg.ptdv.hola.infra.imedia;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import vn.vmg.ptdv.hola.common.des.TripleDES;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static vn.vmg.ptdv.hola.common.des.TripleDESConfig.KEY3DES;


@Data
public class IMediaResponse {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "acc_pwd";
    private static final String REMAIN_BALANCE = "remain_balance";
    private static final String HOLDING_BALANCE = "holding_balance";
    private static final String REQUIRE_CHANGE_PASS = "require_change_pass";
    private static final String DISPLAY_NAME = "display_name";
    private static final String LOGIN_FROM = "login_from";
    private static final String CLIENT_IDENTITY_STR = "client_identity_str";
    private static final String SESSION_KEY = "session_key";
    private static final String ACCOUNT_EPURSE_ID = "account_epurse_id";
    private static final String BIRTHDAY = "birthday";
    private static final String EMAIL = "acc_email";
    private static final String PHONE = "cell_phone";
    private static final String OTP_CODE = "otp_code";
    private String username;
    private String password;
    private String newPassword;
    private int remainBalance;
    private int holdingBalance;
    private int requireChangePass;
    private String displayName;
    private int loginFrom;
    private String clientIdentityStr;
    private String email;
    private LocalDate birthday;
    private String sessionKey;
    private Long accountEpurseId;
    private String idFullName;
    private String idNumber;
    private String phone;
    private String otpCode;
    private int status;

    public IMediaResponse() {
    }

    public IMediaResponse(Map<String, Object> dataAsJson) {
//        if (dataAsJson == null) {
//            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Du lieu khong ton tai");
//        }
        if (dataAsJson.containsKey(USERNAME)) {
            this.username = String.valueOf(dataAsJson.getOrDefault(USERNAME, ""));
        }
        if (dataAsJson.containsKey(PASSWORD)) {
            this.password = String.valueOf(dataAsJson.getOrDefault(PASSWORD, ""));
        }
        if (dataAsJson.containsKey(REMAIN_BALANCE)) {
            this.remainBalance = (int) dataAsJson.getOrDefault(REMAIN_BALANCE, 0);
        }
        if (dataAsJson.containsKey(HOLDING_BALANCE)) {
            this.holdingBalance = (int) dataAsJson.getOrDefault(HOLDING_BALANCE, 0);
        }
        if (dataAsJson.containsKey(REQUIRE_CHANGE_PASS)) {
            this.requireChangePass = (int) dataAsJson.getOrDefault(REQUIRE_CHANGE_PASS, 0);
        }
        if (dataAsJson.containsKey(DISPLAY_NAME)) {
            this.displayName = String.valueOf(dataAsJson.getOrDefault(DISPLAY_NAME, ""));
        }
        if (dataAsJson.containsKey(LOGIN_FROM)) {
            this.loginFrom = (int) dataAsJson.getOrDefault(LOGIN_FROM, 0);
        }
        if (dataAsJson.containsKey(CLIENT_IDENTITY_STR)) {
            this.clientIdentityStr = String.valueOf(dataAsJson.getOrDefault(CLIENT_IDENTITY_STR, ""));
        }
        if (dataAsJson.containsKey(SESSION_KEY)) {
            this.sessionKey = String.valueOf(dataAsJson.getOrDefault(SESSION_KEY, ""));
        }
        if (dataAsJson.containsKey(ACCOUNT_EPURSE_ID)) {
            this.accountEpurseId = ((Integer) dataAsJson.getOrDefault(ACCOUNT_EPURSE_ID, 0)).longValue();
        }
        if (dataAsJson.containsKey(EMAIL)) {
            this.email = String.valueOf(dataAsJson.getOrDefault(EMAIL, ""));
        }
        if (dataAsJson.containsKey(BIRTHDAY)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                    .withLocale(Locale.FRENCH);
            this.birthday = LocalDate.parse(String.valueOf(dataAsJson.getOrDefault(EMAIL, "")), formatter);
//            this.birthday = LocalDate(Long.valueOf((String) dataAsJson.getOrDefault(BIRTHDAY, 0));
        }
        if (dataAsJson.containsKey(PHONE)) {
            this.phone = String.valueOf(dataAsJson.getOrDefault(PHONE, ""));
        }
    }

    public String encrypt() throws JsonProcessingException, SecurityDESException {
        return TripleDES.encrypt(KEY3DES, this.toJson());
    }

    private String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> register = new HashMap<>();
        register.put("username", this.username);
        register.put("password", this.password);
        register.put("newpassword", this.newPassword);
        register.put("remain_balance", this.remainBalance);
        register.put("holding_balance", this.holdingBalance);
        register.put("require_change_pass", this.requireChangePass);
        register.put("display_name", this.displayName);
        register.put("login_from", this.loginFrom);
        register.put("client_identity_str", this.clientIdentityStr);
        register.put("session_key", this.sessionKey);
        register.put("account_epurse_id", this.accountEpurseId);
        register.put("id_full_name", this.idFullName);
        register.put("id_number", this.idNumber);
        register.put("acc_email", this.email);
        register.put("cell_phone", this.phone);
        register.put("birthday", this.birthday);
        register.put(OTP_CODE, this.otpCode);

        return mapper.writeValueAsString(register);
    }


}
