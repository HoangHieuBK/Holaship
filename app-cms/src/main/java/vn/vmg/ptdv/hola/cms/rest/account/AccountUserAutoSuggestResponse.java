package vn.vmg.ptdv.hola.cms.rest.account;

import lombok.Data;
import vn.vmg.ptdv.hola.account.presentation.AccountAutoSuggestResponse;

import java.util.List;
@Data
public class AccountUserAutoSuggestResponse {
    List<AccountAutoSuggestResponse> list;
}
