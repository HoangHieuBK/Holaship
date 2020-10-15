package vn.vmg.ptdv.hola.infra.servicepack;

import org.springframework.beans.BeanUtils;
import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;

public class ServicePackMapper {
    private static ServicePackMapper INSTANCE;

    public ServicePackMapper() {
    }

    public static ServicePackMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePackMapper();
        }
        return INSTANCE;
    }

    public ServicePackInfo fromServicePackDB(ServicePackDB servicePackDB) {
        ServicePackInfo servicePack = new ServicePackInfo();
        servicePack.setName(servicePackDB.getName());
        servicePack.setCode(servicePackDB.getCode());
        return servicePack;
    }

    public ServicePackInfo fromServicePackDBAll(ServicePackDB servicePackDB) {
        ServicePackInfo servicePack = new ServicePackInfo();
        BeanUtils.copyProperties(servicePackDB, servicePack);
        return servicePack;
    }

    public ServicePackDB fromServicePackInfo(ServicePackInfo servicePackInfo) {
        ServicePackDB servicePackDB = new ServicePackDB();
        BeanUtils.copyProperties(servicePackInfo, servicePackDB);
        return servicePackDB;
    }

    public ServicePackInfo fromHistoryServicePackDB(ServicePackDB servicePackDB) {
        ServicePackInfo servicePack = new ServicePackInfo();
//        servicePack
//                .setAccountAdminId(new AccountAdminId(servicePackDB.getUpdatedBy(), servicePackDB.getNameUpdatedBy()));
        servicePack.setId(servicePackDB.getId());
        servicePack.setName(servicePackDB.getName());
        servicePack.setCode(servicePackDB.getCode());
        servicePack.setUpdatedBy(servicePackDB.getUpdatedBy());
//        servicePack.setUpdatedAt(servicePackDB.getUpdatedAt());
//        servicePack.setServicePackSettingId(servicePackDB.getServicePackSettingId());
//        servicePack.setEffectiveAt(servicePackDB.getEffectiveAt());
        servicePack.setActive(servicePackDB.getActive());
        servicePack.setUpdatedBy(servicePackDB.getUpdatedBy());
        return servicePack;
    }

    public ServicePackInfo fromfindServicePackByIdDB(ServicePackDB servicePackDB) {
        ServicePackInfo servicePack = new ServicePackInfo();
        servicePack.setId(servicePackDB.getId());
        servicePack.setName(servicePackDB.getName());
        servicePack.setCode(servicePackDB.getCode());
//        servicePack.setServicePackSettingId(servicePackDB.getServicePackSettingId());
        servicePack.setCreatedBy(servicePackDB.getCreatedBy());
//        servicePack.setCreatedAt(servicePackDB.getCreatedAt());
//        servicePack
//                .setAccountAdminId(new AccountAdminId(servicePackDB.getUpdatedBy(), servicePackDB.getNameUpdatedBy()));
        servicePack.setUTimestamp(servicePackDB.getUTimestamp());
        servicePack.setActive(servicePackDB.getActive());
        servicePack.setNote(servicePackDB.getNote());
        servicePack.setMaxTime(servicePackDB.getMaxTime());
        servicePack.setMaxDistance(servicePackDB.getMaxDistance());
        servicePack.setMaxPoint(servicePackDB.getMaxPoint());
        servicePack.setBaseDistance(servicePackDB.getBaseDistance());
        servicePack.setBasePoint(servicePackDB.getBasePoint());
        servicePack.setSurchargeDistance(servicePackDB.getSurchargeDistance());
        servicePack.setSurchargePoint(servicePackDB.getSurchargePoint());
        servicePack.setBaseCost(servicePackDB.getBaseCost());
        servicePack.setBaseOrderDetail(servicePackDB.getBaseOrderDetail());
        servicePack.setMaxOrder(servicePackDB.getMaxOrder());
        servicePack.setSurchargeOrderDetail(servicePackDB.getSurchargeOrderDetail());
        servicePack.setPorterFee(servicePackDB.getPorterFee());
        servicePack.setDoorDeliveryFee(servicePackDB.getDoorDeliveryFee());
        servicePack.setRefundFee(servicePackDB.getRefundFee());
        servicePack.setPriceDeclareFee(servicePackDB.getPriceDeclareFee());
        servicePack.setType(servicePackDB.getType());
//        servicePack.setEffectiveAt(servicePackDB.getEffectiveAt());
        servicePack.setOtherCost(servicePackDB.getOtherCost());
//        servicePack.setUpdatedAt(servicePackDB.getUpdatedAt());
        return servicePack;
    }
}
