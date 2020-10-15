package vn.vmg.ptdv.hola.servicepack.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.presentation.HistoryServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.BaseFilter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicePackMapper {
    private static ServicePackMapper INSTANCE;

    private ServicePackMapper() {
    }

    public static ServicePackMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePackMapper();
        }

        return INSTANCE;
    }

    public ServicePackResponse fromServicePack(ServicePack servicePack) {
        ServicePackResponse response = new ServicePackResponse();
        if (servicePack != null) {
            ServicePackID servicePackID = servicePack.getServicePackID();
            AuditLog auditLog = servicePack.getAuditLog();

            response.setId(servicePackID != null ? servicePackID.getId() : null);
            response.setCode(servicePackID != null ? servicePackID.getCode() : null);
            response.setName(servicePack.getName());
            response.setStatus(servicePack.getStatus());
            response.setNote(servicePack.getNote());
            response.setActive(servicePack.getActive());
            response.setEffectiveAt(servicePack.getEffectiveAt() != null ? servicePack.getEffectiveAt().toInstant() : null);
            if (auditLog != null) {
                OffsetDateTime createdAt = auditLog.getCreatedAt();
                AccountAdminId createdBy = auditLog.getCreatedBy();
                OffsetDateTime updatedAt = auditLog.getUpdatedAt();
                AccountAdminId updatedBy = auditLog.getUpdatedBy();
                Timestamp uTimestamp = auditLog.getUTimestamp();

                response.setCreatedAt(createdAt != null ? createdAt.toInstant() : null);
                response.setCreatedBy(createdBy != null ? createdBy.getId() : null);
                response.setUpdatedAt(updatedAt != null ? updatedAt.toInstant() : null);
                response.setUpdatedBy(updatedBy != null ? updatedBy.getId() : null);
                response.setUTimestamp(uTimestamp != null ? uTimestamp.toInstant() : null);
            }
            response.setMaxTime(servicePack.getMaxTime());
            response.setMaxDistance(servicePack.getMaxDistance());
            response.setMaxPoint(servicePack.getMaxPoint());
            response.setMaxOrder(servicePack.getMaxOrder());
            response.setBaseDistance(servicePack.getBaseDistance());
            response.setBasePoint(servicePack.getBasePoint());
            response.setBaseCost(servicePack.getBaseCost());
            response.setBaseOrderDetail(servicePack.getBaseOrderDetail());
            response.setSurchargeDistance(servicePack.getSurchargeDistance());
            response.setSurchargePoint(servicePack.getSurchargePoint());
            response.setSurchargeOrderDetail(servicePack.getSurchargeOrderDetail());
            response.setPorterFee(servicePack.getPorterFee());
            response.setDoorDeliveryFee(servicePack.getDoorDeliveryFee());
            response.setRefundFee(servicePack.getRefundFee());
            response.setPriceDeclareFee(servicePack.getPriceDeclareFee());
            response.setType(servicePack.getType());
            response.setOtherCost(servicePack.getOtherCost());
        }
        return response;
    }










    public ServicePackSearch buildServicePackSearch(ServicePackRequest request) {
        ServicePackSearch servicePackSearch = new ServicePackSearch();
        BeanUtils.copyProperties(request, servicePackSearch);
        return servicePackSearch;
    }

//    public BaseFilter buildServicePackFilter(ServicePackRequest request) {
//        BaseFilter filter = new BaseFilter();
//        if (request.getAsc() != null)
//            filter.setAsc(request.getAsc());
//        if (request.getPageIndex() != null)
//            filter.setPageIndex(request.getPageIndex());
//        if (request.getPageSize() != null)
//            filter.setPageSize(request.getPageSize());
//        if (!StringUtils.isEmpty(request.getFieldSort()) && request.getFieldSort() != null) {
//            List<String> fieldSorts = new ArrayList<>();
//            fieldSorts.add(request.getFieldSort());
//            filter.setFieldSort(fieldSorts);
//        }
//        return filter;
//    }

    public BaseFilter buildFilterServicePack(HistoryServicePackRequest request) {
        BaseFilter filter = new BaseFilter();
        BeanUtils.copyProperties(request, filter);
        return filter;
    }

    public ServicePackInfo buildFromServicePackRequest(ServicePackRequest request) {
        ServicePackInfo servicePackInfo = new ServicePackInfo();
        BeanUtils.copyProperties(request, servicePackInfo);
        return servicePackInfo;
    }

    public ServicePackInfo buildServicePackInfo(ServicePackRequest request) {
        ServicePackInfo servicePackInfo = new ServicePackInfo();
        BeanUtils.copyProperties(request, servicePackInfo);
        return servicePackInfo;
    }

}
