package vn.vmg.ptdv.hola.cms.mapper;

import vn.vmg.ptdv.hola.cms.rest.order.HistoryServicePackJSON;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;


public class HistoryServicePackJSONMapper {
    private static HistoryServicePackJSONMapper INSTANCE;

    public HistoryServicePackJSONMapper() {
    }

    public static HistoryServicePackJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryServicePackJSONMapper();
        }
        return INSTANCE;
    }

    public HistoryServicePackJSON fromServicePackHistory(ServicePackInfo servicePackInfo) {
        HistoryServicePackJSON his = new HistoryServicePackJSON();
        his.setId(servicePackInfo.getId());
        his.setName(servicePackInfo.getName());
        his.setCode(servicePackInfo.getCode());
        his.setUpdatedBy(servicePackInfo.getUpdatedBy());
        his.setNameUpdatedBy(servicePackInfo.getAccountAdminId().getName());
        his.setUpdatedAt(servicePackInfo.getUpdatedAt());
        his.setEffectiveAt(servicePackInfo.getEffectiveAt());
        his.setActive(servicePackInfo.getActive());
        his.setServicePackSettingId(servicePackInfo.getServicePackSettingId());
        return his;
    }

}
