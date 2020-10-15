package vn.vmg.ptdv.hola.groupmanager.core;

import lombok.Data;

@Data
public class GroupManagerId {
    private Long id;
    private String code;

    public GroupManagerId() {
    }

    public GroupManagerId(String code) {
        this.code = code;
    }

    public GroupManagerId(Long id, String code) {
        this.id = id;
        this.code = code;
    }
}
