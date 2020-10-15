package vn.vmg.ptdv.hola.api.exception;

import vn.vmg.ptdv.hola.account.validator.AccountError;

import java.util.ArrayList;
import java.util.List;

public class ErrorsJSONPayload {
    public List<ErrorJSON> errors;

    public ErrorsJSONPayload(List<AccountError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }


    private ErrorJSON fromApplicationError(AccountError applicationError) {
        return new ErrorJSON(applicationError.getCode(),
                applicationError.getField(),
                applicationError.getDescription());
    }
}
