package vn.vmg.ptdv.hola.infra.leadtime.factory;

import lombok.Data;
import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.sql.Timestamp;

@Data
public class LeadtimeRouteDB {

    public static final String ID = "ID";
    public static final String LEADTIME_PACK_ID = "LEADTIME_PACK_ID";
    public static final String CODE = "CODE";
    public static final String TYPE = "TYPE";
    public static final String STATUS = "STATUS";
    public static final String FROM_PROVINCE_ID = "FROM_PROVINCE_ID";
    public static final String FROM_PROVINCE_CODE = "FROM_PROVINCE_CODE";
    public static final String TO_PROVINCE_ID = "TO_PROVINCE_ID";
    public static final String TO_PROVINCE_CODE = "TO_PROVINCE_CODE";
    public static final String ESTIMATED_DELIVERY_DAY = "ESTIMATED_DELIVERY_DAY";
    public static final String TRANSPORT_TYPE = "TRANSPORT_TYPE";
    public static final String LIMIT_WEIGHT = "LIMIT_WEIGHT";
    public static final String LIMIT_TYPE = "LIMIT_TYPE";
    public static final String MAX_WEIGHT = "MAX_WEIGHT";
    public static final String FROM_DISTRICT_ID = "FROM_DISTRICT_ID";
    public static final String TO_DISTRICT_ID = "TO_DISTRICT_ID";
    public static final String FROM_DISTRICT_CODE = "FROM_DISTRICT_CODE";
    public static final String TO_DISTRICT_CODE = "TO_DISTRICT_CODE";
    public static final String UTIMESTAMP = "UTIMESTAMP";

    public static final String FROM_PROVINCE_NAME = "FROM_PROVINCE_NAME";
    public static final String TO_PROVINCE_NAME = "TO_PROVINCE_NAME";
    public static final String FROM_DISTRICT_NAME = "FROM_DISTRICT_NAME";
    public static final String TO_DISTRICT_NAME = "TO_DISTRICT_NAME";

    private Long id;
    private Long leadTimePackId;
    private String code;
    private Integer type;
    private Integer status;
    private Long fromProvinceId;
    private String fromProvinceCode;
    private Long toProvinceId;
    private String toProvinceCode;
    private Integer estimatedDeliveryDay;
    private Integer transportType;
    private Double limitWeight;
    private Integer limitType;
    private Double maxWeight;
    private Long fromDistrictId;
    private Long toDistrictId;
    private String fromDistrictCode;
    private String toDistrictCode;
    private Timestamp uTimestamp;

    private String fromProvinceName;
    private String toProvinceName;
    private String fromDistrictName;
    private String toDistrictName;

    public LeadtimeRouteDB() {

    }

    public LeadtimeRouteDB(LeadtimeRouteId leadtimeRouteId, AddressId fromProvince, AddressId toProvince, AddressId fromDistrict, AddressId toDistrict, Weight weight,
                           LeadtimeId leadtimeId, Integer type, Integer status, Integer estimatedDeliveryDay, Vehicle vehicle, AuditLog auditLog) {
        this.id = leadtimeRouteId != null ? leadtimeRouteId.getId() : null;
        this.fromProvinceId = fromProvince != null ? fromProvince.getId() : null;
        this.fromProvinceCode = fromProvince != null ? fromProvince.getCode() : null;
        this.toProvinceId = toProvince != null ? toProvince.getId() : null;
        this.toProvinceCode = toProvince != null ? toProvince.getCode() : null;
        this.fromDistrictId = fromDistrict != null ? fromDistrict.getId() : null;
        this.fromDistrictCode = fromDistrict != null ? fromDistrict.getCode() : null;
        this.toDistrictId = toDistrict != null ? toDistrict.getId() : null;
        this.toDistrictCode = toDistrict != null ? toDistrict.getCode() : null;
        this.limitWeight = weight != null ? weight.getLimitWeight() : null;
        this.maxWeight = weight != null ? weight.getMaxWeight() : null;
        this.limitType = weight != null ? weight.getLimitType() : null;
        this.estimatedDeliveryDay = estimatedDeliveryDay;
        this.transportType = vehicle != null ? vehicle.getType() : null;
        this.leadTimePackId = leadtimeId != null ? leadtimeId.getId() : null;
        this.type = type;
        this.uTimestamp = auditLog != null ? auditLog.getUTimestamp() : null;
    }

}
