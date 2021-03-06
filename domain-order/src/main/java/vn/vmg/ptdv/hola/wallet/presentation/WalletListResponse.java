package vn.vmg.ptdv.hola.wallet.presentation;

import lombok.Data;

@Data
public class WalletListResponse {
    private String codeId;
    private String fullName;
    private String phoneNumber;
    private String account;
    private Double base;
    private Double hold;
    private Double maxBorrow;
    private Double available;
}
