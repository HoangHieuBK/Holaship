package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;
import vn.vmg.ptdv.hola.account.presentation.AccountSearchResponse;

import java.util.List;

@Data
public class AccountUserJSONResponse {
    private List<AccountSearchResponse> list;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalRecord;
}
