package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class HolaProfileDB {
    private Long id;
    private String phone;
    private String email;
    private Integer shop;
    private Integer ship;
    private String sessionKey;
    private Long accountEpurseId;
    private String deviceToken;
    private LocalDate birthday;
    private Integer sex;
    private String avatar;
    private String name;
    private Timestamp uTimestamp;
}
