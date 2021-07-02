package cn.lngex.securitydemo.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MyAuthorSucessHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sucess","false");
        map.put("message","授权失败");
        map.put("reultobject","!认真失败");
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        //httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.OK.value());
        String s = JSON.toJSONString(map);
        writer.write(s);
        writer.close();
    }
}
