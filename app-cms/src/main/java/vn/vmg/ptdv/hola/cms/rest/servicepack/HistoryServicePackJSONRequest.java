package vn.vmg.ptdv.hola.cms.rest.servicepack;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Data
public class HistoryServicePackJSONRequest {

    private Long id;
    private LocalDate effectiveAt;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public HistoryServicePackJSONRequest(
            @RequestParam Long id,
            @RequestParam LocalDate effectiveAt,
            @RequestParam Integer pageIndex,
            @RequestParam Integer pageSize,
            @RequestParam Boolean asc,
            @RequestParam List<String> fieldSort) {
        this.id = id;
        this.effectiveAt = effectiveAt;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }
}
