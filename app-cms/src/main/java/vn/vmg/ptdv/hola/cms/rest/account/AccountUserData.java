package vn.vmg.ptdv.hola.cms.rest.account;

import vn.vmg.ptdv.hola.account.presentation.AccountAutoSuggestResponse;
import vn.vmg.ptdv.hola.account.presentation.AccountSearchResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountUserData {

    private static AccountUserData INSTANCE;

    private AccountUserData() {
    }

    public static AccountUserData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountUserData();
        }
        return INSTANCE;
    }

    public List<AccountSearchResponse> listAccountSearch() {

        List<AccountSearchResponse> listAccount = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            AccountSearchResponse account = new AccountSearchResponse();
            account.setId("ID00"+i);
            account.setServiceName(getShiporShop(i));
            account.setPhone("0989123456");
            account.setDisplayName("Lam dep trai");
            account.setEmail("lamdeptrai@gmail.com");
            account.setGender("MALE");
            account.setStatus(1);
            account.setCreateAt(Instant.now());
            listAccount.add(account);
        }
        return listAccount;
    }

    public String getShiporShop(int i) {
        String account = "SHIPPER";
        if (i % 2 == 0) {
            account = "SHOP";
        }
        return account;
    }


    public ProfileUserJSONResponse mapDataUserProfile(String serviceName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String ulodate = "16/08/1994";
        LocalDate localDate = LocalDate.parse(ulodate, formatter);

        ProfileUserJSONResponse profile = new ProfileUserJSONResponse();
        profile.setId("ID001");
        profile.setDisplayName("Lam");
        profile.setServiceName(serviceName);
        profile.setPhone("0989123456");
        profile.setAvatar("https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setBirthDay(localDate);
        profile.setGender("MALE");
        profile.setEmail("Lamdeptrai@Gmail.com");
        profile.setAddress("36 Hoàng Cầu, Đống Đa, Hà Nội");
        profile.setNumberCardID("123456789");
        profile.setDateCardID(localDate);
        profile.setPlaceCardID("CD Thai Binh");
        profile.setImgBeforeCardID(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setImgAfterCardID(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setVehicleType(1);
        profile.setLicensePlace("29M1-999.99");
        profile.setImgBeforeDriverRegister(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setImgAfterDriverRegister(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setImgBeforeInsurance(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setImgAfterInsurance(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setDriverLicense("B1");
        profile.setSpeciesLicenseType(0);
        profile.setImgBeforeDriverLicense(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setImgAfterDriverLicense(
                "https://images1.fanpop.com/images/image_uploads/Naruto-uzumaki-naruto-964981_320_360.jpg");
        profile.setBank("TechComBank");
        profile.setBankAccount("69696969696");
        profile.setBankAccountName("Lam Dep Trai");
        profile.setUTimestamp(Instant.now());
        return profile;
    }

    public AccountUserAutoSuggestResponse mapDataAccountUserAutoSuggestResponse (){
        AccountUserAutoSuggestResponse account = new AccountUserAutoSuggestResponse();

        List<AccountAutoSuggestResponse> list = new ArrayList<>();

        for (int i = 1; i<=20; i++){
            AccountAutoSuggestResponse autoSuggest = new AccountAutoSuggestResponse();
            autoSuggest.setId("ID00"+i);
            autoSuggest.setPhone("0987654321");
            autoSuggest.setDisplayName("Lam"+i);
            list.add(autoSuggest);
        }
        account.setList(list);
        return account;
    }


}
