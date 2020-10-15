package vn.vmg.ptdv.hola.cms.rest.leadtimeRoute;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class LeadtimeRouteJSONRequest {

    private Long id;
    private Long leadtimeId;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant uTimestamp;

}
