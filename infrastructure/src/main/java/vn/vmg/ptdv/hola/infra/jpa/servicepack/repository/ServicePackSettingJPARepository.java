package vn.vmg.ptdv.hola.infra.jpa.servicepack.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.entity.EServicePackSetting;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

public interface ServicePackSettingJPARepository extends PagingAndSortingRepository<EServicePackSetting, Long> {

    @Query("SELECT e FROM EServicePackSetting e " +
                   "WHERE  e.eServicePack.code = :servicePackCode AND e.uTimestamp = :uTimestamp ")
    EServicePackSetting findByIdAndUTimestamp(String servicePackCode, Timestamp uTimestamp);

    @Query("SELECT e FROM EServicePackSetting e " +
                   "WHERE  ( :servicePackCode IS NULL OR e.eServicePack.code = :servicePackCode) " +
                   "    AND( :servicePackName IS NULL OR e.eServicePack.name LIKE %:servicePackName%) " +
                   "    AND( :active IS NULL OR e.effectiveStatus = :active) " +
                   "    AND( :effectiveDate IS NULL OR e.effectiveDate = :effectiveDate) " +
                   "    AND( :createdFrom IS NULL OR e.createdAt >= :createdFrom) " +
                   "    AND( :createdTo IS NULL OR e.createdAt <= :createdTo )" +
                   "    AND( :globalSearch IS NULL " +
                   "        OR e.eServicePack.code = :globalSearch " +
                   "        OR e.eServicePack.name LIKE %:globalSearch% " +
                   "        OR e.deliveryTimeMax = :globalSearch" +
                   "        OR e.distanceMax = :globalSearch" +
                   "        OR e.priceBase = :globalSearch" +
                   ")"
    )
    List<EServicePackSetting> findBySearch(String globalSearch, String servicePackCode, String servicePackName,
            Boolean active,
            OffsetDateTime effectiveDate, OffsetDateTime createdFrom, OffsetDateTime createdTo);
}
