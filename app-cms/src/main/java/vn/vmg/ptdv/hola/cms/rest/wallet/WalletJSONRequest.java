package vn.vmg.ptdv.hola.cms.rest.wallet;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
public class WalletJSONRequest {
    private String id;
    private String codeId;
    private String fullName;
    private String phoneNumber;
    private String account;
    private Double base;
    private Double hold;
    private Double maxBorrow;
    private Double available;
    private String commonSearch;
    private Long pageIndex;
    private Long pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public WalletJSONRequest(@RequestParam(required = false) String codeId, String fullName, String phoneNumber,
            String account, Double base, Double hold, Double maxBorrow,
            Double available, String commonSearch, Long pageIndex, Long pageSize, Boolean asc,
            List<String> fieldSort) {
        this.id = codeId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.base = base;
        this.hold = hold;
        this.maxBorrow = maxBorrow;
        this.available = available;
        this.commonSearch = commonSearch;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }
}
