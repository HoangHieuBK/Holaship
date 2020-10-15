package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

@Data
public class HolaAccountDB {
    private Long id;
    private String phone;
    private String email;
    private Integer shop;
    private Integer ship;
    private String sessionKey;
    private Long accountEpurseId;
    private String deviceToken;
}
