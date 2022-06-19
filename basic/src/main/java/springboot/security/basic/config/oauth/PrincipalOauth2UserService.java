package springboot.security.basic.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import springboot.security.basic.config.auth.PrincipalDetails;
import springboot.security.basic.model.User;
import springboot.security.basic.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * loadUser() : google 로그인 후 후처리되는 함수.
     * 구글로부터 받은 userRequest 데이터에 대한 후처리.
     * 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
     */
    @Override
    public OAuth2User loadUser (OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest getClientRegistration: " + userRequest.getClientRegistration());    // registrationId로 어떤 Oauth로 로그인했는지 알 수 있음
        System.out.println("userRequest getAccessToken : " + userRequest.getAccessToken());
        System.out.println("userRequest getAccessToken value : " + userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest : " + super.loadUser(userRequest).getAttributes());

        // super.loadUser() : 구글로부터 회원 프로필을 받아준다.
        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oauth2User.getAttributes());

        PrincipalDetails principalDetails = joinForOauthUser(userRequest, oauth2User);

        return new PrincipalDetails(principalDetails.getUser(), oauth2User.getAttributes());
    }

    /**
     * oauth 로그인 후 자동 회원가입
      */
    private PrincipalDetails joinForOauthUser (OAuth2UserRequest userRequest, OAuth2User oauth2User) {
        String provider = userRequest.getClientRegistration().getClientId();    // google
        String providerId = oauth2User.getAttribute("sub"); // google의 아이디
        String username = provider + "_" + providerId;  // ex) google_109385834929224959492
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oauth2User.getAttribute("email"); // google의 아이디
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = new User(username, password, email, role, provider, providerId);
            userRepository.save(userEntity);
        }
        return new PrincipalDetails(userEntity);
    }






}
