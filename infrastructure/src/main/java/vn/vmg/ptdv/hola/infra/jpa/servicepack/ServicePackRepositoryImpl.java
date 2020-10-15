package vn.vmg.ptdv.hola.infra.jpa.servicepack;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.entity.EServicePackSetting;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.mapper.EServicePackMapper;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.repository.ServicePackSettingJPARepository;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeDB;
import vn.vmg.ptdv.hola.infra.servicepack.ServicePackJDBC;
import vn.vmg.ptdv.hola.infra.servicepack.ServicePackMapper;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.infra.servicepack.mapper.ServicePackDBMapper;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.BaseFilter;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServicePackRepositoryImpl implements ServicePackRepository {

    private final ServicePackSettingJPARepository jpaRepository;
    private final ServicePackJDBC jdbc;

    public ServicePackRepositoryImpl(ServicePackSettingJPARepository jpaRepository, ServicePackJDBC jdbc) {
        this.jpaRepository = jpaRepository;
        this.jdbc = jdbc;
    }

    @Override
    public List<ServicePack> findAll(ServicePackSearch servicePackSearch, PagingSortFilter pagingSortFilter) {
        List<ServicePackDB> servicePackDBs = jdbc.findAll(servicePackSearch, pagingSortFilter);
        List<ServicePack> result = new ArrayList<>();
        if (!servicePackDBs.isEmpty()) {
            for (ServicePackDB servicePackDB : servicePackDBs
            ) {
                result.add(ServicePackDBMapper.getInstance().fromDB(servicePackDB));
            }
        }
        return result;
    }

    @Override
    public ServicePack findByCode(String code) {
        ServicePackDB dbRs = jdbc.findByCode(code);
        if (dbRs == null) {
            return null;
        }
        ServicePack servicePack = ServicePackDBMapper.getInstance().fromDB(dbRs);
        return servicePack;
    }

    @Override
    public int create(ServicePack servicePack) {
        ServicePackDB objDB = new ServicePackDB();
        objDB.setCode(servicePack.getServicePackID().getCode());
        objDB.setName(servicePack.getName());
        objDB.setCreatedBy(servicePack.getAuditLog().getCreatedBy() != null ? servicePack.getAuditLog().getCreatedBy().getId() : null);

        return jdbc.create(objDB, servicePack.getServicePackID());
    }

    @Override
    public int update(ServicePack servicePack) {
        ServicePackDB objDB = new ServicePackDB();
        objDB.setId(servicePack.getServicePackID().getId());
        objDB.setCode(servicePack.getServicePackID().getCode());
        objDB.setName(servicePack.getName());
        objDB.setStatus(servicePack.getStatus());
        objDB.setUpdatedBy(servicePack.getAuditLog().getUpdatedBy() != null ? servicePack.getAuditLog().getUpdatedBy().getId() : null);
        objDB.setUTimestamp(servicePack.getAuditLog().getUTimestamp() != null ? servicePack.getAuditLog().getUTimestamp() : null);

        return jdbc.update(objDB);
    }

    @Override
    public int changeStatus(ServicePack servicePack) {
        return jdbc.changeStatus(servicePack.getServicePackID().getId(), servicePack.getStatus(), servicePack.getAuditLog().getUTimestamp());
    }







//    @Override
//    public AuditLog update(ServicePack servicePack) {
//        EServicePackSetting entity = EServicePackMapper.getInstance().bindSPSettingFrom(servicePack);
//        EServicePackSetting saved = jpaRepository.save(entity);
//        return new AuditLog().withUTimestamp(saved.getUTimestamp());
//    }

    @Override
    public ServicePack findByID(ServicePackID servicePackID, AuditLog auditLog) {
        EServicePackSetting entity = jpaRepository
                .findByIdAndUTimestamp(servicePackID.getCode(), auditLog.getUTimestamp());
        return EServicePackMapper.getInstance().bindSFromPSetting(entity);
    }

    @Override
    public AuditLog save(ServicePack servicePack) {
        EServicePackSetting entity = EServicePackMapper.getInstance().bindSPSettingFrom(servicePack);
        EServicePackSetting saved = jpaRepository.save(entity);
        return new AuditLog().withUTimestamp(saved.getUTimestamp());
    }

    @Override
    public List<ServicePackInfo> getServicePack(ServicePackSearch servicePackSearch) throws OrderDomainException {
        List<ServicePackInfo> list = new ArrayList<>();
        try {
            List<ServicePackDB> listServicePackDBs = jdbc.findListServicePack(servicePackSearch);
            if (!listServicePackDBs.isEmpty()) {
                for (ServicePackDB servicePackDB : listServicePackDBs
                ) {
                    list.add(ServicePackMapper.getInstance().fromServicePackDB(servicePackDB));
                }
            }
        } catch (Exception e) {
            throw new OrderDomainException(e.getMessage());
        }
        return list;
    }

    @Override
    public List<ServicePackInfo> findListServicePack(ServicePackSearch servicePackSearch, BaseFilter baseFilter) throws
            OrderDomainException {
        List<ServicePackDB> listServicePackDBs;
//        try {
//            listServicePackDBs = jdbc.listServicePack(servicePackSearch, baseFilter);
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getMessage());
//        }
        List<ServicePackInfo> list = new ArrayList<>();
//        if (!listServicePackDBs.isEmpty()) {
//            for (ServicePackDB servicePackDB : listServicePackDBs) {
//                list.add(ServicePackMapper.getInstance().fromServicePackDBAll(servicePackDB));
//            }
//        }
        return list;
    }

    @Override
    public List<ServicePackInfo> getHistoryServicePack(Long id, LocalDate effectiveAt, BaseFilter baseFilter) throws
            OrderDomainException {
        List<ServicePackInfo> list = new ArrayList<>();
        try {
            List<ServicePackDB> listServicePackDBs = jdbc
                    .findListHistoryServicePack(id, effectiveAt, baseFilter);
            if (!listServicePackDBs.isEmpty()) {
                for (ServicePackDB servicePackDB : listServicePackDBs
                ) {
                    list.add(ServicePackMapper.getInstance().fromHistoryServicePackDB(servicePackDB));
                }
            }
        } catch (Exception e) {
            throw new OrderDomainException(e.getMessage());
        }
        return list;
    }

    @Override
    public Integer countListServicePack(ServicePackSearch servicePackSearch) throws OrderDomainException {
        int countTotal;
        try {
            countTotal = jdbc.countListServicePack(servicePackSearch);
        } catch (Exception e) {
            throw new OrderDomainException(e.getCause());
        }
        return countTotal;
    }

    @Override
    public Integer countTotalByHistoryServicePack(Long id, LocalDate effectiveAt) throws OrderDomainException {
        int countTotal;
        try {
            countTotal = jdbc.countTotalByHistoryServicePack(id, effectiveAt);
        } catch (Exception e) {
            throw new OrderDomainException(e.getCause());
        }
        return countTotal;
    }

    @Override
    public ServicePackInfo findServicePackById(int id) throws OrderDomainException {
        Optional<ServicePackDB> servicePackDB = jdbc.findServicePackById(id);
        ServicePackInfo servicePackInfo = null;
        if (servicePackDB.isPresent()) {
            servicePackInfo = ServicePackMapper.getInstance().fromfindServicePackByIdDB(servicePackDB.get());
        }
        return servicePackInfo;
    }

    public ServicePackInfo findServicePackByCode(String code) throws OrderDomainException {
        Optional<ServicePackDB> servicePackDB;
        try {
            servicePackDB = jdbc.findServicePackByCode(code);
        } catch (Exception e) {
            throw new OrderDomainException(e.getCause());
        }
        ServicePackInfo servicePackInfo = null;
        if (servicePackDB.isPresent()) {
            servicePackInfo = ServicePackMapper.getInstance().fromServicePackDBAll(servicePackDB.get());
        }
        return servicePackInfo;
    }

    @Override
    public Integer activeOrDeactive(ServicePackID servicePackID, OrderDomainState state, AuditLog auditLog) throws OrderDomainException {
        ServicePackDB servicePackDB = new ServicePackDB();
        servicePackDB.setCode(servicePackID.getCode());
        servicePackDB.setStatus(1);
        servicePackDB.setUTimestamp(auditLog.getUTimestamp());
        return jdbc.activeOrDeactive(servicePackDB);
    }

    @Override
    public List<ServicePack> findListPagingAndSort(SPListSearch search, SPPagingAndSort pagingAndSort) {
        OrderDomainState state = search.getState();
        Boolean searchState = null;
        if (state != null) {
            searchState = state.toBoolean();
        }

        List<EServicePackSetting> listOfEntity = jpaRepository
                .findBySearch(search.getGlobalSearch(), search.getServicePackID().getCode(),
                        search.getName(), searchState, search.getEffectiveDate(),
                        search.getCreatedFrom(), search.getCreatedTo());
        return listOfEntity.stream().map(e -> EServicePackMapper.getInstance().bindSFromPSetting(e))
                .collect(Collectors.toList());
    }

    public Integer createServicePack(ServicePackInfo servicePackInfo, ServicePackID servicePackId) throws
            OrderDomainException {
        ServicePackDB servicePackDB = ServicePackDBMapper.getInstance().mapServicePackInfo(servicePackInfo);
        Long servicePackSettingId = jdbc.saveServicePack(servicePackDB, servicePackId);
        int create = 0;
        if (servicePackSettingId != 0) {
//            servicePackDB.setServicePackSettingId(servicePackSettingId);
//            create = servicePackJDBC.saveServicePackSetting(servicePackDB);
        }
        return create;
    }

}
