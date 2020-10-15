package vn.vmg.ptdv.hola.cms.rest.wallet;

import lombok.Data;
import vn.vmg.ptdv.hola.wallet.presentation.WalletListResponse;

import java.util.List;

@Data
public class WalletListJSONResponse {
    private Long pageIndex;
    private Long pageSize;
    private Integer totalRecord;
    private List<WalletListResponse> list;
}
