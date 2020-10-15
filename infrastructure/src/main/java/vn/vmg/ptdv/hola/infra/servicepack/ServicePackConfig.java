//package vn.vmg.ptdv.hola.infra.servicepack;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ServicePackConfig {
//    @Bean
//    ServicePackJDBC getServicePackJDBC(DataSource dataSource) {
//        return new ServicePackJDBC(dataSource);
//    }
//
//    @Bean
//    ServicePackRepository getServicePackRepository(ServicePackJDBC servicePackJDBC) {
//        ServicePackRepository servicePackRepository = new ServicePackRepositoryImpl(servicePackJDBC);
//        return servicePackRepository;
//    }
//}
//
