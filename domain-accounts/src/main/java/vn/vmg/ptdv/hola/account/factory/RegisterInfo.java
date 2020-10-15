package vn.vmg.ptdv.hola.account.factory;

import lombok.Data;

@Data
public class RegisterInfo {
    private String phone;
    private String email;
    private Long accountEpurseId;
    private String fullName;
    private Integer status;

    private int errorCode = -1;
    private String errorMsg;
}
