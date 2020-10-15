package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class BasicProfileDB {
    private Long id;
    private String phone;
    private Timestamp uTimestamp;
    private String name;
    private Integer sex;
    private LocalDate birthday;
    private String avatar;
}
