package vn.vmg.ptdv.hola.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.account.service.AccountService;
import vn.vmg.ptdv.hola.common.document.core.DocumentStorageProperties;
import vn.vmg.ptdv.hola.common.document.exception.DocumentStorageException;
import vn.vmg.ptdv.hola.common.document.repository.DocumentRepository;
import vn.vmg.ptdv.hola.common.document.service.DocumentStorageService;
import vn.vmg.ptdv.hola.common.masterData.bank.repository.BankRepository;
import vn.vmg.ptdv.hola.common.masterData.bank.service.BankService;
import vn.vmg.ptdv.hola.common.otp.repository.OTPVerifyRepository;
import vn.vmg.ptdv.hola.infra.shared.InfrastructureConfig;
import vn.vmg.ptdv.hola.order.repository.OrderRepository;
import vn.vmg.ptdv.hola.order.service.OrderService;

@Configuration
@Import({InfrastructureConfig.class})
public class RESTAppConfig {

    @Value("${file.upload-dir}")
    String uploadDir;

    @Bean
    AccountService standardAccountService(AccountRepository accountRepository,
            OTPVerifyRepository otpVerifyRepository, VARepository vaRepository, CashRepository cashRepository) {
        return new AccountService(accountRepository, otpVerifyRepository, vaRepository, cashRepository);
    }

    @Bean
    BankService standardBankService(BankRepository bankRepository) {
        return new BankService(bankRepository);
    }

    @Bean
    DocumentStorageService standardDocumentStorageService(DocumentRepository documentRepository) throws
            DocumentStorageException {
        return new DocumentStorageService(new DocumentStorageProperties(uploadDir), documentRepository);
    }

    @Bean
    OrderService standardOrderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);
    }
}
