package cn.lngex.auth.domain;

public class AuthClientType {
    private String clientId;
    private String clientSecret;

    public static AuthClientType getInstan(int type){
        if(type == 1){
            return new AuthClientType().setClientId("website").setClientSecret("1");
        }else if(type == 0){
            return new AuthClientType().setClientId("admin").setClientSecret("1");
        }
        throw new RuntimeException("非法请求");
    }

    private AuthClientType() {
    }

    public String getClientId() {
        return clientId;
    }

    private AuthClientType setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    private AuthClientType setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }
}
