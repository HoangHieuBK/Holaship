package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class AccountId {
    private String phoneNumber;
    private Long id;

    public AccountId() {
    }

    public AccountId(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccountId(Long id) {
        this.id = id;
    }

    public AccountId(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
}
