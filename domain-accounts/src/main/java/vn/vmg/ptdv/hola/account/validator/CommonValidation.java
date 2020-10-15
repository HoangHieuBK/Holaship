package vn.vmg.ptdv.hola.account.validator;


import io.micrometer.core.instrument.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class CommonValidation {
    public static List<AccountValidationError> validate(String column) {
        if (StringUtils.isEmpty(column) || StringUtils.isBlank(column)) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<AccountValidationError> validate(Integer column) {
        if (column == null) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
