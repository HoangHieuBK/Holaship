package vn.vmg.ptdv.hola.infra.shared;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.vmg.ptdv.hola.infra.account.AccountInfraConfig;
import vn.vmg.ptdv.hola.infra.common.CommonInfraConfig;
import vn.vmg.ptdv.hola.infra.leadtime.OrderInfraConfig;

@Configuration
@EnableJpaRepositories(basePackages = {"vn.vmg.ptdv.hola.infra.jpa"})
@EntityScan(basePackages = {"vn.vmg.ptdv.hola.infra.jpa"})
@Import({AccountInfraConfig.class, CommonInfraConfig.class, OrderInfraConfig.class})
public class InfrastructureConfig {
}
