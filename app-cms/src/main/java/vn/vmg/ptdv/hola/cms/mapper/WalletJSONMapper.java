package vn.vmg.ptdv.hola.cms.mapper;

public class WalletJSONMapper {
    private static WalletJSONMapper INSTANCE;

    private WalletJSONMapper() {
    }

    public static WalletJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WalletJSONMapper();
        }
        return INSTANCE;
    }

//    public WalletListRequest fromListJsonRequest(WalletJSONRequest walletJSONRequest) {
//        WalletListRequest walletListRequest = new WalletListRequest();
//       r
//    }


}


