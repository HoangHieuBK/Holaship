//package vn.vmg.ptdv.hola.infra.servicepack;
//
//import vn.vmg.ptdv.hola.exception.OrderDomainException;
//import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
//import vn.vmg.ptdv.hola.infra.servicepack.mapper.ServicePackDBMapper;
//import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
//import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
//import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
//import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
//import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
//import vn.vmg.ptdv.hola.shared.AuditLog;
//import vn.vmg.ptdv.hola.shared.BaseFilter;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class ServicePackRepositoryImpl implements ServicePackRepository {
//    private final ServicePackJDBC servicePackJDBC;
//
//    public ServicePackRepositoryImpl(ServicePackJDBC servicePackJDBC) {
//        this.servicePackJDBC = servicePackJDBC;
//    }
//
//    @Override
//    public AuditLog update(ServicePack servicePack) {
//        return null;
//    }
//
//    @Override
//    public ServicePack findByID(ServicePackID servicePackID, AuditLog auditLog) {
//        return null;
//    }
//
//    @Override
//    public AuditLog save(ServicePack servicePack) {
//        return null;
//    }
//
//    @Override
//    public List<ServicePackInfo> getServicePack(ServicePackSearch servicePackSearch) throws OrderDomainException {
//        List<ServicePackInfo> list = new ArrayList<>();
//        try {
//            List<ServicePackDB> listServicePackDBs = servicePackJDBC.findListServicePack(servicePackSearch);
//            if (!listServicePackDBs.isEmpty()) {
//                for (ServicePackDB servicePackDB : listServicePackDBs
//                ) {
//                    list.add(ServicePackMapper.getInstance().fromServicePackDB(servicePackDB));
//                }
//            }
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getMessage());
//        }
//        return list;
//    }
//
//    @Override
//    public List<ServicePackInfo> findListServicePack(ServicePackSearch servicePackSearch, BaseFilter baseFilter) throws
//            OrderDomainException {
//        List<ServicePackDB> listServicePackDBs;
//        try {
//            listServicePackDBs = servicePackJDBC.listServicePack(servicePackSearch, baseFilter);
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getMessage());
//        }
//        List<ServicePackInfo> list = new ArrayList<>();
//        if (!listServicePackDBs.isEmpty()) {
//            for (ServicePackDB servicePackDB : listServicePackDBs) {
//                list.add(ServicePackMapper.getInstance().fromServicePackDBAll(servicePackDB));
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public List<ServicePackInfo> getHistoryServicePack(Integer id, LocalDate effectiveAt, BaseFilter baseFilter) throws
//            OrderDomainException {
//        List<ServicePackInfo> list = new ArrayList<>();
//        try {
//            List<ServicePackDB> listServicePackDBs = servicePackJDBC
//                    .findListHistoryServicePack(id, effectiveAt, baseFilter);
//            if (!listServicePackDBs.isEmpty()) {
//                for (ServicePackDB servicePackDB : listServicePackDBs
//                ) {
//                    list.add(ServicePackMapper.getInstance().fromHistoryServicePackDB(servicePackDB));
//                }
//            }
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getMessage());
//        }
//        return list;
//    }
//
//    @Override
//    public Integer countListServicePack(ServicePackSearch servicePackSearch) throws OrderDomainException {
//        int countTotal;
//        try {
//            countTotal = servicePackJDBC.countListServicePack(servicePackSearch);
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getCause());
//        }
//        return countTotal;
//    }
//
//    @Override
//    public Integer countTotalByHistoryServicePack(Integer id, LocalDate effectiveAt) throws OrderDomainException {
//        int countTotal;
//        try {
//            countTotal = servicePackJDBC.countTotalByHistoryServicePack(id, effectiveAt);
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getCause());
//        }
//        return countTotal;
//    }
//
//    @Override
//    public ServicePackInfo findServicePackById(int id) throws OrderDomainException {
//        Optional<ServicePackDB> servicePackDB = servicePackJDBC.findServicePackById(id);
//        ServicePackInfo servicePackInfo = null;
//        if (servicePackDB.isPresent()) {
//            servicePackInfo = ServicePackMapper.getInstance().fromfindServicePackByIdDB(servicePackDB.get());
//        }
//        return servicePackInfo;
//    }
//
//    public ServicePackInfo findServicePackByCode(String code) throws OrderDomainException {
//        Optional<ServicePackDB> servicePackDB;
//        try {
//            servicePackDB = servicePackJDBC.findServicePackByCode(code);
//        } catch (Exception e) {
//            throw new OrderDomainException(e.getCause());
//        }
//        ServicePackInfo servicePackInfo = null;
//        if (servicePackDB.isPresent()) {
//            servicePackInfo = ServicePackMapper.getInstance().fromServicePackDBAll(servicePackDB.get());
//        }
//        return servicePackInfo;
//    }
//
//    @Override
//    public Integer activeOrDeactive(ServicePackInfo servicePackInfo) throws OrderDomainException {
//        ServicePackDB servicePackDB = ServicePackMapper.getInstance().fromServicePackInfo(servicePackInfo);
//        Integer update = servicePackJDBC.activeOrDeactive(servicePackDB);
//        return update;
//    }
//
//    public Integer createServicePack(ServicePackInfo servicePackInfo, ServicePackID servicePackId) throws
//            OrderDomainException {
//        ServicePackDB servicePackDB = ServicePackDBMapper.getInstance().mapServicePackInfo(servicePackInfo);
//        Long servicePackSettingId = servicePackJDBC.saveServicePack(servicePackDB, servicePackId);
//        int create = 0;
//        if (servicePackSettingId != 0) {
////            servicePackDB.setServicePackSettingId(servicePackSettingId);
////            create = servicePackJDBC.saveServicePackSetting(servicePackDB);
//        }
//        return create;
//    }
//}
