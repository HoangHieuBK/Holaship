package vn.vmg.ptdv.hola.infra.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.UpdateProfileException;
import vn.vmg.ptdv.hola.account.factory.*;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.common.exception.SecurityDESException;
import vn.vmg.ptdv.hola.common.otp.factory.OTPVerification;
import vn.vmg.ptdv.hola.infra.account.factory.*;
import vn.vmg.ptdv.hola.infra.account.mapper.HolaProfileDBMapper;
import vn.vmg.ptdv.hola.infra.imedia.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private final IMediaRestAPI iMediaRestAPI;
    private final AccountJDBC accountJDBC;

    public AccountRepositoryImpl(IMediaRestAPI iMediaRestAPI, AccountJDBC accountJDBC) {
        this.iMediaRestAPI = iMediaRestAPI;
        this.accountJDBC = accountJDBC;
    }

    @Override
    public LoginInfo getIMediaAcc(String phoneNumber, String clearText) {
        try {
            SSOLogin ssoLogin = new SSOLogin(phoneNumber, clearText);
            IMediaResponse iMediaResponse = iMediaRestAPI.login(ssoLogin);
            return IMediaDataMapper.fromRest(iMediaResponse, new LoginInfo());
        } catch (IMediaRestException | SecurityDESException | JsonProcessingException e) {
        }
        return null;
    }

    @Override
    public HolaAccount findByAccountId(AccountId accountId, DeviceToken deviceToken) {
        Optional<HolaAccountDB> appUser;
        if (deviceToken == null) {
            appUser = accountJDBC
                    .findByPhoneWithNonDeviceToken(accountId.getPhoneNumber());
        } else {
            appUser = accountJDBC
                    .findByPhone(accountId.getPhoneNumber(), deviceToken.getUniqueToken());
        }

        if (appUser.isPresent()) {
            HolaAccount account = new HolaAccount();
            account.setEmail(appUser.get().getEmail());

            if (appUser.get().getShip() == 1) {
                account.setShipService(new UserService(HolaProvider.SHIP.name()));
            }
            if (appUser.get().getShop() == 1) {
                account.setShopService(new UserService(HolaProvider.SHOP.name()));
            }
            account.setAccountId(new AccountId(appUser.get().getId(), appUser.get().getPhone()));
            account.setDeviceToken(appUser.get().getDeviceToken());
            return account;
        }

        return null;
    }

    @Override
    public int activeAccount(AccountId accountId, Integer ship, Integer shop) {
        HolaAccountDB holaAccountDB = new HolaAccountDB();
        holaAccountDB.setId(accountId.getId());
        holaAccountDB.setShop(shop);
        holaAccountDB.setShip(ship);
        int updated = accountJDBC.updateBy(holaAccountDB);
        return updated;
    }

    @Override
    public int saveUserDevice(AccountId accountId, String uniqueToken, Integer ship, Integer shop) {
        UserDevice userDevice = new UserDevice();
        userDevice.setAppUserId(accountId.getId());
        userDevice.setDeviceToken(uniqueToken);
        userDevice.setShop(shop);
        userDevice.setShip(ship);
        userDevice.setConfirm("OTP");
        int updated = accountJDBC.save(userDevice, accountId);
        return updated;
    }

    @Override
    public RegisterInfo saveIMediaAcc(String phoneNumber, String clearText, String email) throws
            AccountDomainException {
        RegisterInfo registerInfo = new RegisterInfo();
        try {
            SSORegister ssoRegister = new SSORegister(phoneNumber, clearText, email);
            IMediaResponse iMediaResponse = iMediaRestAPI.register(ssoRegister);
            return IMediaDataMapper.fromRest(iMediaResponse, registerInfo);
        } catch (IMediaRestException e) {
            registerInfo.setErrorCode(e.getCode());
            registerInfo.setErrorMsg(e.getMessage());
        } catch (JsonProcessingException | SecurityDESException e1) {
            throw new AccountDomainException(e1.getCause());
        }
        return registerInfo;
    }

    @Override
    public boolean resetPasswordIMedia(String phone, String clearTextPassword, String otpCode) throws
            AccountDomainException {
        SSOForgotPassword ssoForgotPassword = new SSOForgotPassword();
        ssoForgotPassword.setUserName(phone);
        ssoForgotPassword.setNewPassword(clearTextPassword);
        ssoForgotPassword.setOtpCode(otpCode);

        try {
            iMediaRestAPI.forgotPassword(ssoForgotPassword);
            return true;

        } catch (JsonProcessingException | SecurityDESException e) {
            throw new AccountDomainException(e.getCause());
        } catch (IMediaRestException e) {
            throw new AccountDomainException(e.getCode(), e.getMessage());
        }
    }

    @Override
    public HolaProfile findProfileById(AccountId accountId) {
        Optional<HolaProfileDB> appUser = accountJDBC.findBy(accountId.getPhoneNumber());
        if (appUser.isPresent()) {
            return HolaProfileDBMapper.getInstance().fromDB(appUser.get());
        }
        return null;
    }

    @Override
    public int updateBasicInfo(HolaProfile profile) throws UpdateProfileException {

        BasicProfileDB basicProfileDB = HolaProfileDBMapper.getInstance().fromBasic(profile);
        // update imedia
//        IMediaResponse iMediaResponse = new IMediaResponse();
//        iMediaResponse.setBirthday(basicProfileDB.getBirthday());
//        iMediaResponse.setUsername(basicProfileDB.getName());
//        try {
//            iMediaRestAPI.updateUserInfor(iMediaResponse);
//        } catch (IMediaRestException e) {
//
//        } catch (JsonProcessingException | SecurityDESException e1) {
//            throw new UpdateProfileException();
//        }

        int updated = accountJDBC.updateBasic(basicProfileDB);
        return updated;
    }

    @Override
    public ShipperProfile findShipperBy(AccountId accountId) throws AccountDomainException {
        Optional<ShipperInfoDB> shipperInfoDB = accountJDBC.findShipperByPhone(accountId.getPhoneNumber());
        return shipperInfoDB.isPresent() ? HolaProfileDBMapper.getInstance().fromDB(shipperInfoDB.get()) : null;
    }

    @Override
    public int updateCardID(ShipperProfile profile) {
        ShipperInfoDB db = HolaProfileDBMapper.getInstance().fromCardID(profile);
        int updated = accountJDBC.updateCardID(db, profile.getAuditLog());
        return updated;
    }

    @Override
    public ShipperProfile findShipperBy(AccountId accountId, AuditLog auditLog) {
        Optional<ShipperInfoDB> shipperInfoDB = accountJDBC
                .findShipperByPhone(accountId.getPhoneNumber(), auditLog.getUTimestamp());
        return shipperInfoDB.isPresent() ?
                HolaProfileDBMapper.getInstance().fromDBTimestamp(shipperInfoDB.get(), accountId.getPhoneNumber()) :
                null;
    }

    @Override
    public int updateVehicle(ShipperProfile shipperProfile) {
        ShipperInfoDB db = HolaProfileDBMapper.getInstance().fromVehicle(shipperProfile);
        int updated = accountJDBC.updateVehicle(db, shipperProfile.getAuditLog());
        return updated;
    }

    @Override
    public int updateDriverLicense(ShipperProfile shipperProfile) {
        ShipperInfoDB db = HolaProfileDBMapper.getInstance().fromDriverLicense(shipperProfile);
        int updated = accountJDBC.updateDriverLicense(db, shipperProfile.getAuditLog());
        return updated;
    }

    @Override
    public boolean verifyIMedia(String phone, String otpCode) throws AccountDomainException {
        OTPVerification oTPVerification = new OTPVerification();
        oTPVerification.setPhone(phone);
        oTPVerification.setOtp(otpCode);
        try {
            iMediaRestAPI.verifyOTP(oTPVerification);
            return true;
        } catch (JsonProcessingException | SecurityDESException e) {
            throw new AccountDomainException(e.getCause());
        } catch (IMediaRestException e) {
            throw new AccountDomainException(e.getCode(), e.getMessage());
        }
    }

    @Override
    public int activeShip(AccountId accountId, AuditLog auditLog) {

        return accountJDBC.activeShip(accountId.getPhoneNumber(), auditLog);
    }

    @Override
    public int createHolaAcc(AccountId accountId, RegisterInfo iMediaAcc, Integer ship, Integer shop) {
        return createShipper(accountId, ship, shop, iMediaAcc.getEmail());
    }

    @Override
    public int createHolaAcc(AccountId accountId, LoginInfo iMediaAcc, Integer ship, Integer shop) {

        return createShipper(accountId, ship, shop, iMediaAcc.getEmail());
    }

    private int createShipper(AccountId accountId, Integer ship, Integer shop, String email) {
        HolaAccountDB holaAccountDB = new HolaAccountDB();
        holaAccountDB.setPhone(accountId.getPhoneNumber());
        holaAccountDB.setEmail(email);
        holaAccountDB.setShip(ship);
        holaAccountDB.setShop(shop);

        int created = accountJDBC.save(holaAccountDB, accountId);
        if (created > 0 && ship == 0) {
            ShipperProfileDB shipperProfileDB = new ShipperProfileDB();
            shipperProfileDB.setAppUserId(accountId.getId());
            created = accountJDBC.saveShipperProfile(shipperProfileDB, accountId);
        }

        return created;
    }

    @Override
    public List<BankAccount> getBankAccount(AccountId accountId, Integer ship, Integer shop) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        int roleType = 0;
        if (shop == null) {
            roleType = ship;
        }
        if (ship == null) {
            roleType = shop;
        }
        List<BankAccountDB> response = accountJDBC.findBankAccount(accountId, roleType);
        if (response != null) {
            for (BankAccountDB bankAccountDB : response) {
                BankAccount bankAccount = new BankAccount(bankAccountDB.getImageBank(), bankAccountDB.getBankCode(),
                        bankAccountDB.getBankAccount(), bankAccountDB.getType(), bankAccountDB.getBankAccountName(),
                        bankAccountDB.getBankName(), bankAccountDB.getCode());
                bankAccounts.add(bankAccount);
            }
        }
        return bankAccounts;
    }
}
