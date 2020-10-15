package vn.vmg.ptdv.hola.servicepack.repository;

import vn.vmg.ptdv.hola.exception.OrderDomainException;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.BaseFilter;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.time.LocalDate;
import java.util.List;

public interface ServicePackRepository {

    List<ServicePack> findAll(ServicePackSearch servicePackSearch, PagingSortFilter pagingSortFilter);

    ServicePack findByCode(String code);

    int create(ServicePack servicePack);

    int update(ServicePack servicePack);

    int changeStatus(ServicePack servicePack);




//    AuditLog update(ServicePack servicePack);

    ServicePack findByID(ServicePackID servicePackID, AuditLog auditLog);

    AuditLog save(ServicePack servicePack);

    //ToDo
    List<ServicePackInfo> getServicePack(ServicePackSearch servicePackSearch) throws OrderDomainException;

    List<ServicePackInfo> findListServicePack(ServicePackSearch servicePackSearch, BaseFilter baseFilter) throws
            OrderDomainException;

    List<ServicePackInfo> getHistoryServicePack(Long id, LocalDate effectiveAt, BaseFilter baseFilter) throws
            OrderDomainException;

    Integer countListServicePack(ServicePackSearch servicePackSearch) throws
            OrderDomainException;

    Integer countTotalByHistoryServicePack(Long id, LocalDate effectiveAt) throws
            OrderDomainException;

    ServicePackInfo findServicePackById(int id) throws OrderDomainException;

    Integer createServicePack(ServicePackInfo servicePackInfo, ServicePackID servicePackId) throws OrderDomainException;

    ServicePackInfo findServicePackByCode(String code) throws OrderDomainException;

    Integer activeOrDeactive(ServicePackID servicePackID, OrderDomainState state, AuditLog auditLog) throws OrderDomainException;

    List<ServicePack> findListPagingAndSort(SPListSearch search, SPPagingAndSort pagingAndSort);
}
