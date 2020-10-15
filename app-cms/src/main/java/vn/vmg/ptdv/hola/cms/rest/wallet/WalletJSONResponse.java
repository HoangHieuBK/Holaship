package vn.vmg.ptdv.hola.cms.rest.wallet;

import lombok.Data;

@Data
public class WalletJSONResponse {
    private String codeId;
    private String fullName;
    private String phoneNumber;
    private String account;
    private Double base;
    private Double hold;
    private Double maxBorrow;
    private Double available;
}
