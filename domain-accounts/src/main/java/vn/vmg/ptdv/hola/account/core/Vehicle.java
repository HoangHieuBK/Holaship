package vn.vmg.ptdv.hola.account.core;

import lombok.Data;
import org.springframework.util.StringUtils;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.validator.AccountError;
import vn.vmg.ptdv.hola.account.validator.CommonValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Vehicle {
    private Integer vehicleType;
    private String licensePlace;
    private String imgBeforeDriverRegister;
    private String imgAfterDriverRegister;
    private String imgBeforeInsurance;
    private String imgAfterInsurance;

    public Vehicle withIdentity(Integer vehicleType, String licensePlace) {
        this.vehicleType = vehicleType;
        this.licensePlace = licensePlace;
        return this;
    }

    public Vehicle withRegistration(String imgBeforeDriverRegister, String imgAfterDriverRegister) {
        this.imgBeforeDriverRegister = imgBeforeDriverRegister;
        this.imgAfterDriverRegister = imgAfterDriverRegister;
        return this;
    }

    public Vehicle withInsurance(String imgBeforeInsurance, String imgAfterInsurance) {
        this.imgAfterInsurance = imgAfterInsurance;
        this.imgBeforeInsurance = imgBeforeInsurance;
        return this;
    }

    private void validate(Integer vehicleType, String licensePlace,
            String imgBeforeDriverRegister, String imgAfterDriverRegister,
            String imgBeforeInsurance, String imgAfterInsurance) throws
            AccountDomainException {
        List<AccountError> errors = new ArrayList<>();
        errors.addAll(CommonValidation.validate(vehicleType)
                .stream().map(vr -> new AccountError(vr.code, "vehicleType", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(licensePlace)
                .stream().map(vr -> new AccountError(vr.code, "licensePlace", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgBeforeDriverRegister)
                .stream().map(vr -> new AccountError(vr.code, "imgBeforeDriverRegister", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgAfterDriverRegister)
                .stream().map(vr -> new AccountError(vr.code, "imgAfterDriverRegister", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgBeforeInsurance)
                .stream().map(vr -> new AccountError(vr.code, "imgBeforeInsurance", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgAfterInsurance)
                .stream().map(vr -> new AccountError(vr.code, "imgAfterInsurance", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }
    }

    public Vehicle build() throws AccountDomainException {
        if (StringUtils.isEmpty(licensePlace)) {
            return null;
        }
        validate(vehicleType, licensePlace, imgBeforeDriverRegister, imgAfterDriverRegister,
                imgBeforeInsurance, imgAfterInsurance);
        return this;
    }
}
