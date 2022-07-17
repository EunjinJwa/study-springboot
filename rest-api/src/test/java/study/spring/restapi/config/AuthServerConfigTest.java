package study.spring.restapi.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import study.spring.restapi.accounts.Account;
import study.spring.restapi.accounts.AccountRole;
import study.spring.restapi.accounts.AccountService;
import study.spring.restapi.common.BaseControllerTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        // Given
        String username = "jinny@gmmm.com";
        String password = "jjj";
        Account account = Account.builder()
                .email(username)
                .password(password)
                .roles(Collections.unmodifiableSet(new HashSet<>(Arrays.asList(AccountRole.ADMIN, AccountRole.USER))))
                .build();

        this.accountService.saveAccount(account);


        String clientId = "myApp";
        String clientSecret = "pass";
        this.mockMvc.perform(post("/oauth/token")
                        .with(httpBasic(clientId, clientSecret))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("access_token").exists());
    }
}