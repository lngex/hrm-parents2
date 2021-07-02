package cn.lngex.auth.interception;

import cn.lngex.utils.encrypt.HttpRequest;
import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClientInterceptor implements RequestInterceptor {

    private final String clientCredentialsUrl = "http://localhost:3020/oauth/token?grant_type=client_credentials&scope=hrm&client_id=system&client_secret=1";


    @Override
    public void apply(RequestTemplate template) {
        String str = HttpRequest.get(clientCredentialsUrl);
        Map mapTypes = JSON.parseObject(str);
        String access_token = "Bearer " + mapTypes.get("access_token");
        template.header("Authorization",access_token);
    }
}
