package study.spring.restapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.spring.restapi.accounts.Account;
import study.spring.restapi.accounts.AccountRole;
import study.spring.restapi.accounts.AccountService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner () {
        return new ApplicationRunner() {
            @Autowired
            AccountService accountService;
            @Override
            public void run (ApplicationArguments args) throws Exception {
                Account jinny = Account.builder()
                        .email("admin@email.com")
                        .password("1234")
                        .roles(Collections.unmodifiableSet(new HashSet<>(Arrays.asList(AccountRole.ADMIN, AccountRole.USER))))
                        .build();
                accountService.saveAccount(jinny);
            }
        };
    }

}
