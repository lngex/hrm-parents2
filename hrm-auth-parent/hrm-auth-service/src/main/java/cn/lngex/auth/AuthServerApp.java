package cn.lngex.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "cn.lngex.auth")
@MapperScan("cn.lngex.auth.mapper")
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.lngex.system.feign")
public class AuthServerApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApp.class);
    }
}
