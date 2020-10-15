package vn.vmg.ptdv.hola.account.validator;

import vn.vmg.ptdv.hola.common.des.Commons;

import java.util.Collections;
import java.util.List;

public class EmailValidator {
    public static List<AccountValidationError> validate(String email) {
        if (email.isEmpty()) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);

        } else if (Commons.isGreaterThan(email, 100)) {
            return Collections.singletonList(AccountValidationError.INVALID_MAX_LENGTH_EMAIL);

        } else if (!isValidEmail(email)) {
            return Collections.singletonList(AccountValidationError.INVALID_EMAIL);

        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
