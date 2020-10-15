package vn.vmg.ptdv.hola.infra.jpa.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "APP_ADMINS")
public class AppAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "utimestamp", nullable = false)
    private Timestamp uTimestamp;
}
