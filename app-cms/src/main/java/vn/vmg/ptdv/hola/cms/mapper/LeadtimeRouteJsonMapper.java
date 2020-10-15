package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeRouteListJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.leadtimeRoute.LeadtimeRouteJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.leadtimeRoute.LeadtimeRouteJSONResponse;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeRouteSearch;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRouteResponse;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LeadtimeRouteJsonMapper {

    private static LeadtimeRouteJsonMapper INSTANCE;

    private LeadtimeRouteJsonMapper() {
    }

    public static LeadtimeRouteJsonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeRouteJsonMapper();
        }
        return INSTANCE;
    }

    public LeadtimeRouteSearch fromLeadtimeId(Long leadtimeId) {
        LeadtimeRouteSearch search = new LeadtimeRouteSearch();
        if (leadtimeId != null) {
            search.setLeadtimeId(new LeadtimeId(leadtimeId, null));
        }

        return search;
    }

    public LeadtimeRouteListJSONResponse fromLeadtimeRouteListResponse(LeadtimeRouteListResponse leadtimeRouteListResponse) {
        LeadtimeRouteListJSONResponse leadtimeListJSONResponse = new LeadtimeRouteListJSONResponse();
        if (leadtimeRouteListResponse != null) {
            List<LeadtimeRouteResponse> leadtimeRouteResponses = new ArrayList<>();
            for (LeadtimeRoute leadtimeRoute : leadtimeRouteListResponse.getLeadtimeRoutes()) {
                LeadtimeRouteResponse leadtimeRouteResponse = new LeadtimeRouteResponse();
                leadtimeRouteResponse.setId(leadtimeRoute.getLeadtimeRouteId().getId());
                leadtimeRouteResponse.setCode(leadtimeRoute.getLeadtimeRouteId().getCode());
                leadtimeRouteResponse.setFromProvinceId(leadtimeRoute.getFromProvince().getId());
                leadtimeRouteResponse.setFromProvinceCode(leadtimeRoute.getFromProvince().getCode());
                leadtimeRouteResponse.setFromProvinceName(leadtimeRoute.getFromProvinceName());
                leadtimeRouteResponse.setToProvinceId(leadtimeRoute.getToProvince().getId());
                leadtimeRouteResponse.setToProvinceCode(leadtimeRoute.getToProvince().getCode());
                leadtimeRouteResponse.setToProvinceName(leadtimeRoute.getToProvinceName());
                leadtimeRouteResponse.setFromDistrictId(leadtimeRoute.getFromDistrict().getId());
                leadtimeRouteResponse.setFromDistrictCode(leadtimeRoute.getFromDistrict().getCode());
                leadtimeRouteResponse.setFromDistrictName(leadtimeRoute.getFromDistrictName());
                leadtimeRouteResponse.setToDistrictId(leadtimeRoute.getToDistrict().getId());
                leadtimeRouteResponse.setToDistrictCode(leadtimeRoute.getToDistrict().getCode());
                leadtimeRouteResponse.setToDistrictName(leadtimeRoute.getToDistrictName());
                leadtimeRouteResponse.setType(leadtimeRoute.getType());
                leadtimeRouteResponse.setStatus(leadtimeRoute.getStatus());
                leadtimeRouteResponse.setEstimatedDeliveryDay(leadtimeRoute.getEstimatedDeliveryDay());
                leadtimeRouteResponse.setTransportType(leadtimeRoute.getVehicle().getType());
                leadtimeRouteResponse.setLimitWeight(leadtimeRoute.getWeight().getLimitWeight());
                leadtimeRouteResponse.setMaxWeight(leadtimeRoute.getWeight().getMaxWeight());
                leadtimeRouteResponse.setLimitType(leadtimeRoute.getWeight().getLimitType());
                leadtimeRouteResponse.setLeadtimeId(leadtimeRoute.getLeadtimeId().getId());
                leadtimeRouteResponses.add(leadtimeRouteResponse);
            }
            leadtimeListJSONResponse.setLeadtimeRoutes(leadtimeRouteResponses);
        }
        return leadtimeListJSONResponse;
    }

    public LeadtimeRouteJSONResponse fromLeadtimeRouteResponse(LeadtimeRouteResponse leadtimeRouteResponse) {
        LeadtimeRouteJSONResponse leadtimeRouteJSONResponse = new LeadtimeRouteJSONResponse();
        if (leadtimeRouteResponse != null) {
            leadtimeRouteJSONResponse.setId(leadtimeRouteResponse.getId());
            leadtimeRouteJSONResponse.setCode(leadtimeRouteResponse.getCode());
            leadtimeRouteJSONResponse.setFromProvinceId(leadtimeRouteResponse.getFromProvinceId());
            leadtimeRouteJSONResponse.setFromProvinceCode(leadtimeRouteResponse.getFromProvinceCode());
            leadtimeRouteJSONResponse.setFromProvinceName(leadtimeRouteResponse.getFromProvinceName());
            leadtimeRouteJSONResponse.setToProvinceId(leadtimeRouteResponse.getToProvinceId());
            leadtimeRouteJSONResponse.setToProvinceCode(leadtimeRouteResponse.getToProvinceCode());
            leadtimeRouteJSONResponse.setToProvinceName(leadtimeRouteResponse.getToProvinceName());
            leadtimeRouteJSONResponse.setFromDistrictId(leadtimeRouteResponse.getFromDistrictId());
            leadtimeRouteJSONResponse.setFromDistrictCode(leadtimeRouteResponse.getFromDistrictCode());
            leadtimeRouteJSONResponse.setFromDistrictName(leadtimeRouteResponse.getFromDistrictName());
            leadtimeRouteJSONResponse.setToDistrictId(leadtimeRouteResponse.getToDistrictId());
            leadtimeRouteJSONResponse.setToDistrictCode(leadtimeRouteResponse.getToDistrictCode());
            leadtimeRouteJSONResponse.setToDistrictName(leadtimeRouteResponse.getToDistrictName());
            leadtimeRouteJSONResponse.setType(leadtimeRouteResponse.getType());
            leadtimeRouteJSONResponse.setStatus(leadtimeRouteResponse.getStatus());
            leadtimeRouteJSONResponse.setEstimatedDeliveryDay(leadtimeRouteResponse.getEstimatedDeliveryDay());
            leadtimeRouteJSONResponse.setTransportType(leadtimeRouteResponse.getTransportType());
            leadtimeRouteJSONResponse.setLimitWeight(leadtimeRouteResponse.getLimitWeight());
            leadtimeRouteJSONResponse.setMaxWeight(leadtimeRouteResponse.getMaxWeight());
            leadtimeRouteJSONResponse.setLimitType(leadtimeRouteResponse.getLimitType());
            leadtimeRouteJSONResponse.setLeadtimeId(leadtimeRouteResponse.getLeadtimeId());
            leadtimeRouteJSONResponse.setUTimestamp(leadtimeRouteResponse.getUTimestamp());
        }
        return leadtimeRouteJSONResponse;
    }

    public LeadtimeRouteRequest fromLeadtimeRouteJsonRequest(LeadtimeRouteJSONRequest leadtimeRouteJSONRequest) {
        LeadtimeRouteRequest request = new LeadtimeRouteRequest();
        if (leadtimeRouteJSONRequest != null) {
            request.setStatus(leadtimeRouteJSONRequest.getStatus());
            request.setAuditLog(new AuditLog().withUTimestamp(leadtimeRouteJSONRequest.getUTimestamp() != null ? Timestamp.from(leadtimeRouteJSONRequest.getUTimestamp()) : null));
        }
        return request;
    }

}
