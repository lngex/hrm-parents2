package cn.lngex.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("cn.lngex.auth.mapper")
@EnableEurekaClient
public class AuthServerApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApp.class);
    }
}
