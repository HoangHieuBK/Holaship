package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.groupmanager.GroupManagerJSONRequest;
import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;
import vn.vmg.ptdv.hola.groupmanager.factory.search.GroupManagerSearch;
import vn.vmg.ptdv.hola.groupmanager.presentation.GroupManagerRequest;
import vn.vmg.ptdv.hola.leadtime.factory.search.PagingSortFilter;
import vn.vmg.ptdv.hola.shared.AuditLog;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class GroupManagerJSONMapper {
    private static GroupManagerJSONMapper INSTANCE;

    public GroupManagerJSONMapper() {
    }

    public static GroupManagerJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GroupManagerJSONMapper();
        }
        return INSTANCE;
    }
    public GroupManagerRequest fromGroupManagerRequest(GroupManagerJSONRequest request){

        GroupManagerRequest groupManagerRequest = new GroupManagerRequest();
        if(request != null){
            GroupManagerSearch search = new GroupManagerSearch();
            search.setGroupId(new GroupManagerId(request.getGroupCode()));
            search.setGroupName(request.getGroupName());
            search.setCommonSearch(request.getCommonSearch());

            if(request.getCreatedAtFrom() != null){
                search.setCreatedAtFrom(new AuditLog().withCreatedAt(
                        OffsetDateTime.ofInstant(request.getCreatedAtFrom(), ZoneId.of("UTC"))));
            }
            if(request.getCreatedAtTo() != null){
                search.setCreatedAtTo(new AuditLog().withCreatedAt(
                        OffsetDateTime.ofInstant(request.getCreatedAtTo(), ZoneId.of("UTC"))));
            }
            search.setStatus(request.getStatus());
            groupManagerRequest.setGroupManagerSearch(search);
        }

        PagingSortFilter pagingSortFilter = new PagingSortFilter();
        pagingSortFilter.setPageIndex(request.getPageIndex());
        pagingSortFilter.setPageSize(request.getPageSize());
        pagingSortFilter.setAsc(request.getAsc());
        pagingSortFilter.setFieldSort(request.getFieldSort());

        groupManagerRequest.setPagingSortFilter(pagingSortFilter);

        return groupManagerRequest;

    }

}
