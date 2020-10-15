package vn.vmg.ptdv.hola.infra.account.mapper;

import vn.vmg.ptdv.hola.account.core.*;
import vn.vmg.ptdv.hola.account.exception.AccountDomainException;
import vn.vmg.ptdv.hola.account.factory.HolaProfile;
import vn.vmg.ptdv.hola.account.factory.ShipperProfile;
import vn.vmg.ptdv.hola.infra.account.factory.BasicProfileDB;
import vn.vmg.ptdv.hola.infra.account.factory.HolaProfileDB;
import vn.vmg.ptdv.hola.infra.account.factory.ShipperInfoDB;

public class HolaProfileDBMapper {
    private static HolaProfileDBMapper INSTANCE;

    private HolaProfileDBMapper() {

    }

    public static HolaProfileDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HolaProfileDBMapper();
        }
        return INSTANCE;
    }

    public HolaProfile fromDB(HolaProfileDB holaProfileDB) {
        HolaProfile profile = new HolaProfile();
        profile.setAccountEpurseId(holaProfileDB.getAccountEpurseId());
        profile.setDeviceToken(new DeviceToken().withUniqueToken(holaProfileDB.getDeviceToken()));
        profile.setEmail(new Email().withAddress(holaProfileDB.getEmail()));
        profile.setAccountId(new AccountId(holaProfileDB.getId(), holaProfileDB.getPhone()));
        profile.setAuditLog(new AuditLog(holaProfileDB.getUTimestamp()));
        profile.setHolaService(new HolaService()
                .withShipService(holaProfileDB.getShip())
                .withShopService(holaProfileDB.getShop()));
        profile.setDisplayName(holaProfileDB.getName());
        profile.setBirthDay(holaProfileDB.getBirthday());
        profile.setAvatar(new Avatar().withPath(holaProfileDB.getAvatar()));
        profile.setGender(new Gender(holaProfileDB.getSex()));

        return profile;
    }

    public BasicProfileDB fromBasic(HolaProfile profile) {
        BasicProfileDB basicProfileDB = new BasicProfileDB();
        basicProfileDB.setAvatar(profile.getAvatar().getPath());
        basicProfileDB.setBirthday(profile.getBirthDay());
        basicProfileDB.setId(profile.getAccountId().getId());
        basicProfileDB.setName(profile.getDisplayName());
        basicProfileDB.setSex(profile.getGender().getValue());
        basicProfileDB.setUTimestamp(profile.getAuditLog().getUTimestamp());
        basicProfileDB.setPhone(profile.getAccountId().getPhoneNumber());

        return basicProfileDB;
    }

    public ShipperProfile fromDB(ShipperInfoDB db) throws AccountDomainException {
        ShipperProfile shipperProfile = new ShipperProfile();
        shipperProfile.setAccountId(new AccountId(db.getId()));
        shipperProfile.setAuditLog(new AuditLog(db.getUtimestamp()));
        shipperProfile.setCardID(new CardID()
                .withIdentity(db.getNumberCardID(), db.getDateCardID(), db.getPlaceCardID())
                .withRegistration(db.getImgBeforeCardID(), db.getImgAfterCardID())
                .build()
        );
        shipperProfile.setVehicle(new Vehicle()
                .withIdentity(db.getVehicleType(), db.getLicensePlace())
                .withRegistration(db.getImgBeforeDriverRegister(), db.getImgAfterDriverRegister())
                .withInsurance(db.getImgBeforeInsurance(), db.getImgAfterInsurance())
                .build()
        );
        shipperProfile.setDriverLicense(new DriverLicense()
                .withIdentity(db.getDriverLicense(), db.getSpeciesLicenseType())
                .withRegistration(db.getImgAfterDriverLicense(), db.getImgBeforeDriverLicense())
                .build()
        );

        return shipperProfile;
    }

    public ShipperInfoDB fromCardID(ShipperProfile profile) {
        ShipperInfoDB db = new ShipperInfoDB();
        db.setId(profile.getAccountId().getId());
        db.setNumberCardID(profile.getCardID().getNumberCardID());
        db.setPlaceCardID(profile.getCardID().getPlaceCardID());
        db.setImgBeforeCardID(profile.getCardID().getImgBeforeCardID());
        db.setImgAfterCardID(profile.getCardID().getImgAfterCardID());
        db.setUtimestamp(profile.getAuditLog().getUTimestamp());
        db.setDateCardID(profile.getCardID().getDateCardID());
        return db;
    }

    public ShipperInfoDB fromVehicle(ShipperProfile profile) {
        ShipperInfoDB db = new ShipperInfoDB();
        db.setId(profile.getAccountId().getId());
        db.setVehicleType(profile.getVehicle().getVehicleType());
        db.setLicensePlace(profile.getVehicle().getLicensePlace());
        db.setImgBeforeDriverRegister(profile.getVehicle().getImgBeforeDriverRegister());
        db.setImgAfterDriverRegister(profile.getVehicle().getImgAfterDriverRegister());
        db.setImgBeforeInsurance(profile.getVehicle().getImgBeforeInsurance());
        db.setImgAfterInsurance(profile.getVehicle().getImgAfterInsurance());
        db.setUtimestamp(profile.getAuditLog().getUTimestamp());
        return db;
    }

    public ShipperInfoDB fromDriverLicense(ShipperProfile profile) {
        ShipperInfoDB db = new ShipperInfoDB();
        db.setId(profile.getAccountId().getId());
        db.setDriverLicense(profile.getDriverLicense().getDriverLicense());
        db.setSpeciesLicenseType(profile.getDriverLicense().getSpeciesLicenseType());
        db.setImgAfterDriverLicense(profile.getDriverLicense().getImgAfterDriverLicense());
        db.setImgBeforeDriverLicense(profile.getDriverLicense().getImgBeforeDriverLicense());
        db.setUtimestamp(profile.getAuditLog().getUTimestamp());
        return db;
    }


    public ShipperProfile fromDBTimestamp(ShipperInfoDB db, String phoneNumber) {
        ShipperProfile shipperProfile = new ShipperProfile();
        shipperProfile.setAccountId(new AccountId(db.getId(), phoneNumber));
        shipperProfile.setAuditLog(new AuditLog(db.getUtimestamp()));
        return shipperProfile;
    }
}
