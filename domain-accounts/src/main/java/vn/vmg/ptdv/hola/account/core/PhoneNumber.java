package vn.vmg.ptdv.hola.account.core;

import lombok.Data;

@Data
public class PhoneNumber {
    private String number;
    private String areaCode;
    private String countryCode;
    private String supplier;
}
