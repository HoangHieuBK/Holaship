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
public class DriverLicense {
    private String driverLicense;
    private Integer speciesLicenseType;
    private String imgAfterDriverLicense;
    private String imgBeforeDriverLicense;

    public DriverLicense withIdentity(String driverLicense, Integer speciesLicenseType) {
        this.speciesLicenseType = speciesLicenseType;
        this.driverLicense = driverLicense;
        return this;
    }

    public DriverLicense withRegistration(String imgAfterDriverLicense, String imgBeforeDriverLicense) {
        this.imgBeforeDriverLicense = imgBeforeDriverLicense;
        this.imgAfterDriverLicense = imgAfterDriverLicense;
        return this;
    }

    private void validate(String driverLicense,
            Integer speciesLicenseType, String imgAfterDriverLicense,
            String imgBeforeDriverLicense) throws
            AccountDomainException {
        List<AccountError> errors = new ArrayList<>();
        errors.addAll(CommonValidation.validate(driverLicense)
                .stream().map(vr -> new AccountError(vr.code, "driverLicense", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(speciesLicenseType)
                .stream().map(vr -> new AccountError(vr.code, "speciesLicenseType", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgAfterDriverLicense)
                .stream().map(vr -> new AccountError(vr.code, "imgAfterDriverLicense", vr.message))
                .collect(Collectors.toList()));
        errors.addAll(CommonValidation.validate(imgBeforeDriverLicense)
                .stream().map(vr -> new AccountError(vr.code, "imgBeforeDriverLicense", vr.message))
                .collect(Collectors.toList()));
        if (!errors.isEmpty()) {
            throw new AccountDomainException(errors);
        }
    }

    public DriverLicense build() throws AccountDomainException {
        if (StringUtils.isEmpty(driverLicense)) {
            return null;
        }
        validate(driverLicense, speciesLicenseType, imgAfterDriverLicense, imgBeforeDriverLicense);
        return this;
    }
}
