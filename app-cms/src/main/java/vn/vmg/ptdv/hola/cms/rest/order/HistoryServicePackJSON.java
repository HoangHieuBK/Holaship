package vn.vmg.ptdv.hola.cms.rest.order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryServicePackJSON {
    private Long id;
    private String name;
    private String code;
    private Long updatedBy;
    private String nameUpdatedBy;
    private LocalDate updatedAt;
    private Long servicePackSettingId;
    private LocalDate effectiveAt;
    private Integer active;

}
