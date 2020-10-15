package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDevice {
    private Long id;
    private Long appUserId;
    private String deviceToken;
    private Integer shop;
    private Integer ship;
    private String confirm;
    private Timestamp utimestamp;
}
