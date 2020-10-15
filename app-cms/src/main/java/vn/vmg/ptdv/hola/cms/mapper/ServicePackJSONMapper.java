package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.order.ServicePackJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.servicepack.ServicePackJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.servicepack.ServicePackListJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.servicepack.ServicePackSearchJSONRequest;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.search.ServicePackSearch;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackListResponse;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackRequest;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackResponse;
import vn.vmg.ptdv.hola.servicepack.presentation.ServicePackSearchRequest;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public class ServicePackJSONMapper {
    private static ServicePackJSONMapper INSTANCE;

    public ServicePackJSONMapper() {
    }

    public static ServicePackJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePackJSONMapper();
        }
        return INSTANCE;
    }

    public ServicePackJSONResponse fromServicePackResponse(ServicePackResponse servicePackResponse) {
        ServicePackJSONResponse jsonResponse = new ServicePackJSONResponse();
        if (servicePackResponse != null) {
            jsonResponse.setId(servicePackResponse != null ? servicePackResponse.getId() : null);
            jsonResponse.setCode(servicePackResponse != null ? servicePackResponse.getCode() : null);
            jsonResponse.setName(servicePackResponse.getName());
            jsonResponse.setStatus(servicePackResponse.getStatus());
            jsonResponse.setNote(servicePackResponse.getNote());
            jsonResponse.setActive(servicePackResponse.getActive());
            jsonResponse.setEffectiveAt(servicePackResponse.getEffectiveAt());
            jsonResponse.setCreatedAt(servicePackResponse.getCreatedAt());
            jsonResponse.setCreatedBy(servicePackResponse.getCreatedBy());
            jsonResponse.setUpdatedAt(servicePackResponse.getUpdatedAt());
            jsonResponse.setUpdatedBy(servicePackResponse.getUpdatedBy());
            jsonResponse.setUTimestamp(servicePackResponse.getUTimestamp());
            jsonResponse.setMaxTime(servicePackResponse.getMaxTime());
            jsonResponse.setMaxDistance(servicePackResponse.getMaxDistance());
            jsonResponse.setMaxPoint(servicePackResponse.getMaxPoint());
            jsonResponse.setMaxOrder(servicePackResponse.getMaxOrder());
            jsonResponse.setBaseDistance(servicePackResponse.getBaseDistance());
            jsonResponse.setBasePoint(servicePackResponse.getBasePoint());
            jsonResponse.setBaseCost(servicePackResponse.getBaseCost());
            jsonResponse.setBaseOrderDetail(servicePackResponse.getBaseOrderDetail());
            jsonResponse.setSurchargeDistance(servicePackResponse.getSurchargeDistance());
            jsonResponse.setSurchargePoint(servicePackResponse.getSurchargePoint());
            jsonResponse.setSurchargeOrderDetail(servicePackResponse.getSurchargeOrderDetail());
            jsonResponse.setPorterFee(servicePackResponse.getPorterFee());
            jsonResponse.setDoorDeliveryFee(servicePackResponse.getDoorDeliveryFee());
            jsonResponse.setRefundFee(servicePackResponse.getRefundFee());
            jsonResponse.setPriceDeclareFee(servicePackResponse.getPriceDeclareFee());
            jsonResponse.setType(servicePackResponse.getType());
            jsonResponse.setOtherCost(servicePackResponse.getOtherCost());
        }
        return jsonResponse;
    }

    public ServicePackRequest fromServicePackJSONRequest(ServicePackJSONRequest jsonRequest) {
        ServicePackRequest searchRequest = new ServicePackRequest();
        if (jsonRequest != null) {
            ServicePackSearch search = new ServicePackSearch();
            search.setServicePackID(new ServicePackID(jsonRequest.getId(), jsonRequest.getCode()));
            search.setName(jsonRequest.getName());
//            if (jsonRequest.getCreatedAtFrom() != null) {
//                search.setCreatedAtFrom(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtFrom(), ZoneId.of("UTC"))));
//            }
//            if (jsonRequest.getCreatedAtTo() != null) {
//                search.setCreatedAtTo(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtTo(), ZoneId.of("UTC"))));
//            }
            search.setStatus(jsonRequest.getStatus());
        }
        return searchRequest;
    }

    public ServicePackListJSONResponse fromServicePackListResponse(ServicePackListResponse servicePackListResponse) {
        ServicePackListJSONResponse servicePackListJSONResponse = new ServicePackListJSONResponse();
        if (servicePackListResponse != null) {
            List<ServicePackResponse> servicePackResponses = new ArrayList<>();
            for (ServicePack servicePack : servicePackListResponse.getServicePacks()) {
                ServicePackID servicePackID = servicePack.getServicePackID();
                AuditLog auditLog = servicePack.getAuditLog();

                ServicePackResponse servicePackResponse = new ServicePackResponse();
                servicePackResponse.setId(servicePackID != null ? servicePackID.getId() : null);
                servicePackResponse.setCode(servicePackID != null ? servicePackID.getCode() : null);
                servicePackResponse.setName(servicePack.getName());
                servicePackResponse.setStatus(servicePack.getStatus());
                servicePackResponse.setNote(servicePack.getNote());
                servicePackResponse.setActive(servicePack.getActive());
                servicePackResponse.setEffectiveAt(servicePack.getEffectiveAt() != null ? servicePack.getEffectiveAt().toInstant() : null);
                if (auditLog != null) {
                    servicePackResponse.setCreatedAt(auditLog.getCreatedAt() != null ? auditLog.getCreatedAt().toInstant() : null);
                    servicePackResponse.setCreatedBy(auditLog.getCreatedBy() != null ? auditLog.getCreatedBy().getId() : null);
                    servicePackResponse.setUpdatedAt(auditLog.getUpdatedAt() != null ? auditLog.getUpdatedAt().toInstant() : null);
                    servicePackResponse.setUpdatedBy(auditLog.getUpdatedBy() != null ? auditLog.getUpdatedBy().getId() : null);
                    servicePackResponse.setUTimestamp(auditLog.getUTimestamp() != null ? auditLog.getUTimestamp().toInstant() : null);
                }
                servicePackResponse.setMaxTime(servicePack.getMaxTime());
                servicePackResponse.setMaxDistance(servicePack.getMaxDistance());
                servicePackResponse.setMaxPoint(servicePack.getMaxPoint());
                servicePackResponse.setMaxOrder(servicePack.getMaxOrder());
                servicePackResponse.setBaseDistance(servicePack.getBaseDistance());
                servicePackResponse.setBasePoint(servicePack.getBasePoint());
                servicePackResponse.setBaseCost(servicePack.getBaseCost());
                servicePackResponse.setBaseOrderDetail(servicePack.getBaseOrderDetail());
                servicePackResponse.setSurchargeDistance(servicePack.getSurchargeDistance());
                servicePackResponse.setSurchargePoint(servicePack.getSurchargePoint());
                servicePackResponse.setSurchargeOrderDetail(servicePack.getSurchargeOrderDetail());
                servicePackResponse.setPorterFee(servicePack.getPorterFee());
                servicePackResponse.setDoorDeliveryFee(servicePack.getDoorDeliveryFee());
                servicePackResponse.setRefundFee(servicePack.getRefundFee());
                servicePackResponse.setPriceDeclareFee(servicePack.getPriceDeclareFee());
                servicePackResponse.setType(servicePack.getType());
                servicePackResponse.setOtherCost(servicePack.getOtherCost());
                servicePackResponses.add(servicePackResponse);
            }
            servicePackListJSONResponse.setServicePacks(servicePackResponses);
            servicePackListJSONResponse.setPageIndex(servicePackListResponse.getPageIndex());
            servicePackListJSONResponse.setPageSize(servicePackListResponse.getPageSize());
            servicePackListJSONResponse.setTotalCount(servicePackListResponse.getTotalCount());
        }
        return servicePackListJSONResponse;
    }

    public ServicePackSearchRequest fromServicePackSearchJSONRequest(ServicePackSearchJSONRequest jsonRequest) {
        ServicePackSearchRequest searchRequest = new ServicePackSearchRequest();
        if (jsonRequest != null) {
            ServicePackSearch servicePackSearch = new ServicePackSearch();
            servicePackSearch.setServicePackID(new ServicePackID(jsonRequest.getId(), jsonRequest.getCode()));
            servicePackSearch.setGlobalSearch(jsonRequest.getGlobalSearch());
            servicePackSearch.setName(jsonRequest.getName());
            if (jsonRequest.getEffectiveAt() != null) {
                servicePackSearch.setEffectiveAt(OffsetDateTime.ofInstant(jsonRequest.getEffectiveAt(), ZoneId.of("UTC")));
            }
            if (jsonRequest.getCreatedAtFrom() != null) {
                servicePackSearch.setCreatedAtFrom(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtFrom(), ZoneId.of("UTC"))));
            }
            if (jsonRequest.getCreatedAtTo() != null) {
                servicePackSearch.setCreatedAtTo(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtTo(), ZoneId.of("UTC"))));
            }
            servicePackSearch.setStatus(jsonRequest.getStatus());
            servicePackSearch.setSearchSuggest(jsonRequest.getSearchSuggest());

            PagingSortFilter pagingSortFilter = new PagingSortFilter(jsonRequest.getPageIndex(), jsonRequest.getPageSize(), jsonRequest.getAsc(), jsonRequest.getFieldSort());

            searchRequest.setServicePackSearch(servicePackSearch);
            searchRequest.setPagingSortFilter(pagingSortFilter);
        }

        return searchRequest;
    }

}
