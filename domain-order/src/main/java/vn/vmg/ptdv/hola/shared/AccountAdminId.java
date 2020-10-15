package vn.vmg.ptdv.hola.shared;

import lombok.Data;

@Data
public class AccountAdminId {

    private Long id;
    private String username;
    private String name;

    public AccountAdminId() {
    }

    public AccountAdminId(String name) {
        this.name = name;
    }

    public AccountAdminId(Long id) {
        this.id = id;
    }

    public AccountAdminId(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
