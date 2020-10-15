package vn.vmg.ptdv.hola.account.repository;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.account.core.DeviceToken;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.*;

import java.util.List;

public interface AccountRepository {
    LoginInfo getIMediaAcc(String phoneNumber, String clearText);

    HolaAccount findByAccountId(AccountId accountId, DeviceToken deviceToken);

    int createHolaAcc(AccountId accountId, RegisterInfo iMediaAcc, Integer ship, Integer shop);

    int createHolaAcc(AccountId accountId, LoginInfo iMediaAcc, Integer ship, Integer shop);

    int activeAccount(AccountId accountId, Integer ship, Integer shop);

    int saveUserDevice(AccountId accountId, String uniqueToken, Integer ship, Integer shop);

    RegisterInfo saveIMediaAcc(String phoneNumber, String clearText, String email) throws AccountDomainException;

    boolean resetPasswordIMedia(String phone, String password, String otpCode) throws AccountDomainException;

    HolaProfile findProfileById(AccountId accountId);

    int updateBasicInfo(HolaProfile profile) throws AccountDomainException;

    ShipperProfile findShipperBy(AccountId accountId) throws AccountDomainException;

    int updateCardID(ShipperProfile profile);

    ShipperProfile findShipperBy(AccountId accountId, AuditLog auditLog);

    int updateVehicle(ShipperProfile shipperProfile);

    int updateDriverLicense(ShipperProfile shipperProfile);

    boolean verifyIMedia(String phone, String otpCode) throws AccountDomainException;

    int activeShip(AccountId accountId, AuditLog auditLog);

    List<BankAccount> getBankAccount(AccountId accountId, Integer ship, Integer shop);
}
