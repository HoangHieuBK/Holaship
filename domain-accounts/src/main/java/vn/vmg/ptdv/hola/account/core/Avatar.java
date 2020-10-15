package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class Avatar {
    private String path;
    private String base64;
    private String name;

    public Avatar withPath(String path) {
        this.path = path;
        return this;
    }
}
