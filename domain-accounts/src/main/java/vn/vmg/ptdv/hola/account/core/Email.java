package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class Email {
    private String address;
    private String domain;

    public Email withAddress(String address) {
        this.address = address;
        return this;
    }
}
