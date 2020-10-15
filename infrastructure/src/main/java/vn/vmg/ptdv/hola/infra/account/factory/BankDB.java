package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

@Data
public class BankDB {
    public static final String NAME ="NAME";
    public static final String CODE ="CODE";
    public static final String BANK_CODE="BANK_CODE";
    public static final String IMAGE_BANK="IMAGE_BANK";
    private String name;
    private String code;
    private String bankCode;
    private String imageBank;
}
