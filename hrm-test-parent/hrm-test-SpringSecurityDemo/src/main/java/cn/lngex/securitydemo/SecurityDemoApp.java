package cn.lngex.securitydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lngex.securitydemo.mapper")
public class SecurityDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApp.class);
    }
}
