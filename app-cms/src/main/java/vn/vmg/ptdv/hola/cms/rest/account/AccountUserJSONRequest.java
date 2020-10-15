package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;

import java.util.List;

@Data
public class AccountUserJSONRequest {

    private String id;
    private String name;
    private Integer status;
    private String phone;
    private String serviceName;
    private String commonSearch;
    private Integer pageIndex;
    private Integer pageSize;
    private Boolean asc;
    private List<String> fieldSort;

    public AccountUserJSONRequest(@RequestParam(required = false) String id, String name, Integer status, String phone,
            String serviceName,
            String commonSearch, Integer pageIndex, Integer pageSize, Boolean asc,
            List<String> fieldSort) throws AccountDomainException {
        this.id = id;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.serviceName = serviceName;
        this.commonSearch = commonSearch;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.asc = asc;
        this.fieldSort = fieldSort;
    }
}
