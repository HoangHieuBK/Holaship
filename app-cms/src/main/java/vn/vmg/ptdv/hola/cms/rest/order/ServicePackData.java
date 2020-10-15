package vn.vmg.ptdv.hola.cms.rest.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicePackData {
    private static ServicePackData INSTANCE;

    private ServicePackData() {
    }

    public static ServicePackData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePackData();
        }
        return INSTANCE;
    }

    public List<HistoryServicePackJSON> listHistorysTest() {

        List<HistoryServicePackJSON> listHistorys = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String updatedate = "16/08/2016";
        String ulodate = "16/08/2016";
        LocalDate localDate = LocalDate.parse(ulodate, formatter);
        LocalDate updatedAt = LocalDate.parse(updatedate, formatter);

        LocalDate localDate2 = LocalDate.parse("17/08/2016", formatter);
        LocalDate updatedAt2 = LocalDate.parse("19/08/2016", formatter);
        HistoryServicePackJSON history = new HistoryServicePackJSON();
        history.setId(190l);
        history.setName("Lam dep trai 1");
        history.setCode("HOLA01");
        history.setUpdatedBy(126L);
        history.setNameUpdatedBy("Minh Vu");
        history.setUpdatedAt(updatedAt);
        history.setServicePackSettingId(1l);
        history.setEffectiveAt(localDate);
        history.setActive(1);


        HistoryServicePackJSON history1 = new HistoryServicePackJSON();

        history1.setId(191l);
        history1.setName("Minh");
        history1.setCode("Hoala2");
        history1.setUpdatedBy(126L);
        history1.setNameUpdatedBy("Minh Vu");
        history1.setUpdatedAt(updatedAt2);
        history1.setServicePackSettingId(1l);
        history1.setEffectiveAt(localDate2);
        history1.setActive(1);

        listHistorys.add(history);
        listHistorys.add(history1);
        return listHistorys;
    }

    public List<ServicePackDetailJSONResponse> listHistorysServicePackTest() {
        List<ServicePackDetailJSONResponse> listServicePackInfo = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String updatedate = "16/08/2016";
        String ulodate = "16/08/2016";
        LocalDate localDate = LocalDate.parse(ulodate, formatter);
        LocalDate updatedAt = LocalDate.parse(updatedate, formatter);
        Timestamp timestamp = null;
        timestamp = Timestamp.valueOf("2018-11-12 01:02:03.123456789");

        for(long i= 1; i<15; i++){
            ServicePackDetailJSONResponse servicePackInfo = new ServicePackDetailJSONResponse();
            servicePackInfo.setId(i);
            servicePackInfo.setServicePackSettingId(i);
            servicePackInfo.setName("Lam dep trai "+i);
            servicePackInfo.setCode("HOLA0"+i);
            servicePackInfo.setSettingId(i);
            servicePackInfo.setNote("giao trong "+i+"h");
            servicePackInfo.setActive(1);
            servicePackInfo.setStatus(1);
            servicePackInfo.setMaxTime((int) i+1);
            servicePackInfo.setMaxDistance((int) i+3);
            servicePackInfo.setMaxPoint((int) i);
            servicePackInfo.setMaxOrder((int) i+2);
            servicePackInfo.setBaseDistance((int) i);
            servicePackInfo.setBasePoint((int) i);
            servicePackInfo.setBaseCost((int) i+1);
            servicePackInfo.setBaseDistance((int) i+2);
            servicePackInfo.setSurchargeDistance(i*1000);
            servicePackInfo.setSurchargePoint(i*2000);
            servicePackInfo.setSurchargeOrderDetail(i*3000);
            servicePackInfo.setPorterFee(i*1000);
            servicePackInfo.setDoorDeliveryFee(i*50000);
            servicePackInfo.setRefundFee(i*1000);
            servicePackInfo.setPriceDeclareFee(i*1000);
            servicePackInfo.setType(1);
            servicePackInfo.setCreatedBy(i);
            servicePackInfo.setCreatedAt(localDate);
            servicePackInfo.setUpdatedBy(i);
            servicePackInfo.setUpdatedAt(updatedAt);
            servicePackInfo.setEffectiveAt(localDate);
            servicePackInfo.setSettingId(i);
            servicePackInfo.setOtherCost(i*1000);
            servicePackInfo.setBaseOrderDetail((int) i*4);
            servicePackInfo.setUTimestamp(timestamp.toInstant());
            listServicePackInfo.add(servicePackInfo);
        }
        return listServicePackInfo;
    }

    public List<ServicePackAutoSuggestJSON> listNameCode() {
        List<ServicePackAutoSuggestJSON> listNameCode = new ArrayList<>();
        ServicePackAutoSuggestJSON data = new ServicePackAutoSuggestJSON();
        data.setName("Gói hola test 1");
        data.setCode("HOLA_LEADTIME");
        ServicePackAutoSuggestJSON data1 = new ServicePackAutoSuggestJSON();
        data1.setName("Gói hola test 10");
        data1.setCode("HOLA_LEADTIME_10");
        ServicePackAutoSuggestJSON data2 = new ServicePackAutoSuggestJSON();
        data2.setName("Hola2");
        data2.setCode("Hola2");
        ServicePackAutoSuggestJSON data3 = new ServicePackAutoSuggestJSON();
        data3.setName("Hola3");
        data3.setCode("Hola3");
        ServicePackAutoSuggestJSON data4 = new ServicePackAutoSuggestJSON();
        data4.setName("Hola4");
        data4.setCode("Hola4");
        listNameCode.add(data);
        listNameCode.add(data1);
        listNameCode.add(data2);
        listNameCode.add(data3);
        listNameCode.add(data4);
        return listNameCode;
    }

}
