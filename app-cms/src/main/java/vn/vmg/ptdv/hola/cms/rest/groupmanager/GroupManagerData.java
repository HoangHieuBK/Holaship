package vn.vmg.ptdv.hola.cms.rest.groupmanager;

import vn.vmg.ptdv.hola.groupmanager.core.GroupManagerId;
import vn.vmg.ptdv.hola.groupmanager.presentation.GroupManagerAutoSuggestResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GroupManagerData {
    private static GroupManagerData INSTANCE;

    private GroupManagerData() {
    }

    public static GroupManagerData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GroupManagerData();
        }
        return INSTANCE;
    }

    public GroupManagerJSONResponse getGroupManagerData() {
        GroupManagerJSONResponse response = new GroupManagerJSONResponse();
        response.setPageIndex(1l);
        response.setPageSize(15l);
        response.setTotalRecord(15);

        List<GroupManagerResponse> list = new ArrayList<>();

        for (long i = 1; i <= 15; i++) {
            GroupManagerResponse groupManagerResponse = new GroupManagerResponse();
            groupManagerResponse.setGroupid(new GroupManagerId(i, "GM001" + i));
            groupManagerResponse.setGrounpName("Goi cuoc " + i);
            groupManagerResponse.setGroupSort((int) i);
            Instant offsetDateTime = Instant.now();
            groupManagerResponse.setCreateAt(offsetDateTime);
            if(i%2 ==0 || i%3 ==0){
                groupManagerResponse.setStatus(1);
            }else{
                groupManagerResponse.setStatus(0);
            }


            list.add(groupManagerResponse);
        }
        response.setList(list);

        return response;
    }

    public AutoSuggestResponse mapDataAutoSuggestResponse() {
        AutoSuggestResponse autoSuggestResponse = new AutoSuggestResponse();

        List<GroupManagerAutoSuggestResponse> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            GroupManagerAutoSuggestResponse autoSuggest = new GroupManagerAutoSuggestResponse();
            autoSuggest.setCode("KH00" + i);
            autoSuggest.setName("Nhom khách hàng" + i);
            list.add(autoSuggest);
        }
        autoSuggestResponse.setList(list);
        return autoSuggestResponse;

    }

    public GroupManagerDetailJSONResponse mapGroupManagerDetailJSONResponse() {
        GroupManagerDetailJSONResponse response = new GroupManagerDetailJSONResponse();
        response.setGroundCode("KH001");
        response.setGroundName("Nhom khách hàng 1");
        response.setPriceList(2);
        response.setStatus(1);
        response.setSingleQuantity(500l);
        response.setFreightFee(30000l);
        response.setMassProduct(20l);
        response.setValueRoute(10l);
        response.setRoute(1l);
        response.setGroundSort(2);
        response.setNote("Nhom khach hang vip");
        Instant instant = Instant.now();
        response.setUTimestamp(instant);

        return response;

    }
}
