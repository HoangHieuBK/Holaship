package vn.vmg.ptdv.hola.infra.jpa.servicepack.entity;

import lombok.Data;
import vn.vmg.ptdv.hola.infra.jpa.account.entity.AppAdmin;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "SERVICE_PACKS")
@Data
public class EServicePack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "code", length = 100, nullable = false)
    private String code;

    @Column(name = "status", length = 2)
    private Boolean status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AppAdmin createdBy;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AppAdmin updatedBy;

    @Column(name = "utimestamp", nullable = false)
    private Timestamp uTimestamp;
}
