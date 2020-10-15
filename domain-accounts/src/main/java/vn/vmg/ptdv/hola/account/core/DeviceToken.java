package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class DeviceToken {
    private String uniqueToken;
    private String type;
    private String MAC;
    private Boolean active;

    public DeviceToken() {
    }

    public DeviceToken(Boolean active) {
        this.active = active;
    }
    public DeviceToken(String uniqueToken){
        this.uniqueToken = uniqueToken;
    }

    public DeviceToken withUniqueToken(String deviceToken) {
        this.uniqueToken = deviceToken;
        return this;
    }

    public void isActive(DeviceToken deviceToken) {
        active = this.uniqueToken == null || !this.uniqueToken.equals(deviceToken.getUniqueToken());
    }
}
