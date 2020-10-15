package vn.vmg.ptdv.hola.infra.jpa.servicepack.entity;

import lombok.Data;
import vn.vmg.ptdv.hola.infra.jpa.account.entity.AppAdmin;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "service_pack_settings")
public class EServicePackSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_PACK_ID")
    private EServicePack eServicePack;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private AppAdmin createdBy;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private AppAdmin updatedBy;

    @Column(name = "utimestamp", nullable = false)
    private Timestamp uTimestamp;

    //basic info
    @Column(name = "MAX_TIME")
    private Integer deliveryTimeMax;

    @Column(name = "base_cost")
    private Double priceBase;

    @Column(name = "EFFECTIVE_AT")
    private LocalDate effectiveDate;

    @Column(name = "ACTIVE")
    private Boolean effectiveStatus;

    @Column(name = "NOTE")
    private String description;

    //distance
    @Column(name = "MAX_DISTANCE")
    private Integer distanceMax;

    @Column(name = "BASE_DISTANCE")
    private Integer distanceBase;

    @Column(name = "SURCHARGE_DISTANCE")
    private Double distanceCost;

    //destination
    @Column(name = "MAX_POINT")
    private Integer destinationMax;

    @Column(name = "BASE_POINT")
    private Integer destinationBase;

    @Column(name = "SURCHARGE_POINT")
    private Double destinationCost;

    //order detail
    @Column(name = "MAX_ORDER")
    private Integer detailOrderMax;

    @Column(name = "BASE_ORDER_DETAIL")
    private Integer detailOrderBase;

    @Column(name = "SURCHARGE_ORDER_DETAIL")
    private Double detailOrderCost;

    //other
    @Column(name = "PORTER_FEE")
    private Double porterage;

    @Column(name = "DOOR_DELIVERY_FEE")
    private Double d2dCost;

    @Column(name = "REFUND_FEE")
    private Double refundCost;

    @Column(name = "PRICE_DECLARE_FEE")
    private Double declarePriceCost;

    @Column(name = "OTHER_COST")
    private Double otherCost;

    @Column(name = "type")
    private Integer type;
}
