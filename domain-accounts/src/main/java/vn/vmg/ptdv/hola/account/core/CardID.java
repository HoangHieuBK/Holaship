package vn.vmg.ptdv.hola.account.core;

import lombok.Data;
import org.springframework.util.StringUtils;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.validator.AccountError;
import vn.vmg.ptdv.hola.account.validator.CommonValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CardID {
    private String numberCardID;
    private LocalDate dateCardID;
    private String placeCardID;
    private String imgBeforeCardID;
    private String imgAfterCardID;

    public CardID withIdentity(String numberCardID, LocalDate dateCardID, String placeCardID) {
        this.numberCardID = numberCardID;
        this.dateCardID = dateCardID;
        this.placeCardID = placeCardID;
        return this;
    }

    public CardID withRegistration(String imgBeforeCardID, String imgAfterCardID) {
        this.imgAfterCardID = imgAfterCardID;
        this.imgBeforeCardID = imgBeforeCardID;
        return this;
    }

    private void validate(String numberCardID, LocalDate dateCardID, String placeCardID, String imgBeforeCardID,
            String imgAfterCardID) throws AccountDomainException {
        List<AccountError> errors = new ArrayList<>();
        errors.addAll(CommonValidation.validate(numberCardID)
                .stream().map(vr -> new AccountError(vr.code, "numberCardID", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(dateCardID.toString())
                .stream().map(vr -> new AccountError(vr.code, "dateCardID", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(placeCardID)
                .stream().map(vr -> new AccountError(vr.code, "placeCardID", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgBeforeCardID)
                .stream().map(vr -> new AccountError(vr.code, "imgBeforeCardID", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgAfterCardID)
                .stream().map(vr -> new AccountError(vr.code, "imgAfterCardID", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }
    }

    public CardID build() throws AccountDomainException {
        if (StringUtils.isEmpty(numberCardID)) {
            return null;
        }
        validate(numberCardID, dateCardID, placeCardID, imgBeforeCardID, imgAfterCardID);
        return this;
    }
}
