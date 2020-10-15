package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;
import vn.vmg.ptdv.hola.account.core.IMediaTech;

@Data
public class BankAccountResponse {
    private String bankCode;
    private int Type;
    private String bankName;
    private String bankAccount;
    private String bankAccountName;
    private String bankShortName;
    private String requestId;
    private IMediaTech iMediaTech;
    private Integer operation;
    private String partnerCode;
    private String requestAmount;
}
