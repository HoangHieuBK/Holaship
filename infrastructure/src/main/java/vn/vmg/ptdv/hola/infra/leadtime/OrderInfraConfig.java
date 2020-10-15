package vn.vmg.ptdv.hola.infra.leadtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.vmg.ptdv.hola.common.masterData.bank.repository.BankRepository;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.infra.common.bank.BankJDBC;
import vn.vmg.ptdv.hola.infra.common.bank.BankRepositoryImpl;
import vn.vmg.ptdv.hola.infra.district.DistrictJDBC;
import vn.vmg.ptdv.hola.infra.district.DistrictRepositoryImpl;
import vn.vmg.ptdv.hola.infra.imedia.RESTInfraConfig;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.ServicePackRepositoryImpl;
import vn.vmg.ptdv.hola.infra.jpa.servicepack.repository.ServicePackSettingJPARepository;
import vn.vmg.ptdv.hola.infra.order.OrderJDBC;
import vn.vmg.ptdv.hola.infra.order.OrderRepositoryImpl;
import vn.vmg.ptdv.hola.infra.province.ProvinceJDBC;
import vn.vmg.ptdv.hola.infra.province.ProvinceRepositoryImpl;
import vn.vmg.ptdv.hola.infra.servicepack.ServicePackJDBC;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeLogRepository;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.order.repository.OrderRepository;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;

import javax.sql.DataSource;


@Configuration
@Import({RESTInfraConfig.class})
public class OrderInfraConfig {

    // JDBC
    @Bean
    LeadtimeJDBC standardLeadtimeJDBC(DataSource dataSource) {
        return new LeadtimeJDBC(dataSource);
    }

    @Bean
    LeadtimeRouteJDBC standardLeadtimeRouteJDBC(DataSource dataSource) {
        return new LeadtimeRouteJDBC(dataSource);
    }

    @Bean
    LeadtimeLogJDBC standardLeadtimeLogJDBC(DataSource dataSource) {
        return new LeadtimeLogJDBC(dataSource);
    }

    @Bean
    ServicePackJDBC standardServicePackJDBC(DataSource dataSource) {
        return new ServicePackJDBC(dataSource);
    }

    @Bean
    BankJDBC standardBankJDBC(DataSource dataSource){
        return new BankJDBC(dataSource);
    }

    @Bean
    ProvinceJDBC standardProvinceJDBC(DataSource dataSource){
        return new ProvinceJDBC(dataSource);
    }

    @Bean
    DistrictJDBC standardDistrictJDBC(DataSource dataSource){
        return new DistrictJDBC(dataSource);
    }

    @Bean
    OrderJDBC standardOrderJDBC(DataSource dataSource){return new OrderJDBC(dataSource);}

    // Repository
    @Bean
    LeadtimeRepository standardLeadtimeRepository(LeadtimeJDBC jdbc) {
        LeadtimeRepository repository = new LeadtimeRepositoryImpl(jdbc);
        return repository;
    }

    @Bean
    LeadtimeRouteRepository standardLeadtimeRouteRepository(LeadtimeRouteJDBC jdbc) {
        LeadtimeRouteRepository repository = new LeadtimeRouteRepositoryImpl(jdbc);
        return repository;
    }

    @Bean
    LeadtimeLogRepository standardLeadtimeLogRepository(LeadtimeLogJDBC jdbc) {
        LeadtimeLogRepository repository = new LeadtimeLogRepositoryImpl(jdbc);
        return repository;
    }

    @Bean
    ServicePackRepository jpaServicePackRepository(ServicePackSettingJPARepository jpaRepository, ServicePackJDBC servicePackJDBC) {
        return new ServicePackRepositoryImpl(jpaRepository, servicePackJDBC);
    }

    @Bean
    BankRepository standardBankRepository(BankJDBC bankJDBC){
        BankRepository bankRepository = new BankRepositoryImpl(bankJDBC);
        return bankRepository;
    }

    @Bean
    ProvinceRepository standardProvinceRepository(ProvinceJDBC jdbc) {
        ProvinceRepository repository = new ProvinceRepositoryImpl(jdbc);
        return repository;
    }

    @Bean
    DistrictRepository standardDistrictRepository(DistrictJDBC jdbc) {
        DistrictRepository repository = new DistrictRepositoryImpl(jdbc);
        return repository;
    }

    @Bean
    OrderRepository standardOrderRepository(OrderJDBC orderJDBC){
        OrderRepository repository = new OrderRepositoryImpl(orderJDBC);
        return repository;
    }
    
}
