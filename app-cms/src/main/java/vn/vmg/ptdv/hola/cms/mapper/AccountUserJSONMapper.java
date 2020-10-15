package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.UserService;
import vn.vmg.ptdv.hola.account.factory.AccountSearch;
import vn.vmg.ptdv.hola.account.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.account.presentation.AccountSearchRequest;
import vn.vmg.ptdv.hola.cms.rest.account.AccountUserJSONRequest;

public class AccountUserJSONMapper {
    private static AccountUserJSONMapper INSTANCE;

    public AccountUserJSONMapper() {
    }

    public static AccountUserJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountUserJSONMapper();
        }
        return INSTANCE;
    }

    public AccountSearchRequest mapAccountSearchRequest(AccountUserJSONRequest request) {
        AccountSearchRequest accountSearchRequest = new AccountSearchRequest();
        if (request != null) {
            AccountSearch accountSearch = new AccountSearch();
            accountSearch.setAccountId(new AccountId(request.getPhone()));
            accountSearch.setDisplayName(request.getName());
            accountSearch.setStatus(request.getStatus());

            if(!request.getServiceName().isEmpty()){
                accountSearch.setUserService(new UserService(request.getServiceName()));
            }
            PagingSortFilter pagingSortFilter = new PagingSortFilter(request.getPageIndex(), request.getPageSize(),
                    request.getAsc(), request.getFieldSort());
            accountSearchRequest.setPagingSortFilter(pagingSortFilter);
            accountSearchRequest.setAccountSearch(accountSearch);
        }
        return accountSearchRequest;
    }
}
