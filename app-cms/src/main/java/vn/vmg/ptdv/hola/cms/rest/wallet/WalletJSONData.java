package vn.vmg.ptdv.hola.cms.rest.wallet;

import vn.vmg.ptdv.hola.wallet.presentation.WalletListResponse;

import java.util.ArrayList;
import java.util.List;

public class WalletJSONData {
    private static WalletJSONData INSTANCE;

    private WalletJSONData() {
    }

    public static WalletJSONData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WalletJSONData();
        }
        return INSTANCE;
    }
    public List<WalletListResponse> listWalletResponse() {

        List<WalletListResponse> listWallet = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            WalletListResponse walletListResponse = new WalletListResponse();
            walletListResponse.setCodeId("ID000"+i);
            walletListResponse.setFullName("Tran Van A");
            walletListResponse.setPhoneNumber("0989123456");
            walletListResponse.setAccount("Shiper");
            walletListResponse.setBase(1.00002);
            walletListResponse.setHold(8.29856022);
            walletListResponse.setMaxBorrow(12.36998);
            walletListResponse.setAvailable(598.25655);
            listWallet.add(walletListResponse);
        }
        return listWallet;
    }


}
