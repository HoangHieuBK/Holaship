package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class SessionKey {
    private String value;
    private String accessToken;
    private String refreshToken;

    public SessionKey() {
    }

    public SessionKey(String value) {
        this.value = value;
    }

    public SessionKey(String value, String accessToken, String refreshToken) {
        this.value = value;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
