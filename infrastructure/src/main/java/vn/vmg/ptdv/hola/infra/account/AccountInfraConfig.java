package vn.vmg.ptdv.hola.infra.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.vmg.ptdv.hola.account.repository.AccountRepository;
import vn.vmg.ptdv.hola.account.repository.CashRepository;
import vn.vmg.ptdv.hola.account.repository.VARepository;
import vn.vmg.ptdv.hola.infra.imedia.IMediaRestAPI;
import vn.vmg.ptdv.hola.infra.imedia.RESTInfraConfig;

import javax.sql.DataSource;


@Configuration
@Import({RESTInfraConfig.class})
public class AccountInfraConfig {

    @Bean
    AccountJDBC standardAccountJDBC(DataSource dataSource) {
        return new AccountJDBC(dataSource);
    }

    @Bean
    VAJDBC standardVAJDBC(DataSource dataSource) {
        return new VAJDBC(dataSource);
    }

    @Bean
    CashJDBC standardCashJDBC(DataSource dataSource) {
        return new CashJDBC(dataSource);
    }

    @Bean
    AccountRepository standardAccountRepository(IMediaRestAPI iMediaRestAPI, AccountJDBC accountJDBC) {
        AccountRepository accountRepository = new AccountRepositoryImpl(iMediaRestAPI, accountJDBC);
        return accountRepository;
    }

    @Bean
    VARepository standardVARepository(IMediaRestAPI iMediaRestAPI, VAJDBC vajdbc) {
        VARepository vaRepository = new VARepositoryImpl(iMediaRestAPI, vajdbc);
        return vaRepository;
    }
    @Bean
    CashRepository standardCashRepository(IMediaRestAPI iMediaRestAPI, CashJDBC cashJDBC){
        CashRepository cashRepository = new CashRepositoryImpl(iMediaRestAPI,cashJDBC);
        return cashRepository;
    }
}
