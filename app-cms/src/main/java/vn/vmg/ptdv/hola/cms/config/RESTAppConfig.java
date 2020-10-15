package vn.vmg.ptdv.hola.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;
import vn.vmg.ptdv.hola.district.repository.DistrictRepository;
import vn.vmg.ptdv.hola.infra.shared.InfrastructureConfig;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeLogRepository;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRepository;
import vn.vmg.ptdv.hola.leadtime.repository.LeadtimeRouteRepository;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeLogService;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeRouteService;
import vn.vmg.ptdv.hola.leadtime.service.LeadtimeService;
import vn.vmg.ptdv.hola.province.repository.ProvinceRepository;
import vn.vmg.ptdv.hola.servicepack.repository.ServicePackRepository;
import vn.vmg.ptdv.hola.servicepack.service.ServicePackService;

@Configuration
@Import({InfrastructureConfig.class})
public class RESTAppConfig {
    @Bean
    AccountService standardAccountService(AccountRepository accountRepository,
            OTPVerifyRepository otpVerifyRepository, VARepository vaRepository, CashRepository cashRepository) {
        return new AccountService(accountRepository, otpVerifyRepository, vaRepository, cashRepository);
    }

    @Bean
    ServicePackService standardServicePackService(ServicePackRepository servicePackRepository) {
        return new ServicePackService(servicePackRepository);
    }

    @Bean
    LeadtimeService standardLeadtimeService(LeadtimeRepository leadtimeRepository,
                                            LeadtimeRouteRepository leadtimeRouteRepository, ProvinceRepository provinceRepository,
                                            DistrictRepository districtRepository) {
        return new LeadtimeService(leadtimeRepository, leadtimeRouteRepository, provinceRepository, districtRepository);
    }

    @Bean
    LeadtimeLogService standardLeadtimeLogService(LeadtimeLogRepository leadtimeLogRepository) {
        return new LeadtimeLogService(leadtimeLogRepository);
    }

    @Bean
    LeadtimeRouteService standardLeadtimeRouteService(LeadtimeRouteRepository leadtimeRouteRepository) {
        return new LeadtimeRouteService(leadtimeRouteRepository);
    }
}
