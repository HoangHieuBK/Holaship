package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeJSONRequest;
import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeListJSONResponse;
import vn.vmg.ptdv.hola.cms.rest.leadtime.LeadtimeSearchJSONRequest;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.factory.Leadtime;
import vn.vmg.ptdv.hola.leadtime.factory.search.LeadtimeSearch;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeListResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeRequest;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeResponse;
import vn.vmg.ptdv.hola.leadtime.presentation.LeadtimeSearchRequest;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class LeadtimeJsonMapper {

    private static LeadtimeJsonMapper INSTANCE;

    private LeadtimeJsonMapper() {
    }

    public static LeadtimeJsonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeJsonMapper();
        }
        return INSTANCE;
    }

    public LeadtimeRequest fromLeadtimeJsonRequest(LeadtimeJSONRequest leadtimeJSONRequest) {
        LeadtimeRequest request = new LeadtimeRequest();
        if (leadtimeJSONRequest != null) {
            LeadtimeId leadtimeId = new LeadtimeId();
            leadtimeId.setId(leadtimeJSONRequest.getId());
            leadtimeId.setCode(leadtimeJSONRequest.getCode());

            request.setLeadTimeId(leadtimeId);
            request.setName(leadtimeJSONRequest.getName());
            request.setNote(leadtimeJSONRequest.getNote());
            request.setStatus(leadtimeJSONRequest.getStatus());
            request.setEffectiveAt(leadtimeJSONRequest.getEffectiveAt() != null ? OffsetDateTime.ofInstant(leadtimeJSONRequest.getEffectiveAt(), ZoneId.of("UTC")) : null);
            request.setAuditLog(new AuditLog().withUTimestamp(leadtimeJSONRequest.getUTimestamp() != null ? Timestamp.from(leadtimeJSONRequest.getUTimestamp()) : null));
        }
        return request;
    }

    public LeadtimeSearchRequest fromLeadtimeSearchJsonRequest(LeadtimeSearchJSONRequest leadtimeSearchJSONRequest) {
        LeadtimeSearchRequest searchRequest = new LeadtimeSearchRequest();
        if (leadtimeSearchJSONRequest != null) {
            LeadtimeSearch leadtimeSearch = new LeadtimeSearch();
            leadtimeSearch.setLeadtimeId(new LeadtimeId(leadtimeSearchJSONRequest.getId(), leadtimeSearchJSONRequest.getCode()));
            leadtimeSearch.setGlobalSearch(leadtimeSearchJSONRequest.getGlobalSearch());
            leadtimeSearch.setName(leadtimeSearchJSONRequest.getName());
            if (leadtimeSearchJSONRequest.getEffectiveAt() != null) {
                leadtimeSearch.setEffectiveAt(OffsetDateTime.ofInstant(leadtimeSearchJSONRequest.getEffectiveAt(), ZoneId.of("UTC")));
            }
            if (leadtimeSearchJSONRequest.getCreatedAtFrom() != null) {
                leadtimeSearch.setCreatedAtFrom(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(leadtimeSearchJSONRequest.getCreatedAtFrom(), ZoneId.of("UTC"))));
            }
            if (leadtimeSearchJSONRequest.getCreatedAtTo() != null) {
                leadtimeSearch.setCreatedAtTo(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(leadtimeSearchJSONRequest.getCreatedAtTo(), ZoneId.of("UTC"))));
            }
            leadtimeSearch.setStatus(leadtimeSearchJSONRequest.getStatus());
            leadtimeSearch.setSearchSuggest(leadtimeSearchJSONRequest.getSearchSuggest());

            PagingSortFilter pagingSortFilter = new PagingSortFilter(leadtimeSearchJSONRequest.getPageIndex(), leadtimeSearchJSONRequest.getPageSize(), leadtimeSearchJSONRequest.getAsc(), leadtimeSearchJSONRequest.getFieldSort());

            searchRequest.setLeadtimeSearch(leadtimeSearch);
            searchRequest.setPagingSortFilter(pagingSortFilter);
        }

        return searchRequest;
    }

    public LeadtimeJSONResponse fromLeadtimeResponse(LeadtimeResponse leadtimeResponse) {
        LeadtimeJSONResponse jsonResponse = new LeadtimeJSONResponse();
        if (leadtimeResponse != null) {
            jsonResponse.setId(leadtimeResponse.getId());
            jsonResponse.setCode(leadtimeResponse.getCode());
            jsonResponse.setName(leadtimeResponse.getName());
            jsonResponse.setStatus(leadtimeResponse.getStatus());
            jsonResponse.setNote(leadtimeResponse.getNote());
            jsonResponse.setEffectiveAt(leadtimeResponse.getEffectiveAt());
            jsonResponse.setCreatedAt(leadtimeResponse.getCreatedAt());
            jsonResponse.setCreatedBy(leadtimeResponse.getCreatedBy());
            jsonResponse.setUpdatedAt(leadtimeResponse.getUpdatedAt());
            jsonResponse.setUpdatedBy(leadtimeResponse.getUpdatedBy());
            jsonResponse.setUTimestamp(leadtimeResponse.getUTimestamp());
        }
        return jsonResponse;
    }

    public LeadtimeListJSONResponse fromLeadtimeListResponse(LeadtimeListResponse leadtimeListResponse) {
        LeadtimeListJSONResponse leadtimeListJSONResponse = new LeadtimeListJSONResponse();
        if (leadtimeListResponse != null) {
            List<LeadtimeResponse> leadtimeResponses = new ArrayList<>();
            for (Leadtime leadtime : leadtimeListResponse.getLeadtimes()) {
                LeadtimeResponse leadtimeResponse = new LeadtimeResponse();
                leadtimeResponse.setId(leadtime.getLeadtimeId().getId());
                leadtimeResponse.setCode(leadtime.getLeadtimeId().getCode());
                leadtimeResponse.setName(leadtime.getName());
                leadtimeResponse.setStatus(leadtime.getStatus());
                leadtimeResponse.setNote(leadtime.getNote());
                leadtimeResponse.setEffectiveAt(leadtime.getEffectiveAt().toInstant());
                leadtimeResponse.setCreatedAt(leadtime.getAuditLog().getCreatedAt().toInstant());
                leadtimeResponse.setCreatedBy(leadtime.getAuditLog().getCreatedBy().getId());
                leadtimeResponse.setUpdatedAt(leadtime.getAuditLog().getUpdatedAt() != null ? leadtime.getAuditLog().getUpdatedAt().toInstant() : null);
                leadtimeResponse.setUpdatedBy(leadtime.getAuditLog().getUpdatedBy() != null ? leadtime.getAuditLog().getUpdatedBy().getId() : null);
                leadtimeResponse.setUTimestamp(leadtime.getAuditLog().getUTimestamp() != null ? leadtime.getAuditLog().getUTimestamp().toInstant() : null);
                leadtimeResponses.add(leadtimeResponse);
            }
            leadtimeListJSONResponse.setLeadtimes(leadtimeResponses);
            leadtimeListJSONResponse.setPageIndex(leadtimeListResponse.getPageIndex());
            leadtimeListJSONResponse.setPageSize(leadtimeListResponse.getPageSize());
            leadtimeListJSONResponse.setTotalCount(leadtimeListResponse.getTotalCount());
        }
        return leadtimeListJSONResponse;
    }

}
