package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.servicepack.SPRequest;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPListSearch;
import vn.vmg.ptdv.hola.servicepack.factory.search.SPPagingAndSort;
import vn.vmg.ptdv.hola.servicepack.presentation.SPListRequest;
import vn.vmg.ptdv.hola.shared.OrderDomainState;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class SPJSONMapper {
    private static SPJSONMapper INSTANCE;

    private SPJSONMapper() {
    }

    public static SPJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SPJSONMapper();
        }
        return INSTANCE;
    }

//    public ServicePackSearchRequest fromServicePackJSONRequest(ServicePackSearchJSONRequest jsonRequest) {
//        ServicePackSearchRequest searchRequest = new ServicePackSearchRequest();
//        if (jsonRequest != null) {
//            ServicePackSearch search = new ServicePackSearch();
//            search.setServicePackID(new ServicePackID(jsonRequest.getId(), jsonRequest.getCode()));
//            search.setGlobalSearch(jsonRequest.getGlobalSearch());
//            search.setName(jsonRequest.getName());
//            if (jsonRequest.getCreatedAtFrom() != null) {
//                search.setCreatedAtFrom(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtFrom(), ZoneId.of("UTC"))));
//            }
//            if (jsonRequest.getCreatedAtTo() != null) {
//                search.setCreatedAtTo(new AuditLog().withCreatedAt(OffsetDateTime.ofInstant(jsonRequest.getCreatedAtTo(), ZoneId.of("UTC"))));
//            }
//            search.setStatus(jsonRequest.getStatus());
//            search.setSearchSuggest(jsonRequest.getSearchSuggest());
//
//            PagingSortFilter pagingSortFilter = new PagingSortFilter(jsonRequest.getPageIndex(), jsonRequest.getPageSize(),
//                    jsonRequest.getAsc(), jsonRequest.getFieldSort());
//
//            searchRequest.setServicePackSearch(search);
//            searchRequest.setPagingSortFilter(pagingSortFilter);
//        }
//        return searchRequest;
//    }















    public SPListRequest mapSPListRequest(SPRequest request) {
        SPListRequest spListRequest = new SPListRequest();
        if(request != null){
            SPPagingAndSort pagingAndSort = new SPPagingAndSort(request.getPageIndex(), request.getPageSize(), 0);
            spListRequest.setPagingAndSort(pagingAndSort);

            SPListSearch search = new SPListSearch();
//            search.setServicePackID(new ServicePackID(request.getCode()));
            search.setName(request.getName());
            if (request.getEffectiveAt() != null) {
                search.setEffectiveDate(OffsetDateTime.ofInstant(request.getEffectiveAt(), ZoneId.of("UTC")));
            }
            if(request.getState() != null){
                search.setState(OrderDomainState.fromBoolean(request.getState()));
            }
            if(request.getCreatedFrom() != null){
                search.setCreatedFrom(OffsetDateTime.ofInstant(request.getCreatedFrom(), ZoneId.of("UTC")));
            }
            if(request.getCreatedTo() != null){
                search.setCreatedTo(OffsetDateTime.ofInstant(request.getCreatedTo(), ZoneId.of("UTC")));
            }
            spListRequest.setSearch(search);
        }
        return spListRequest;
    }
}
