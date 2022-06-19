package springboot.security.basic.config.oauth;


public interface Oauth2UserInfo {

    public String getProviderId();
    public String getProvider();
    public String getEmail();
}
