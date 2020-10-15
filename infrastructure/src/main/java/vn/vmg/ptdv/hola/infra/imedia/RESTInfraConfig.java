package vn.vmg.ptdv.hola.infra.imedia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RESTInfraConfig {
    @Bean
    IMediaRestAPI iMediaRestAPI() {
        return new IMediaRestAPIImpl();
    }

}
