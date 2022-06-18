package springboot.security.basic.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    /**
     * loadUser() : google 로그인 후 후처리되는 함수.
     * 구글로부터 받은 userRequest 데이터에 대한 후처리.
     */
    @Override
    public OAuth2User loadUser (OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest getClientRegistration: " + userRequest.getClientRegistration());    // registrationId로 어떤 Oauth로 로그인했는지 알 수 있음
        System.out.println("userRequest getAccessToken : " + userRequest.getAccessToken());
        System.out.println("userRequest getAccessToken value : " + userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest : " + super.loadUser(userRequest).getAttributes());

        OAuth2User oauth2User = super.loadUser(userRequest);

        return super.loadUser(userRequest);
    }
}
