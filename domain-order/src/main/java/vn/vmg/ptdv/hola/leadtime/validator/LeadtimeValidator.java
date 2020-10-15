package vn.vmg.ptdv.hola.leadtime.validator;

import vn.vmg.ptdv.hola.common.des.Commons;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.util.Collections;
import java.util.List;

public class LeadtimeValidator {
    //    public static List<AccountValidationError> validate(String email) {
//        if (email.isEmpty()) {
//            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);
//
//        } else if (Commons.isGreaterThan(email, 100)) {
//            return Collections.singletonList(AccountValidationError.INVALID_MAX_LENGTH_EMAIL);
//
//        } else if (!isValidEmail(email)) {
//            return Collections.singletonList(AccountValidationError.INVALID_EMAIL);
//
//        } else {
//            return Collections.EMPTY_LIST;
//        }
//    }
//
//    public static boolean isValidEmail(String email) {
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        return email.matches(regex);
//    }
    public static List<LeadtimeValidationError> validateName(String name) {
        if (name.isEmpty()) {
            return Collections.singletonList(LeadtimeValidationError.EMPTY_FIELD);

        } else if (Commons.isGreaterThan(name, 50)) {
            return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_NAME);

        } else {
            return Collections.EMPTY_LIST;
        }
    }
    public static List<LeadtimeValidationError> validateDatetime(AuditLog auditLog){
        if (auditLog != null) {

        }
//        if (!input.isEmpty()){
//             try {
//                 Date date= Commons.convertStringToDate(input);
//             } catch (Exception e) {
//                 return Collections.singletonList(LeadtimeValidationError.INVALID_DATETIME);
//             }
//        }
        return Collections.EMPTY_LIST;
    }
    
    public static List<LeadtimeValidationError> validateTransportType(String transportType) {
        if (transportType.isEmpty()) {
            return Collections.singletonList(LeadtimeValidationError.EMPTY_FIELD);

        } else if (Commons.isGreaterThan(transportType, 5)) {
            return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_NAME);

        } else {
            return Collections.EMPTY_LIST;
        }
    }

//    public static List<LeadtimeValidationError> validateMaxLength(Object object, int maxLength) {
//        if (Commons.isGreaterThan(object, maxLength)) {
//            if (maxLength == 5) {
//                return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_5);
//            } else if (maxLength == 15) {
//                return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_15);
//            } else if (maxLength == 50) {
//                return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_50);
//            }
//            return Collections.singletonList(LeadtimeValidationError.INVALID_MAX_LENGTH_200);
//        } else {
//            return Collections.EMPTY_LIST;
//        }
//    }

}
