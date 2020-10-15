package vn.vmg.ptdv.hola.account.service.usecase;

import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.exception.UpdateProfileException;
import vn.vmg.ptdv.hola.account.presentation.BankAccountListResponse;
import vn.vmg.ptdv.hola.account.presentation.ProfileResponse;
import vn.vmg.ptdv.hola.account.presentation.ShipperResponse;
import vn.vmg.ptdv.hola.account.presentation.VerifyResponse;
import vn.vmg.ptdv.hola.account.service.impl.PartInfo;

import java.time.LocalDate;

public interface ProfileUseCase {
    ProfileUseCase findProfileById(AccountId accountId, DeviceToken deviceToken);

    ProfileUseCase profileNotFound() throws AccountNotFoundException;

    ProfileUseCase applyIdentity(AccountId accountId, AuditLog auditLog);

    ProfileUseCase applyBasicInfo(Avatar avatar, String displayName, LocalDate birthday, Gender gender);

    ProfileUseCase fail() throws UpdateProfileException;

    ProfileUseCase update() throws AccountDomainException;

    ProfileUseCase path(PartInfo pathInfo);

    ProfileUseCase getShipperProfile(AccountId accountId) throws AccountDomainException;

    ProfileResponse end();

    ShipperResponse endShipper();

    ProfileUseCase getShipperProfile(AccountId accountId, AuditLog auditLog);

    ProfileUseCase applyCardID(CardID cardID);

    ProfileUseCase applyVehicle(Vehicle cehicle);

    ProfileUseCase applyDriverLicense(DriverLicense driverLicense);

    ProfileUseCase activeAccount(AccountId accountId, String otp, AuditLog auditLog, DeviceToken deviceToken,
            HolaProvider serviceName) throws AccountDomainException;

    VerifyResponse endIdentity();

    ProfileUseCase getHolaAccount(AccountId username, DeviceToken deviceToken);

    ProfileUseCase getHolaAccountFirstLogin(AccountId accountId);

    ProfileUseCase getBankAccount(AccountId accountId, HolaProvider serviceName);

    BankAccountListResponse endGetBankAccount();

}
