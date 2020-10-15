package vn.vmg.ptdv.hola.infra.account.factory;

import lombok.Data;

@Data
public class BankAccountDB {
    public static final String ID = "ID";
    public static final String BANK_ID = "BANK_ID";
    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";
    public static final String BANK_ACCOUNT_NAME = "BANK_ACCOUNT_NAME";
    public static final String BANK_ADDRESS = "BANK_ADDRESS";
    public static final String STATUS = "STATUS";
    public static final String BANK_BRANCH_ID = "BANK_BRANCH_ID";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String APP_USER_ID = "APP_USER_ID";
    public static final String TYPE = "TYPE";
    public static final String ROLE_TYPE = "ROLE_TYPE";
    public static final String BANK_NAME = "BANK_NAME";
    public static final String BANK_CODE = "BANK_CODE";
    public static final String IMAGE_BANK = "IMAGE_BANK";
    public static final String CODE = "CODE";

    private Long id;
    private Long bankId;
    private String bankAccount;
    private String bankAccountName;
    private String bankAddress;
    private int status;
    private Long bankBranchId;
    private String phone;
    private String email;
    private Long appUserId;
    private int type;
    private int roleType;
    private String bankName;
    private String bankCode;
    private String imageBank;
    private String code;

}
