package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.AccountSearch;
import vn.vmg.ptdv.hola.account.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.account.validator.AccountError;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountSearchRequest {

    private AccountSearch accountSearch;
    private PagingSortFilter pagingSortFilter;

    public AccountSearchRequest() {
    }

    public AccountSearchRequest(AccountSearch accountSearch,
            PagingSortFilter pagingSortFilter) throws AccountDomainException {
        this.accountSearch = accountSearch;
        this.pagingSortFilter = pagingSortFilter;
        validate();
    }

    private void validate() throws AccountDomainException {
        List<AccountError> errors = new ArrayList<>();
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }

    }
}
