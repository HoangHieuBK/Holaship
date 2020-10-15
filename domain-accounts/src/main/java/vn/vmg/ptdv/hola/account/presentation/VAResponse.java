package vn.vmg.ptdv.hola.account.presentation;

import lombok.Data;

@Data
public class VAResponse {
    private Long userId;
    private String userName;
    private String shipCode;
    private String shopCode;
}
