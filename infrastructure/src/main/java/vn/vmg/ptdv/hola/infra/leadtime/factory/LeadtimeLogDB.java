package vn.vmg.ptdv.hola.infra.leadtime.factory;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeadtimeLogDB {

    public static final String ID = "ID";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String DATA_UPDATED = "DATA_UPDATED";
    public static final String LEADTIME_PACK_ID = "LEADTIME_PACK_ID";

    private Long id;
    private LocalDate createdAt;
    private Long createdBy;
    private String dataUpdated;
    private Long leadtimeId;

}
