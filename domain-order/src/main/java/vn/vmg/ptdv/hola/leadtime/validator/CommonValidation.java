package vn.vmg.ptdv.hola.leadtime.validator;


import io.micrometer.core.instrument.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class CommonValidation {
    public static List<LeadtimeValidationError> validate(String column) {
        if (StringUtils.isEmpty(column) || StringUtils.isBlank(column)) {
            return Collections.singletonList(LeadtimeValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<LeadtimeValidationError> validate(Integer column) {
        if (column == null) {
            return Collections.singletonList(LeadtimeValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<LeadtimeValidationError> validate(ZonedDateTime column) {
        if (column == null) {
            return Collections.singletonList(LeadtimeValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static boolean isGreaterThan(Object value, int maxlength) {
        if (value.getClass().equals(String.class)) {
            return (((String) value).length() > maxlength);
        } else if (value.getClass().equals(Double.class)) {
            int length = String.valueOf(value).length();
            return length > maxlength;
        }
        return false;
    }
}
