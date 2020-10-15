package vn.vmg.ptdv.hola.infra.account;

import vn.vmg.ptdv.hola.account.core.AccountId;
import vn.vmg.ptdv.hola.account.core.AuditLog;
import vn.vmg.ptdv.hola.infra.account.context.AccountInfraContext;
import vn.vmg.ptdv.hola.infra.account.factory.*;
import vn.vmg.ptdv.hola.infra.account.sql.*;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountJDBC {
    private final DataSource dataSource;
    private AccountInfraContext jdbcContext;

    public AccountJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<HolaAccountDB> findByPhone(String phoneNumber,String deviceToken) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindAppUserByPhone())
                .withParams(phoneNumber,deviceToken)
                .executeCommand();
        if (rs.size() == 0) {
            return Optional.empty();
        }

        return Optional.of((HolaAccountDB) rs.get(0));
    }

    public Optional<HolaAccountDB> findByPhoneWithNonDeviceToken(String phoneNumber){
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindAppUserByPhoneWithNonDeviceToken())
                .withParams(phoneNumber)
                .executeCommand();
        if (rs.size() == 0) {
            return Optional.empty();
        }

        return Optional.of((HolaAccountDB) rs.get(0));
    }



    public int updateBy(HolaAccountDB holaAccountDB) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new UpdateActiveAccount(holaAccountDB.getShip(), holaAccountDB.getShop()))
                .withParams(holaAccountDB)
                .executeUpdate(null);
        return updated;
    }

    public int save(UserDevice userDevice, AccountId accountId) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int created = jdbcContext
                .withSQLCommand(new SavingUserDevice())
                .withParams(userDevice)
                .executeUpdate(accountId);
        return created;
    }

    public int save(HolaAccountDB holaAccountDB, AccountId accountId) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int created = jdbcContext
                .withSQLCommand(new SavingHolaAccount())
                .withParams(holaAccountDB)
                .executeUpdate(accountId);
        return created;
    }

    public int saveShipperProfile(ShipperProfileDB shipperProfileDB, AccountId accountId) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int created = jdbcContext
                .withSQLCommand(new SavingShipperProfile())
                .withParams(shipperProfileDB)
                .executeUpdate(null);
        return created;
    }

    public Optional<HolaProfileDB> findBy(String phoneNumber) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindUserBasicInfo())
                .withParams(phoneNumber)
                .executeCommand();
        if (rs.size() == 0) {
            return Optional.empty();
        }

        return Optional.of((HolaProfileDB) rs.get(0));
    }

    public int updateBasic(BasicProfileDB basicProfileDB) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new UpdateBasicProfileSQL())
                .withParams(basicProfileDB)
                .executeUpdate(null);
        return updated;
    }

    public Optional<ShipperInfoDB> findShipperByPhone(String phoneNumber) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindShipperInfoSQL())
                .withParams(phoneNumber)
                .executeCommand();
        if (rs == null || rs.size() == 0) {
            return Optional.empty();
        }

        return Optional.of((ShipperInfoDB) rs.get(0));
    }

    public int updateCardID(ShipperInfoDB db, AuditLog auditLog) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new UpdateCardIDProfileSQL())
                .withParams(db, auditLog)
                .executeUpdate(null);
        return updated;
    }

    public Optional<ShipperInfoDB> findShipperByPhone(String phoneNumber, Timestamp uTimestamp) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> rs = jdbcContext
                .withSQLCommand(new FindShipperForUpdate())
                .withParams(phoneNumber, uTimestamp)
                .executeCommand();
        if (rs == null || rs.size() == 0) {
            return Optional.empty();
        }

        return Optional.of((ShipperInfoDB) rs.get(0));
    }

    public int updateVehicle(ShipperInfoDB db, AuditLog auditLog) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new UpdateVehicleProfileSQL())
                .withParams(db, auditLog)
                .executeUpdate(null);
        return updated;
    }

    public int updateDriverLicense(ShipperInfoDB db, AuditLog auditLog) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new UpdateDriverLicenseProfileSQL())
                .withParams(db, auditLog)
                .executeUpdate(null);
        return updated;
    }

    public int activeShip(String phoneNumber, AuditLog auditLog) {
        this.jdbcContext = new AccountInfraContext(dataSource);
        int updated = jdbcContext
                .withSQLCommand(new ActiveAccountShip())
                .withParams(phoneNumber, auditLog)
                .executeUpdate(null);
        return updated;
    }
    public List<BankAccountDB> findBankAccount(AccountId accountId,Integer roleType){
        this.jdbcContext = new AccountInfraContext(dataSource);
        List<?> result = jdbcContext
                .withSQLCommand(new FindBankAccountList())
                .withParams(accountId.getPhoneNumber(), roleType)
                .executeCommand();
        if(result.size() > 0){
            return (List<BankAccountDB>) result;
        }
        return new ArrayList<>();
    }

}
