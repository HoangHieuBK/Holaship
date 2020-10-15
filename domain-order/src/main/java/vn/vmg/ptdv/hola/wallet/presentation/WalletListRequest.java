package vn.vmg.ptdv.hola.wallet.presentation;

import lombok.Data;

@Data
public class WalletListRequest {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String account;
    private Double currentBalance;
    private Double custodyBalance;
    private Double availableBalances;
    private Double advanceLimit;

}
