package vn.vmg.ptdv.hola.infra.servicepack.mapper;

import vn.vmg.ptdv.hola.infra.servicepack.factory.ServicePackDB;
import vn.vmg.ptdv.hola.servicepack.core.ServicePackID;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePackInfo;
import vn.vmg.ptdv.hola.shared.AccountAdminId;
import vn.vmg.ptdv.hola.shared.AuditLog;

public class ServicePackDBMapper {
    private static ServicePackDBMapper INSTANCE;

    private ServicePackDBMapper() {

    }

    public static ServicePackDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicePackDBMapper();
        }
        return INSTANCE;
    }

    public ServicePack fromDB(ServicePackDB servicePackDB) {
        ServicePackID servicePackID = new ServicePackID(servicePackDB.getId(), servicePackDB.getCode());
        AuditLog auditLog = new AuditLog()
                .withCreatedAt(servicePackDB.getCreatedAt())
                .withCreatedBy(new AccountAdminId(servicePackDB.getCreatedBy()))
                .withUpdatedAt(servicePackDB.getUpdatedAt())
                .withUpdatedBy(new AccountAdminId(servicePackDB.getUpdatedBy()))
                .withUTimestamp(servicePackDB.getUTimestamp());

        ServicePack servicePack = new ServicePack();
        servicePack.setServicePackID(servicePackID);
        servicePack.setName(servicePackDB.getName());
        servicePack.setStatus(servicePackDB.getStatus());
        servicePack.setNote(servicePackDB.getNote());
        servicePack.setActive(servicePackDB.getActive());
        servicePack.setEffectiveAt(servicePackDB.getEffectiveAt());
        servicePack.setAuditLog(auditLog);
        servicePack.setMaxTime(servicePackDB.getMaxTime());
        servicePack.setMaxDistance(servicePackDB.getMaxDistance());
        servicePack.setMaxPoint(servicePackDB.getMaxPoint());
        servicePack.setMaxOrder(servicePackDB.getMaxOrder());
        servicePack.setBaseDistance(servicePackDB.getBaseDistance());
        servicePack.setBasePoint(servicePackDB.getBasePoint());
        servicePack.setBaseCost(servicePackDB.getBaseCost());
        servicePack.setBaseOrderDetail(servicePackDB.getBaseOrderDetail());
        servicePack.setSurchargeDistance(servicePackDB.getSurchargeDistance());
        servicePack.setSurchargePoint(servicePackDB.getSurchargePoint());
        servicePack.setSurchargeOrderDetail(servicePackDB.getSurchargeOrderDetail());
        servicePack.setPorterFee(servicePackDB.getPorterFee());
        servicePack.setDoorDeliveryFee(servicePackDB.getDoorDeliveryFee());
        servicePack.setRefundFee(servicePackDB.getRefundFee());
        servicePack.setPriceDeclareFee(servicePackDB.getPriceDeclareFee());
        servicePack.setType(servicePackDB.getType());
        servicePack.setOtherCost(servicePackDB.getOtherCost());
        return servicePack;
    }





    public ServicePackDB mapServicePackInfo(ServicePackInfo servicePackInfo) {
        ServicePackDB servicePackDB = new ServicePackDB();
        servicePackDB.setName(servicePackInfo.getName());
        servicePackDB.setCode(servicePackInfo.getCode());
        servicePackDB.setCreatedBy(servicePackDB.getCreatedBy());
        return servicePackDB;
    }

}
