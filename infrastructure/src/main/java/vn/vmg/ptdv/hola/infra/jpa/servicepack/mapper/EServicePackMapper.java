package vn.vmg.ptdv.hola.infra.jpa.servicepack.mapper;

import vn.vmg.ptdv.hola.infra.jpa.servicepack.entity.EServicePackSetting;
import vn.vmg.ptdv.hola.servicepack.factory.ServicePack;
import vn.vmg.ptdv.hola.shared.AuditLog;

public class EServicePackMapper {

    private static EServicePackMapper INSTANCE;

    public static EServicePackMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EServicePackMapper();
        }
        return INSTANCE;
    }

    public EServicePackSetting bindSPSettingFrom(ServicePack servicePack) {
        EServicePackSetting entity = new EServicePackSetting();
        return entity;
    }

    public ServicePack bindSFromPSetting(EServicePackSetting entity) {

        if(entity != null){
            ServicePack servicePack = new ServicePack();
//            servicePack.setState(OrderDomainState.fromBoolean(entity.getEffectiveStatus()));
//            servicePack.setServicePackID(new ServicePackID(entity.getEServicePack().getCode(), entity.getId()));
            servicePack.setAuditLog(new AuditLog().withUTimestamp(entity.getUTimestamp()));
//            servicePack.setOtherCost(new Money(entity.getOtherCost()));
//            servicePack.setDeclarePriceCost(new Money(entity.getDeclarePriceCost()));
//            servicePack.setRefundCost(new Money(entity.getRefundCost()));
//            servicePack.setD2dCost(new Money(entity.getRefundCost()));
//            servicePack.setPorterage(new Money(entity.getPorterage()));
//            servicePack.setPriceBase(new Money(entity.getPriceBase()));
//            servicePack.setOrderDetailSetting(new OrderDetailSetting()
//                    .withBase(entity.getDetailOrderBase())
//                    .withMax(entity.getDetailOrderMax())
//                    .withCost(new Money(entity.getDetailOrderCost()))
//            );
//            servicePack.setDestination(new Destination()
//                    .withBase(entity.getDestinationBase())
//                    .withMax(entity.getDestinationMax())
//                    .withCost(new Money(entity.getDestinationCost()))
//            );
//            servicePack.setDistance(new Distance()
//                    .withBase(entity.getDistanceBase())
//                    .withMax(entity.getDistanceMax())
//                    .withCost(new Money(entity.getDistanceCost()))
//            );
//            servicePack.setEffectiveDate(entity.getEffectiveDate());
//            servicePack.setDeliveryTimeMax(entity.getDeliveryTimeMax());
//            servicePack.setDescription(entity.getDescription());
//            servicePack.setName(entity.getEServicePack().getName());
//            return servicePack;
        }
        return null;
    }
}
