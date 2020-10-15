package vn.vmg.ptdv.hola.account.validator;


import vn.vmg.ptdv.hola.common.des.Commons;

import java.util.Collections;
import java.util.List;

public class PasswordValidator {
    public static List<AccountValidationError> validate(String password,
            String confirmPassword) {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);

        } else if (!checkCapitalLetterPassword(password)) {
            return Collections.singletonList(
                    AccountValidationError.PASSWORD_MUST_HAVE_CAPITAL_PASSWORD);

        } else if (!checkNormalLetterPassword(password)) {
            return Collections.singletonList(
                    AccountValidationError.PASSWORD_MUST_HAVE_NORMAL_PASSWORD);

        } else if (!checkSpecialLetterPassword(password)) {
            return Collections.singletonList(
                    AccountValidationError.PASSWORD_MUST_HAVE_SPECIAL_LETTER);

        } else if (!checkNumberPassword(password)) {
            return Collections.singletonList(
                    AccountValidationError.PASSWORD_MUST_HAVE_NUMBER_PASSWORD);

        } else if (Commons.isGreaterThan(password, 15)) {
            return Collections
                    .singletonList(AccountValidationError.INVALID_MAX_LENGTH_PASSWORD);

        } else if (Commons.isSmallerThan(password, 8)) {
            return Collections
                    .singletonList(AccountValidationError.INVALID_MIN_LENGTH_PASSWORD);

        } else if (!confirmPassword.equals(password)) {
            return Collections
                    .singletonList(AccountValidationError.INVALID_CONFIRM_PASSWORD);

        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static List<AccountValidationError> validate(String password) {
        if (password.isEmpty()) {
            return Collections.singletonList(AccountValidationError.EMPTY_FIELD);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static boolean checkNormalLetterPassword(String password) {
        String regex = "^.*[a-z]+.*$";
        return password.matches(regex);
    }

    public static boolean checkCapitalLetterPassword(String password) {
        String regex = "^.*[A-Z]+.*$";
        return password.matches(regex);
    }

    public static boolean checkNumberPassword(String password) {
        String regex = "^.*[0-9]+.*$";
        return password.matches(regex);
    }

    public static boolean checkSpecialLetterPassword(String password) {
        String regex = "^.*[@#$%^&-+=()]+.*$";
        return password.matches(regex);
    }
}
