package vn.vmg.ptdv.hola.account.validator;


import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static List<AccountValidationError> validate(String phone) {
        String rex = "0[35789]{1}\\d{8}$";

        if (StringUtils.isEmpty(phone)) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);
        } else {
            Pattern pattern = Pattern.compile(rex);
            Matcher matcher = pattern.matcher(phone);

            if (matcher.find()) {
                return Collections.EMPTY_LIST;
            } else {
                return Collections.singletonList(AccountValidationError.INVALID_PHONE);
            }

        }
    }
}
