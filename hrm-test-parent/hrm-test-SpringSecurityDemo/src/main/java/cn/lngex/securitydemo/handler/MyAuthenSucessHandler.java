package cn.lngex.securitydemo.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MyAuthenSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sucess","trues");
        map.put("message","认证成功");
        map.put("reultobject",authentication.getName()+"!认真成功");
        map.put("code", HttpStatus.OK.value());
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        PrintWriter writer = httpServletResponse.getWriter();
        // httpServletResponse.setHeader("Content-Type","application/json; charset=utf8");
        String s = JSON.toJSONString(map);
        writer.write(s);
        writer.close();
    }
}
