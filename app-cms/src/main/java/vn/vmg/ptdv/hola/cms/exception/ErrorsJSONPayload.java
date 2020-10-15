package vn.vmg.ptdv.hola.cms.exception;

import vn.vmg.ptdv.hola.account.validator.AccountError;

import java.util.ArrayList;
import java.util.List;

public class ErrorsJSONPayload {
    public List<ErrorJSON> errors;

    public ErrorsJSONPayload(List<AccountError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }

    private ErrorJSON fromApplicationError(AccountError error) {
        return new ErrorJSON(error.getCode(),
                error.getField(),
                error.getDescription());
    }
}
