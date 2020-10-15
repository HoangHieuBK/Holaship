package vn.vmg.ptdv.hola.account.service.impl;

import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.exception.AccountNotFoundException;
import vn.vmg.ptdv.hola.account.exception.UpdateProfileException;
import vn.vmg.ptdv.hola.account.factory.BankAccount;
import vn.vmg.ptdv.hola.account.factory.HolaAccount;
import vn.vmg.ptdv.hola.account.factory.HolaProfile;
import vn.vmg.ptdv.hola.account.factory.ShipperProfile;
import vn.vmg.ptdv.hola.account.presentation.BankAccountListResponse;
import vn.vmg.ptdv.hola.account.presentation.ProfileResponse;
import vn.vmg.ptdv.hola.account.presentation.ShipperResponse;
import vn.vmg.ptdv.hola.account.presentation.VerifyResponse;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.service.usecase.ProfileUseCase;

import java.time.LocalDate;
import java.util.List;

public class ProfileUseCaseImpl implements ProfileUseCase {
    private final AccountRepository accountRepository;
    private HolaProfile profile;
    private PartInfo part;
    private int updated;
    private ShipperProfile shipperProfile;
    private Boolean isFirstLogin = false;
    private HolaAccount holaAccount;
    private HolaProfile holaProfile;
    private List<BankAccount> bankAccountList;

    public ProfileUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        part = PartInfo.BASIC;
    }

    @Override
    public ProfileUseCase findProfileById(AccountId accountId, DeviceToken deviceToken) {
        profile = accountRepository.findProfileById(accountId);
        HolaProvider shipActive = profile.getHolaService().getShipService();
        if (shipActive == HolaProvider.SHIP) {
            profile.setDeviceToken(new DeviceToken(false));
        } else {
            profile.setDeviceToken(new DeviceToken(true));
        }
//        profile.getDeviceToken().isActive(deviceToken);
        part = PartInfo.BASIC;
        return this;
    }

    @Override
    public ProfileUseCase profileNotFound() throws AccountNotFoundException {
        if (profile == null && part == PartInfo.BASIC) {
            throw new AccountNotFoundException(404, "Tài khoản không được tìm thấy!");
        }
        if (shipperProfile == null && part != PartInfo.BASIC && part != PartInfo.BANK) {
            throw new AccountNotFoundException(404, "Tài khoản không được tìm thấy!");
        }
        return this;
    }


    @Override
    public ProfileUseCase applyIdentity(AccountId accountId, AuditLog auditLog) {
        if (part == PartInfo.BASIC) {
            if (profile == null) profile = new HolaProfile();
            profile.setAccountId(accountId);
            profile.setAuditLog(auditLog);
        }
        if (part == PartInfo.CMND || part == PartInfo.LICENSE || part == PartInfo.VEHICLE) {
            shipperProfile.setAccountId(accountId);
            shipperProfile.setAuditLog(auditLog);
        }

        return this;
    }

    @Override
    public ProfileUseCase applyBasicInfo(Avatar avatar, String displayName, LocalDate birthday, Gender gender) {
        profile.setAvatar(avatar);
        profile.setDisplayName(displayName);
        profile.setBirthDay(birthday);
        profile.setGender(gender);
        part = PartInfo.BASIC;
        return this;
    }

    @Override
    public ProfileUseCase fail() throws UpdateProfileException {
        if (updated != 1) {
            throw new UpdateProfileException(400, "Cannot update profile");
        }
        return this;
    }

    @Override
    public ProfileUseCase update() throws AccountDomainException {
        if (part == PartInfo.BASIC) {
            updated = accountRepository.updateBasicInfo(profile);
        } else if (part == PartInfo.CMND) {
            updated = accountRepository.updateCardID(shipperProfile);
        } else if (part == PartInfo.VEHICLE) {
            updated = accountRepository.updateVehicle(shipperProfile);
        } else if (part == PartInfo.LICENSE) {
            updated = accountRepository.updateDriverLicense(shipperProfile);
        }
        return this;
    }

    @Override
    public ProfileUseCase path(PartInfo partInfo) {
        this.part = partInfo;
        return this;
    }

    @Override
    public ProfileUseCase getShipperProfile(AccountId accountId) throws AccountDomainException {
        shipperProfile = accountRepository.findShipperBy(accountId);
        return this;
    }


    @Override
    public ProfileResponse end() {
        ProfileResponse response = new ProfileResponse();
        response.setProfile(profile);
        return response;
    }

    @Override
    public ShipperResponse endShipper() {
        ShipperResponse response = new ShipperResponse();
        response.setShipperProfile(shipperProfile);
        response.setStatus(updated != 0);
        return response;
    }

    @Override
    public ProfileUseCase getShipperProfile(AccountId accountId, AuditLog auditLog) {
        shipperProfile = accountRepository.findShipperBy(accountId, auditLog);
        return this;
    }

    @Override
    public ProfileUseCase applyCardID(CardID cardID) {
        shipperProfile.setCardID(cardID);
        return this;
    }

    @Override
    public ProfileUseCase applyVehicle(Vehicle cehicle) {
        shipperProfile.setVehicle(cehicle);
        return this;
    }

    @Override
    public ProfileUseCase applyDriverLicense(DriverLicense driverLicense) {
        shipperProfile.setDriverLicense(driverLicense);
        return this;
    }

    @Override
    public ProfileUseCase activeAccount(AccountId accountId, String otp, AuditLog auditLog, DeviceToken deviceToken,
            HolaProvider serviceName) throws
            AccountDomainException {
        boolean verifyIMedia = accountRepository.verifyIMedia(accountId.getPhoneNumber(), otp);
        if (verifyIMedia) {
            updated = 1;
            accountRepository.activeShip(accountId, auditLog);
            Integer shop = serviceName == HolaProvider.SHOP ? 1 : null;
            Integer ship = serviceName == HolaProvider.SHIP ? 1 : null;
            if (isFirstLogin) {
                updated = accountRepository
                        .saveUserDevice(holaProfile.getAccountId(), deviceToken.getUniqueToken(), ship, shop);
            } else {
                updated = accountRepository
                        .saveUserDevice(holaAccount.getAccountId(), deviceToken.getUniqueToken(), ship, shop);
            }
            if (updated == 0) {
                throw new AccountDomainException("Cannot update Hola Account info");
            }
        }
        return this;
    }

    @Override
    public VerifyResponse endIdentity() {
        VerifyResponse response = new VerifyResponse();
        response.setStatus(updated != 0);
        return response;
    }

    @Override
    public ProfileUseCase getHolaAccount(AccountId accountId, DeviceToken deviceToken) {
        holaAccount = accountRepository.findByAccountId(accountId, deviceToken);
        if (holaAccount == null) {
            isFirstLogin = true;
        }
        return this;
    }

    @Override
    public ProfileUseCase getHolaAccountFirstLogin(AccountId accountId) {
        if (isFirstLogin) {
            holaProfile = accountRepository.findProfileById(accountId);
        }
        return this;
    }

    @Override
    public ProfileUseCase getBankAccount(AccountId accountId, HolaProvider serviceName) {
        Integer shop = serviceName == HolaProvider.SHOP ? 1 : null;
        Integer ship = serviceName == HolaProvider.SHIP ? 1 : null;
        bankAccountList  = accountRepository.getBankAccount(accountId,ship,shop);
        return this;
    }

    @Override
    public BankAccountListResponse endGetBankAccount() {
        BankAccountListResponse bankAccountListResponse = new BankAccountListResponse();
        bankAccountListResponse.setBankAccountList(bankAccountList);
        return bankAccountListResponse;
    }


}
