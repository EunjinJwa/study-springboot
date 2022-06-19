package springboot.security.basic.config.oauth;

import java.util.Map;

public class GoogleUserInfo implements Oauth2UserInfo {
    public Map<String, Object> attributes;
    public GoogleUserInfo (Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId () {
        return (String) attributes.get("id");
    }

    @Override
    public String getProvider () {
        return "google";
    }

    @Override
    public String getEmail () {
        return (String) attributes.get("email");
    }
}
