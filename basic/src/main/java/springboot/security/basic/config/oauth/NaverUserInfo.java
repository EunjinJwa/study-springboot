package springboot.security.basic.config.oauth;

import java.util.Map;

public class NaverUserInfo implements Oauth2UserInfo {

    Map<String, Object> attributes; // oauth2User.getAttribute()
    public NaverUserInfo (Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId () {
        return (String) attributes.get("id");
    }

    @Override
    public String getProvider () {
        return "naver";
    }

    @Override
    public String getEmail () {
        return (String) attributes.get("email");
    }
}
