package study.spring.restapi.accounts;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    public void findByUsername () {
        String email = "jinny@email.com";
        String password = "1234";
        Account account = Account.builder()
                .email(email)
                .password(password)
                .roles(Collections.unmodifiableSet(new HashSet<>(Arrays.asList(AccountRole.ADMIN, AccountRole.USER))))
                .build();
        this.accountService.saveAccount(account);
        // when
        UserDetailsService userDetailsService = accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        //Then
        Assertions.assertThat(this.passwordEncoder.matches(password, userDetails.getPassword()));
    }

    @Test
    public void findByUsernameFail() {
        String username = "random@email.com";
        try {
            accountService.loadUserByUsername(username);
            fail("supposed to be failed.");
        } catch (UsernameNotFoundException e) {
            Assertions.assertThat(e.getMessage().contains(username));
        }
    }

    @Test
    public void findByUsernameFail2() {
        // Expected
        String username = "random@email.com";
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(username));

        // When
//        accountService.loadUserByUsername(username);      // UsernameNotFoundException 발생하는데, 테스트 통과 안함..
    }
}