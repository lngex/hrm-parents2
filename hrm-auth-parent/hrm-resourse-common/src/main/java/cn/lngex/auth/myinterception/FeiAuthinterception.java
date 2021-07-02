package cn.lngex.auth.myinterception;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
public class FeiAuthinterception implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        /* 获取上下文对象 */
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        /* 获取请求 */
        HttpServletRequest request = requestAttributes.getRequest();
        /* 获取授权信息 */
        String authorization = request.getHeader("Authorization");

        /* 设置到feign请求中 */
        template.header("Authorization",authorization);
    }
}
