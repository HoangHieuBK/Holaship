package vn.vmg.ptdv.hola.infra.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.vmg.ptdv.hola.common.document.repository.DocumentRepository;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;
import vn.vmg.ptdv.hola.infra.common.document.DocumentJDBC;
import vn.vmg.ptdv.hola.infra.common.document.DocumentRepositoryImpl;
import vn.vmg.ptdv.hola.infra.common.otp.OTPVerifyRepositoryImpl;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestAPI;
import vn.vmg.ptdv.hola.infra.imedia.RESTInfraConfig;

import javax.sql.DataSource;

@Configuration
@Import({RESTInfraConfig.class})
public class CommonInfraConfig {
    @Bean
    OTPVerifyRepository otpVerifyRepository(IMediaRestAPI iMediaRestAPI) {
        return new OTPVerifyRepositoryImpl(iMediaRestAPI);
    }

    @Bean
    DocumentJDBC getDocumentJDBC(DataSource dataSource) {
        return new DocumentJDBC(dataSource);
    }

    @Bean
    DocumentRepository documentRepository(DocumentJDBC documentJDBC) {
        return new DocumentRepositoryImpl(documentJDBC);
    }
}
