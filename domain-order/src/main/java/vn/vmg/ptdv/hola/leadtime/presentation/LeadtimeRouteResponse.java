package vn.vmg.ptdv.hola.leadtime.presentation;

import lombok.Data;

import java.time.Instant;

@Data
public class LeadtimeRouteResponse {

    private Long id;
    private String code;
    private Long fromProvinceId;
    private String fromProvinceCode;
    private String fromProvinceName;
    private Long toProvinceId;
    private String toProvinceCode;
    private String toProvinceName;
    private Long fromDistrictId;
    private String fromDistrictCode;
    private String fromDistrictName;
    private Long toDistrictId;
    private String toDistrictCode;
    private String toDistrictName;
    private Integer type;
    private Integer status;
    private Integer estimatedDeliveryDay;
    private Integer transportType;
    private Double limitWeight;
    private Double maxWeight;
    private Integer limitType;
    private Long leadtimeId;
    private Instant uTimestamp;

}
