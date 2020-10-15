package vn.vmg.ptdv.hola.infra.leadtime.mapper;

import vn.vmg.ptdv.hola.common.address.core.AddressId;
import vn.vmg.ptdv.hola.infra.leadtime.factory.LeadtimeRouteDB;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeId;
import vn.vmg.ptdv.hola.leadtime.core.LeadtimeRouteId;
import vn.vmg.ptdv.hola.leadtime.factory.LeadtimeRoute;
import vn.vmg.ptdv.hola.shared.AuditLog;
import vn.vmg.ptdv.hola.shared.Vehicle;
import vn.vmg.ptdv.hola.shared.Weight;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeadtimeRouteDBMapper {

    private static LeadtimeRouteDBMapper INSTANCE;

    private LeadtimeRouteDBMapper() {

    }

    public static LeadtimeRouteDBMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeadtimeRouteDBMapper();
        }
        return INSTANCE;
    }

    public LeadtimeRoute fromDB(LeadtimeRouteDB leadtimeRouteDB) {
        LeadtimeRoute leadtimeRoute = new LeadtimeRoute();
        leadtimeRoute.setLeadtimeRouteId(new LeadtimeRouteId(leadtimeRouteDB.getId(), leadtimeRouteDB.getCode()));
        leadtimeRoute.setLeadtimeId(new LeadtimeId(leadtimeRouteDB.getLeadTimePackId(), null));
        leadtimeRoute.setType(leadtimeRouteDB.getType());
        leadtimeRoute.setStatus(leadtimeRouteDB.getStatus());
        leadtimeRoute.setFromProvince(new AddressId(leadtimeRouteDB.getFromProvinceId(), leadtimeRouteDB.getFromProvinceCode()));
        leadtimeRoute.setToProvince(new AddressId(leadtimeRouteDB.getToProvinceId(), leadtimeRouteDB.getToProvinceCode()));
        leadtimeRoute.setFromDistrict(new AddressId(leadtimeRouteDB.getFromDistrictId(), leadtimeRouteDB.getFromDistrictCode()));
        leadtimeRoute.setToDistrict(new AddressId(leadtimeRouteDB.getToDistrictId(), leadtimeRouteDB.getToDistrictCode()));
        leadtimeRoute.setEstimatedDeliveryDay(leadtimeRouteDB.getEstimatedDeliveryDay());
        leadtimeRoute.setVehicle(new Vehicle(leadtimeRouteDB.getTransportType()));
        leadtimeRoute.setWeight(new Weight(leadtimeRouteDB.getLimitWeight(), leadtimeRouteDB.getMaxWeight(), leadtimeRouteDB.getLimitType()));
        leadtimeRoute.setFromProvinceName(leadtimeRouteDB.getFromProvinceName());
        leadtimeRoute.setToProvinceName(leadtimeRouteDB.getToProvinceName());
        leadtimeRoute.setFromDistrictName(leadtimeRouteDB.getFromDistrictName());
        leadtimeRoute.setToDistrictName(leadtimeRouteDB.getToDistrictName());
        leadtimeRoute.setAuditLog(new AuditLog().withUTimestamp(leadtimeRouteDB.getUTimestamp()));
        return leadtimeRoute;
    }

    public LeadtimeRouteDB fromResultSet(ResultSet rs) throws SQLException {
        LeadtimeRouteDB objDB = new LeadtimeRouteDB();
        objDB.setId(rs.getLong(LeadtimeRouteDB.ID));
        objDB.setLeadTimePackId(rs.getLong(LeadtimeRouteDB.LEADTIME_PACK_ID));
        objDB.setCode(rs.getString(LeadtimeRouteDB.CODE));
        objDB.setType(rs.getInt(LeadtimeRouteDB.TYPE));
        objDB.setStatus(rs.getInt(LeadtimeRouteDB.STATUS));
        objDB.setFromProvinceId(rs.getLong(LeadtimeRouteDB.FROM_PROVINCE_ID));
        objDB.setFromProvinceCode(rs.getString(LeadtimeRouteDB.FROM_PROVINCE_CODE));
        objDB.setToProvinceId(rs.getLong(LeadtimeRouteDB.TO_PROVINCE_ID));
        objDB.setToProvinceCode(rs.getString(LeadtimeRouteDB.TO_PROVINCE_CODE));
        objDB.setEstimatedDeliveryDay(rs.getInt(LeadtimeRouteDB.ESTIMATED_DELIVERY_DAY));
        objDB.setTransportType(rs.getInt(LeadtimeRouteDB.TRANSPORT_TYPE));
        objDB.setLimitWeight(rs.getDouble(LeadtimeRouteDB.LIMIT_WEIGHT));
        objDB.setLimitType(rs.getInt(LeadtimeRouteDB.LIMIT_TYPE));
        objDB.setMaxWeight(rs.getDouble(LeadtimeRouteDB.MAX_WEIGHT));
        objDB.setFromDistrictId(rs.getLong(LeadtimeRouteDB.FROM_DISTRICT_ID));
        objDB.setToDistrictId(rs.getLong(LeadtimeRouteDB.TO_DISTRICT_ID));
        objDB.setFromDistrictCode(rs.getString(LeadtimeRouteDB.FROM_DISTRICT_CODE));
        objDB.setToDistrictCode(rs.getString(LeadtimeRouteDB.TO_DISTRICT_CODE));
        objDB.setFromProvinceName(rs.getString(LeadtimeRouteDB.FROM_PROVINCE_NAME));
        objDB.setToProvinceName(rs.getString(LeadtimeRouteDB.TO_PROVINCE_NAME));
        objDB.setFromDistrictName(rs.getString(LeadtimeRouteDB.FROM_DISTRICT_NAME));
        objDB.setToDistrictName(rs.getString(LeadtimeRouteDB.TO_DISTRICT_NAME));
        objDB.setUTimestamp(rs.getTimestamp(LeadtimeRouteDB.UTIMESTAMP));
        return objDB;
    }
    
}
